(defproject team-code-off "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
  				 [http-kit "2.1.16"]]
  :main ^:skip-aot team-code-off.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
