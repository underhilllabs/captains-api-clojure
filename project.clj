(defproject cptnapi "0.1.0-SNAPSHOT"
  :description "Vote for your Favorite Captain of the Starz"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.2"]
                 [clj-time "0.9.0"]
                 [http-kit "2.1.16"]
                 [hiccup "1.0.5"]
                 [com.novemberain/monger "2.0.0"]
                 [cheshire "5.3.1"]]
  :main ^:skip-aot cptnapi.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
