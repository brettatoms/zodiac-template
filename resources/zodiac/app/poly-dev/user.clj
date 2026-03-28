(ns user
  (:require [integrant.core :as ig]
            [{{top/ns}}.app.core :as core]))

(defonce ^:dynamic *system* nil)

(defn go
  ([] (go :local))
  ([profile]
   (let [sys (core/start profile)]
     (alter-var-root #'*system* (constantly sys))
     :started)))

(defn stop []
  (when *system*
    (ig/halt! *system*)
    (alter-var-root #'*system* (constantly nil))
    :stopped))

(defn restart []
  (stop)
  (go))
