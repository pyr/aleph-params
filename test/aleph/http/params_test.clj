(ns aleph.http.params-test
  (:require [clojure.test                  :refer :all]
            [aleph.http.params             :refer :all]
            [aleph.http                    :refer [start-server get]]
            [exoscale.interceptor.manifold :refer [execute]])
  (:refer-clojure :exclude [get]))

(defn get-port []
  (let [sock (java.net.ServerSocket. 0)
        port (.getLocalPort sock)]
    (.close sock)
    port))

(deftest unit-test

  (is (= {:one "one" :two ["one" "two"]}
         (parse-params "?two=one&one=one&two=two")))

  (is (thrown? java.lang.NullPointerException (parse-params nil))))

(defn handler [params request]
  (swap! params conj (:get-params request))
  {:status 200 :body ""})



(deftest ring-integration-test
  (let [params (atom [])
        port   (get-port)
        srv    (start-server (wrap-params (partial handler params))
                             {:port port})]

    @(get (format "http://localhost:%s?two=one&one=one&two=two" port))
    @(get (format "http://localhost:%s" port))
    @(get (format "http://localhost:%s?foo=bar" port))

    (is (= [{:one "one" :two ["one" "two"]} nil {:foo "bar"}] @params))
    (.close srv)))

(def ix-handler
  {:name :handler
   :enter (fn [{:keys [params request] :as ctx}]
            (swap! params conj (:get-params request))
            (assoc ctx :response {:status 200 :body ""}))})

(deftest interceptor-integration-test
  (let [port   (get-port)
        params (atom [])
        chain  #(execute {:params params :request %}
                         [{:name :exit :leave :response}
                          interceptor
                          ix-handler])
        srv    (start-server chain {:port port})]
    @(get (format "http://localhost:%d?two=one&one=one&two=two" port))
    @(get (format "http://localhost:%d" port))
    @(get (format "http://localhost:%d?foo=bar" port))

    (is (= [{:one "one" :two ["one" "two"]} nil {:foo "bar"}] @params))
    (.close srv)))
