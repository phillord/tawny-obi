
(ns tawny.core
  (:use [tawny.owl])
  (:require [tawny.obi])
  )


(with-ontology tawny.obi/obi
  (save-ontology "obi.omn" :omn))