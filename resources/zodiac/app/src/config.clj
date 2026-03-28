(ns {{top/ns}}.{{main/ns}}.config
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod aero/reader 'ig/ref
  [_opts _tag value]
  (ig/ref value))

(defn read-config
  "Read system.edn from the classpath using aero for profile support."
  [resource-path opts]
  (some-> (io/resource resource-path)
          (aero/read-config opts)))
