(ns clj-pgn.parser
  (:require [instaparse.core :as i]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def pgn-grammar
  (i/parser
   (clojure.java.io/resource "pgn.bnf")))

(defn pgn-map [& elements]
  (let [[headers movelist result] elements]
    {:headers headers
     :movelist movelist
     :result result}))

(defn move-map
  [& moves]
  (let [[white black] moves]
    {:white white
     :black black}))

(defn halfmove-map [& vals]
  (reduce
   (fn [a v]
     (cond
       (string? v) (update a :move str v)
       (map? v) (merge a v)
       :else (assoc a :move v)))
   {:move nil}
   vals))

(defn comment-strig [& vals]
  (let [s (apply str vals)]
    {:comment s}))

(def pgn-transformations
  {:PGNDB vector
   :PGN pgn-map
   :HEADERS vector
   :HEADER hash-map
   :TAG keyword
   :VALUE str
   :MOVELIST vector
   :MOVE move-map
   :HALFMOVE halfmove-map
   :COMMENT comment-strig
   :RESULT identity})

(defn parse [pgn]
  (->> pgn
       (i/parse pgn-grammar)
       (i/transform pgn-transformations)))
