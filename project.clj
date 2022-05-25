(defproject spootnik/aleph-params "0.1.5-SNAPSHOT"
  :description "Netty-based query string decoder"
  :url "https://github.com/pyr/aleph-params"
  :license {:name "MIT/ISC"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.netty/netty-codec "4.1.77.Final"]]
  :deploy-repositories [["releases" :clojars] ["snapshots" :clojars]]
  :pedantic? :abort
  :global-vars {*warn-on-reflection* true}
  :profiles {:dev {:dependencies [[exoscale/interceptor "0.1.10"]
                                  [aleph                "0.4.7"]]
                   :pedantic?    :ignore
                   :global-vars  {*warn-on-reflection* false}}})
