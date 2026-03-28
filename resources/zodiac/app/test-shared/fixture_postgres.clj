(defn system-fixture [f]
  (let [config (test-system-config test-jdbc-url)
        _ (ig/load-namespaces config)
        system (ig/init config)]
    (try
      (let [[db app] (system-refs system)]
        (binding [*db* db
                  *app* app]
          (f)))
      (finally
        (ig/halt! system)))))
