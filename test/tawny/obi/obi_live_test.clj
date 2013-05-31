(ns
  tawny.obi.obi-live-test
  (:use [clojure.test])
  (:require
   [tawny.owl :as o]
   [tawny.reasoner :as r]
   [tawny.obi.obi-live])

  )

(defn ontology-reasoner-fixture [tests]
  ;; this should kill the reasoner factory and all reasoners which is the
  ;; safest, but slowest way to start.
  (r/reasoner-factory :hermit)

  ;; inject the ontology into the current namespace, which saves the
  ;; hassle of using with ontology every where. set this up each time in case
  ;; pizzaontology has been re-evaled
  (o/ontology-to-namespace tawny.obi.obi-live/obi-live)
  (binding [r/*reasoner-progress-monitor*
            (atom r/reasoner-progress-monitor-silent)]
    (tests)))

(use-fixtures :once ontology-reasoner-fixture)




(deftest ^:heavy consistent
  (is (r/consistent?))
  (is (r/coherent?)))


(deftest profile
  (is (p/inprofile?
       (o/get-current-ontology)
       p/profile-owl2dl)))
