(ns user
  (:require [integrant.core :as ig]
            [{{top/ns}}.{{main/ns}} :as {{main/ns}}]))

(defonce ^:dynamic *system* nil)

(defn go
  ([] (go :local))
  ([profile]
   (let [sys ({{main/ns}}/start profile)]
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
