(ns clj-pgn.parser
  (:require [instaparse.core :as i]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def pgn-grammar
  (i/parser
   (clojure.java.io/resource "pgn.bnf")))

(def pgn-transformations
  {:HEADERS (fn [& vs] {:headers (apply vector vs)})
   :HEADER hash-map
   :TAG (comp keyword str)
   :VALUE str
   :HALFMOVE str})

(defn parse [pgn]
  (->> pgn
       (i/parse pgn-grammar)
       (i/transform pgn-transformations)))
