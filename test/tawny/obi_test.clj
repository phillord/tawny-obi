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


(ns tawny.obi-test
  (:use [clojure.test])
  (:require [tawny.owl] [tawny.obi]))


(deftest obiread 
  (tawny.owl/with-ontology tawny.obi/obi
    (tawny.owl/save-ontology "obi.omn" :omn)
    (tawny.owl/save-ontology "obi.owl" :owl)))
