(ns {{ns-prefix}}.server-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [integrant.core :as ig]
            [zodiac.core :as z]
            [zodiac.ext.sql :as z.sql]
            [{{ns-prefix}}.server :as server]
            [{{ns-prefix}}.routes :as routes]
            [{{top/ns}}.database.interface :as todo]))

;; --- Test System ---

(def ^:dynamic *db* nil)
(def ^:dynamic *app* nil)
{{test-jdbc-url-def}}

(defn test-system-config [jdbc-url]
  {:{{ns-prefix}}.server/zodiac-sql
   {:spec {:jdbcUrl jdbc-url}
    :context-key :db}

   :{{ns-prefix}}.server/zodiac
   {:extensions [(ig/ref :{{ns-prefix}}.server/zodiac-sql)]
    :routes #'routes/routes
    :start-server? false}

   :{{ns-prefix}}.server/migrate
   {:zodiac (ig/ref :{{ns-prefix}}.server/zodiac)}})

(defn system-refs [system]
  [(-> system :{{ns-prefix}}.server/zodiac ::z.sql/db)
   (-> system :{{ns-prefix}}.server/zodiac ::z/app)])

{{test-fixture}}

(use-fixtures :once system-fixture)

{{test-body}}
