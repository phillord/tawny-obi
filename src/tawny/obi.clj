(ns tawny.obi
  (:refer-clojure :only [and filter instance?])
  (:require [tawny.owl]
            [tawny.read])
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
         #(.. % (getProperty) (isLabel))
         ;; get the annotations
         (.getAnnotations e
                          (tawny.owl/get-current-jontology))))))
     #"[ /]" "_"
     ))
  )




