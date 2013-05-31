;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2012, 2013 Newcastle University

;; This program is free software: you can redistribute it and/or modify it under
;; the terms of the Lesser GNU General Public License as published by the Free
;; Software Foundation, either version 3 of the License, or (at your option) any
;; later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.

;; You should have received a copy of the Lesser GNU General Public License
;; along with this program.  If not, see http://www.gnu.org/licenses/.


(ns tawny.obi.obi-test
  (:use [clojure.test])
  (:require [tawny.owl :as o]
            [tawny.profile :as p]
            [tawny.reasoner :as r]
            [tawny.obi.obi :as obi]))

(defn ontology-reasoner-fixture [tests]
  ;; this should kill the reasoner factory and all reasoners which is the
  ;; safest, but slowest way to start.
  (r/reasoner-factory :hermit)

  ;; inject the pizzaontology into the current namespace, which saves the
  ;; hassle of using with ontology every where. set this up each time in case
  ;; pizzaontology has been re-evaled
  (o/ontology-to-namespace obi/obi)
  (binding [r/*reasoner-progress-monitor*
            (atom r/reasoner-progress-monitor-silent)]
    (tests)))

(use-fixtures :once ontology-reasoner-fixture)

;; Test whether we can save stuff
(deftest obiread
  (is
   (do (tawny.owl/save-ontology "obi.omn" :omn)
       (tawny.owl/save-ontology "obi.owl" :owl)
       true)))

(deftest ^:heavy obicoherency
  (is (r/coherent?))
  (is (r/consistent?)))

(deftest profile
  (is (p/inprofile?
       (o/get-current-ontology)
       p/profile-owl2dl)))

(deftest emission
  (is
   (o/subclass? obi/excitation_function
                obi/light_emission_function)))

(deftest enzyme
  (is
   (r/isubclass? obi/enzyme
                 obi/restriction_enzyme)))
