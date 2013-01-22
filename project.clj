(defproject uk.org.russet/tawny-obi "0.1.0-SNAPSHOT"
  :description "FIXME: write description"

  
  :license {:name "LGPL"
            :url "http://www.gnu.org/licenses/lgpl-3.0.txt"
            :distribution :repo}

  :dependencies [[org.clojure/clojure "1.4.0"]
                 [uk.org.russet/tawny-owl "0.8-SNAPSHOT"]
                 
                 [net.sourceforge.owlapi/owlapi-api "3.4.2"]
                 
                 ]
  ;; this is a problem -- I have this namespace elsewhere
  :main tawny.core)

