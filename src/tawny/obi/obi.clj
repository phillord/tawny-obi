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


(ns tawny.obi
  (:refer-clojure :only [fn and filter instance? first filter])
  (:require [tawny.owl]
            [tawny.read]
            [tawny.memorise])
  (:import (java.io File)
           (org.semanticweb.owlapi.model IRI OWLNamedObject)))


(tawny.read/defread obi
  ;; something that the OWL API can interpret. This includes a stream, so
  ;; it's totally generic.
  :location (IRI/create (clojure.java.io/resource "obi.owl"))
  ;; the prefix that you want to use in this case
  :prefix "obo"
  ;; normally only things from this IRI will be imported
  :iri "http://purl.obolibrary.org/obo/obi.owl"
  :viri "http://purl.obolibrary.org/obo/obi/2012-07-01/obi.owl"
  ;; but OBO ontologies are wierd, so pass in a filter function
  :filter
  (fn [e]
    (and (instance? OWLNamedObject e)
         (.startsWith
          (.toString (.getIRI e))
          "http://purl.obolibrary.org/obo/OBI"
          ))
    )
  :transform
  ;; fix the space problem
  (fn [e]
    (clojure.string/replace
     ;; with luck these will always be literals, so we can do this
     ;; although not true in general
     (.getLiteral 
      ;; get the value of the annotation
      (.getValue
       (first
        ;; filter for annotations which are labels
        ;; is lazy, so doesn't eval all
        (filter
         #(.isLabel (.getProperty %))
         ;; get the annotations
         (.getAnnotations e
                          (tawny.owl/get-current-ontology))))))
     #"[ /]" "_"
     ))
  )




;; remember mappings between IRIs and atom names that we have seen previously. 
;; These may change, because the IRI uses a semantics-free identifier, while
;; tawny converts everything to a readable symbol based on the human-readable
;; label. 

;; Mappings between a name and a specific concept that are no longer present
;; (probably because the label has changed), will still be created, but a
;; warning will be emited both when the remember form is run below and also
;; when these symbols are used. Old mappings can be individually removed
;; manually from the obi_memorise.clj file. All old mappings can be removed by
;; deleting the file and saving it again. 

;; Before this is run for the first time, the mapping must be saved using the
;; memorise function below.
(tawny.memorise/remember "./src/tawny/obi_memorise.clj")



;; Memorise mappings between IRIs and atom names. 

;; In this case, I have downloaded the obi.owl. This form should be run before
;; this file is updated. If, instead of storing a snapshot of obi.owl a URL
;; was used, it would probably make more sense to run this at every load.

;;(tawny.memorise/memorise "./src/tawny/obi_memorise.clj")



