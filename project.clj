(defproject lein-watcher "0.3.0"
  :description "Watchs files in a directory and executes them on change dumping the 
               results into a new file in a target directory."
  :url "http://github.com/immutablelabs/lein-watcher"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [filevents "0.1.0"]
                 [org.clojure/core.async "0.1.278.0-76b25b-alpha"]]
  :lein-watcher {:input-dir "test/in" 
               :input-pattern "*.clj"
               :output-dir "test/out"
               :output-type "svg"}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true)
