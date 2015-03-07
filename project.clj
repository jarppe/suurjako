(defproject suurjako "0.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2850"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [reagent "0.5.0-alpha3"]]

  :plugins [[lein-cljsbuild "1.0.5"]]

  :cljsbuild {:builds {:dev {:source-paths ["src/cljs"]
                             :compiler {:main           "suurjako.main"
                                        :asset-path     "app/out"
                                        :output-dir     "target/dev/app/out"
                                        :output-to      "target/dev/app/suurjako.js"
                                        :optimizations  :none
                                        :preamble       ["reagent/react.js"]
                                        :pretty-print   true
                                        :source-map     true}}
                       :prod {:source-paths ["src/cljs"]
                              :compiler {:main           "suurjako.main"
                                         :output-dir     "target/prod"
                                         :output-to      "app/suurjako.js"
                                         :optimizations  :advanced
                                         :preamble       ["reagent/react.min.js"]
                                         :elide-asserts  true
                                         :pretty-print   false}}}}

  :aliases {"develop" ["cljsbuild" "auto" "dev"]
            "build" ["cljsbuild" "once" "prod"]})
