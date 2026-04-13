(ns zodiac.app-test
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is testing]]
            [org.corfield.new])) ; for the test-template functionality

(deftest valid-template-test
  (testing "template.edn is valid"
    (let [template-edn (-> (io/resource "zodiac/app/template.edn")
                           slurp
                           edn/read-string)]
      (is (map? template-edn))
      (is (contains? template-edn :data-fn))
      (is (contains? template-edn :template-fn)))))
