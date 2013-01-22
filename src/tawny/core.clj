
(ns tawny.core
  (:use [tawny.owl])
  (:require [tawny.obi])
  (:gen-class)
  )


(defn -main [&args]
  (with-ontology tawny.obi/obi
    (save-ontology "obi.omn" :omn)))
