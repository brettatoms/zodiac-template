(defn make-test-db []
  (let [db-file (java.io.File/createTempFile "test-" ".db")]
    [(str "jdbc:sqlite:" (.getAbsolutePath db-file))
     #(.delete db-file)]))

(defn system-fixture [f]
  (let [[jdbc-url cleanup-fn] (make-test-db)
        config (test-system-config jdbc-url)
        _ (ig/load-namespaces config)
        system (ig/init config)]
    (try
      (let [[db app] (system-refs system)]
        (binding [*db* db
                  *app* app]
          (f)))
      (finally
        (ig/halt! system)
        (when cleanup-fn (cleanup-fn))))))
