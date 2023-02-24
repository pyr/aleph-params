(defproject spootnik/aleph-params "0.1.8-SNAPSHOT"
  :description "Netty-based query string decoder"
  :url "https://github.com/pyr/aleph-params"
  :license {:name "MIT/ISC"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :deploy-repositories [["releases" :clojars] ["snapshots" :clojars]]
  :java-source-paths ["java"]
  :javac-options ["-target" "1.8" "-source" "1.8" "-Xlint:-options"]
  :pedantic? :abort
  :global-vars {*warn-on-reflection* true}
  :profiles {:test {:dependencies [[exoscale/interceptor "0.1.10"]
                                   [aleph                "0.4.7"]]
                    :pedantic?    :ignore
                    :global-vars  {*warn-on-reflection* false}}})
