

(ns tawny.obi.obi-live
  (:refer-clojure :only [fn and filter instance? first filter])
  (:use [tawny.read])
  (:import  (org.semanticweb.owlapi.model IRI OWLNamedObject))
  )


;; (tawny.read/defread obi-live
;;   ;; something that the OWL API can interpret. This includes a stream, so
;;   ;; it's totally generic.

;;   :location (IRI/create "http://purl.obolibrary.org/obo/obi.owl")
;;   ;; the prefix that you want to use in this case
;;   :prefix "obo"
;;   ;; normally only things from this IRI will be imported
;;   :iri "http://purl.obolibrary.org/obo/obi.owl"
;;   ;; but OBO ontologies are wierd, so pass in a filter function
;;   :filter
;;   (fn [e]
;;     (and (instance? OWLNamedObject e)
;;          (.startsWith
;;           (.toString (.getIRI e))
;;           "http://purl.obolibrary.org/obo/OBI"
;;           ))
;;     )
;;   :transform
;;   ;; fix the space problem
;;   (fn [e]
;;     (clojure.string/replace
;;      ;; with luck these will always be literals, so we can do this
;;      ;; although not true in general
;;      (.getLiteral 
;;       ;; get the value of the annotation
;;       (.getValue
;;        (first
;;         ;; filter for annotations which are labels
;;         ;; is lazy, so doesn't eval all
;;         (filter
;;          #(.isLabel (.getProperty %))
;;          ;; get the annotations
;;          (.getAnnotations e
;;                           (tawny.owl/get-current-ontology))))))
;;      #"[ /]" "_"
;;      ))
;;   )


