(ns clj-pgn.parser
  (:require [instaparse.core :as i]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def pgn-grammar
  (i/parser
   (clojure.java.io/resource "pgn.bnf")))

(defn parse-halfmove [s & r]
  (println s r)
  s)

(def pgn-transformations nil)

(defn parse [pgn]
  (->> pgn
       (i/parse pgn-grammar)))
;; (i/transform pgn-transformations)
