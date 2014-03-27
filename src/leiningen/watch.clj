(ns leiningen.watcher
  (:require [filevents.core :as filevents])
  (:use [clojure.core.async :only [chan <! <!! put! go]]))
;; Code is based off of hiccup watch
;;  https://github.com/twashing/hiccup-watch


(defn output [from-path to-path]
  (spit to-path ((load-file from-path))))

(def starting-string "Lein-watch starting")
(def configuration-failure  "ERROR: both :input-dir and :output-dir not defined in 'project.clj' Exiting")

(defn- watch-chan [target & kinds]
  (let [output-chan (chan 1)]
    (filevents/watch (fn [kind file]
                       (if (kind (set kinds))
                         (put! output-chan file)))
                     target)
    output-chan))

(defn watcher [project & args]
  (let [{input-dir :input-dir 
         output-dir :output-dir
         output-type :output-type} (project :lein-watch)]
    (if-not (and input-dir output-dir)
      (println configuration-failure)
      (do (println starting-string)
          (println (str "Watching: " \" input-dir \"))
          (let [changes-chan (watch-chan input-dir 
                                         :modified :created)]
            (while true
              (let [file (<!! changes-chan)
                    filename (.getName file)]
                (when (re-find #"\.clj$" filename)
                  (let [sourcepath (.getPath file)
                        newname (str output-dir
                                     java.io.File/separator
                                     filename "." output-type)]
                    (println "Outputing" sourcepath "to" newname)
                    (output sourcepath newname))))))))))
