(defproject cloko "0.1.0"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [org.clojure/test.check "0.9.0"]
                 [devcards "0.2.2"]
                 [lein-doo "0.1.7"]
                 [reagent "0.6.0"]
                 [com.cognitect/transit-cljs "0.8.239"]
                 [org.clojure/math.combinatorics "0.1.4"]]
  :plugins [[lein-figwheel "0.5.8"]
            [lein-doo "0.1.7"]
            [devcards "0.2.2"]
            [lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]
            [lein-ancient "0.6.10"]]
  :hooks [leiningen.cljsbuild]
  :figwheel {
             :css-dirs ["resources/public/css"]}
  :doo {
        :build "test"
        :alias {:default [:phantom]}
        :paths {:phantom "phantomjs --web-security=false"}}

  :aliases {"test" ["doo" "once"]
            "compile" ["cljsbuild" "once" "min"]
            "kibit" ["with-profile" "kibit" "kibit"]}

  :profiles {:kibit {:plugins [[lein-kibit "0.1.3"]]}}

  :clean-targets ^{:protect false} [:target-path "resources/public/cljs/"]

  :cljsbuild {
              :test-commands {"test" ["lein" "doo" "once"]}
              :builds {:dev {:source-paths ["src"]
                             :figwheel true
                             :compiler {:main "cloko.frontend"
                                        :optimizations :none
                                        :pretty-print true
                                        :source-map true
                                        :asset-path "cljs/dev/out"
                                        :output-dir "resources/public/cljs/dev/out"
                                        :output-to "resources/public/cljs/main.js"}}
                       :test {
                              :source-paths ["src" "test"]
                              :compiler {:main runners.doo
                                         :optimizations :none
                                         :asset-path "resources/public/cljs/test/out"
                                         :output-dir "resources/public/cljs/test/out"
                                         :output-to "resources/public/cljs/test/all-tests.js"}}
                       :devcard-test {
                                      :source-paths ["src" "test"]
                                      :figwheel {:devcards true}
                                      :compiler {:main runners.browser
                                                 :optimizations :none
                                                 :asset-path "cljs/devcard-test/out"
                                                 :output-dir "resources/public/cljs/devcard-test/out"
                                                 :output-to "resources/public/cljs/devcard-test/all-tests.js"
                                                 :source-map-timestamp true}}
                       :min {
                             :source-paths ["src"]
                             :compiler {
                                        :optimizations :advanced
                                        :elide-asserts true
                                        :output-to "resources/public/cljs/main.js"}}}})


