(defproject uk.org.russet/tawny-obi "0.1.0-SNAPSHOT"
  :description "FIXME: write description"

  
  :license {:name "LGPL"
            :url "http://www.gnu.org/licenses/lgpl-3.0.txt"
            :distribution :repo}

  :dependencies [[org.clojure/clojure "1.5.0"]
                 [uk.org.russet/tawny-owl "0.12-SNAPSHOT"]
                 
                 [net.sourceforge.owlapi/owlapi-api "3.4.4"]

                 
                 ]
  ;; this is a problem -- I have this namespace elsewhere
  :main tawny.core)

