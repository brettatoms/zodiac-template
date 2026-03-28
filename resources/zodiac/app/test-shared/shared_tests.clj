;; --- Data Layer Tests ---

(deftest create-and-list-todos-test
  (testing "create a todo and list todos"
    (todo/create-todo! *db* "Test todo")
    (let [todos (todo/list-todos *db*)]
      (is (some #(= "Test todo" (:todo/title %)) todos)))))

(deftest delete-todo-test
  (testing "delete removes a todo"
    (todo/create-todo! *db* "Delete me")
    (let [id (->> (todo/list-todos *db*)
                  (filter #(= "Delete me" (:todo/title %)))
                  first
                  :todo/id)]
      (todo/delete-todo! *db* id)
      (is (not (some #(= id (:todo/id %)) (todo/list-todos *db*)))))))
