(ns zodiac.app
  (:require [clojure.java.io :as io]
            [zodiac.app.rules :as rules]))

(defn data-fn
  "Compute substitution data based on :db and :structure options."
  [data]
  (let [db (or (:db data) :sqlite)
        main (:main data)
        test-body (slurp (io/resource "zodiac/app/test-shared/shared_tests.clj"))
        test-fixture (slurp (io/resource
                             (case db
                               :sqlite "zodiac/app/test-shared/fixture_sqlite.clj"
                               :postgres "zodiac/app/test-shared/fixture_postgres.clj")))]
    (merge
     {:db (name db)
      :test-body test-body
      :test-fixture test-fixture
      :test-jdbc-url-def ""}
     (case db
       :sqlite
       {:jdbc-driver-dep "org.xerial/sqlite-jdbc {:mvn/version \"3.49.1.0\"}"
        :jdbc-url (format "\"jdbc:sqlite:%s.db\"" main)
        :db-comment "SQLite database"
        :create-table-sql "create table if not exists todo (\n  id integer primary key autoincrement,\n  title text not null,\n  created_at text not null default (datetime('now'))\n)"
        :insert-returning ""}

       :postgres
       {:jdbc-driver-dep "org.postgresql/postgresql {:mvn/version \"42.7.5\"}"
        :jdbc-url (format "\"jdbc:postgresql://localhost:5432/%s\"" main)
        :db-comment "PostgreSQL database"
        :create-table-sql "create table if not exists todo (\n  id serial primary key,\n  title text not null,\n  created_at timestamptz not null default now()\n)"
        :insert-returning " returning *"
        :test-jdbc-url-def (format "(def test-jdbc-url \"jdbc:postgresql://localhost:5432/%s_test\")" main)}))))

(defn template-fn
  "Select transform rules based on :structure option."
  [edn data]
  (let [structure (or (:structure data) :flat)]
    (println (str "Creating zodiac app with :db "
                  (or (:db data) :sqlite)
                  " :structure " structure))
    (assoc edn :transform
           (case structure
             :flat (rules/flat data)
             :polylith (rules/polylith data)))))
