(defproject jfxtest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2280"]
                 [reacl "1.0.0"]
                 [com.facebook/react "0.11.1"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild {:builds [{:id "app"
                        :source-paths ["src"]
                        :compiler {:preamble ["react/react_with_addons.js"]
                                   :output-to "target/main.js"
                                   :output-dir "target/out"
                                   :source-map "target/main.js.out"
                                   :optimizations :none}}]})
