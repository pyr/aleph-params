(defproject spootnik/aleph-params "0.1.0"
  :description "Netty-based query string decoder"
  :url "https://github.com/pyr/aleph-params"
  :license {:name "MIT/ISC"}
  :dependencies [[org.clojure/clojure  "1.10.1"]
                 [io.netty/netty-all    "4.1.43.Final"]]
  :deploy-repositories [["releases" :clojars] ["snapshots" :clojars]]
  :profiles {:dev {:dependencies [[exoscale/interceptor "0.1.6"]
                                  [aleph                "0.4.7-alpha5"]]}})
