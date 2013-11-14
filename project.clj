(defproject uk.org.russet/tawny-obi "0.1.0-SNAPSHOT"
  :description "FIXME: write description"


  :license {:name "LGPL"
            :url "http://www.gnu.org/licenses/lgpl-3.0.txt"
            :distribution :repo}

  :dependencies [[uk.org.russet/tawny-owl "1.0"]]


  :test-selectors {:default (constantly true)
                   :light (complement :heavy)}


  ;; this is a problem -- I have this namespace elsewhere
  :main tawny.core)
