(ns {{top/ns}}.app.server-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [integrant.core :as ig]
            [zodiac.core :as z]
            [zodiac.ext.sql :as z.sql]
            [{{top/ns}}.app.server :as server]
            [{{top/ns}}.app.routes :as routes]
            [{{top/ns}}.database.interface :as todo]))

;; --- Test System ---

(def ^:dynamic *db* nil)
(def ^:dynamic *app* nil)
{{test-jdbc-url-def}}

(defn test-system-config [jdbc-url]
  {:{{top/ns}}.app.server/zodiac-sql
   {:spec {:jdbcUrl jdbc-url}
    :context-key :db}

   :{{top/ns}}.app.server/zodiac
   {:extensions [(ig/ref :{{top/ns}}.app.server/zodiac-sql)]
    :routes #'routes/routes
    :start-server? false}

   :{{top/ns}}.app.server/migrate
   {:zodiac (ig/ref :{{top/ns}}.app.server/zodiac)}})

(defn system-refs [system]
  [(-> system :{{top/ns}}.app.server/zodiac ::z.sql/db)
   (-> system :{{top/ns}}.app.server/zodiac ::z/app)])

{{test-fixture}}

(use-fixtures :once system-fixture)

{{test-body}}
