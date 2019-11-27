(ns aleph.http.params
  "Netty-based query string parameter handling functions"
  (:import io.netty.handler.codec.http.QueryStringDecoder))

(defn- extract-param
  "Transform query argument: keywordize key and extract single
   values."
  [[k vs]]
  [(keyword k) (if (= 1 (count vs)) (first vs) (vec vs))])

(defn parse-params
  "Given a query-string yield a map of argument name to value.
   Multiple occurences of the same argument yield a vector of
   values."
  [input]
  (let [decoder (QueryStringDecoder. input false)]
    (into {} (map extract-param) (.parameters decoder))))

(defn add-params
  "Add parsed params to a request at the `:get-params' key.
   Empty query strings yield an unmodified request map."
  [{:keys [query-string] :as request}]
  (cond-> request
    (some? query-string) (assoc :get-params (parse-params query-string))))

(defn wrap-params
  "A ring wrapper for parameters."
  [f]
  (comp f add-params))

(def interceptor
  "An interceptor-style handler for query args."
  {:name  :params
   :enter #(update % :request add-params)})
