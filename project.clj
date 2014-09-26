(defproject js-idiomatic-symbols "0.1.0"
  :description "Transform Clojure naming conventions to those of JavaScript."
  :url "https://github.com/nilern/js-idiomatic-symbols"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["target/clj/classes" "target/clj/test-classes"
                 "target/cljs/classes" "target/cljs/test-classes"
                 "src/clj"]

  :dependencies [[org.clojure/clojurescript "0.0-2356"]
                 [com.cemerick/clojurescript.test "0.3.1"]]

  :hooks [cljx.hooks]
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/clj/classes"
                   :rules :clj}

                  {:source-paths ["src/cljx"]
                   :output-path "target/cljs/classes"
                   :rules :cljs}

                  {:source-paths ["test/cljx"]
                   :output-path "target/clj/test-classes"
                   :rules :clj}

                  {:source-paths ["test/cljx"]
                   :output-path "target/cljs/test-classes"
                   :rules :cljs}]}

  :profiles {:dev {:source-paths ["src/cljx" "src/clj" "test/cljx"]
                   :plugins [[com.keminglabs/cljx "0.4.0"
                              :exclusions [org.clojure/clojure]]]
                   :repl-options {:nrepl-middleware
                                   [cljx.repl-middleware/wrap-cljx]}}})
