(let [cfg   (clojure.edn/read-string (slurp "deps.edn"))
      deps  (for [[k {:keys [mvn/version exclusions]}] (:deps cfg)]
              [k version :exclusions exclusions])
      paths (:paths cfg)]
  (defproject spootnik/aleph-params "0.1.1"
    :description "Netty-based query string decoder"
    :url "https://github.com/pyr/aleph-params"
    :license {:name "MIT/ISC"}
    :dependencies ~deps
    :source-paths ~paths
    :deploy-repositories [["releases" :clojars] ["snapshots" :clojars]]
    :pedantic? :abort
    :global-vars {*warn-on-reflection* true}
    :profiles {:dev {:dependencies [[exoscale/interceptor "0.1.6"]
                                    [aleph                "0.4.7-alpha5"]]}}))
