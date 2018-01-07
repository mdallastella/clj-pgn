(ns clj-pgn.core
  (:require [clj-pgn.parser :as parser]))

(defn load-pgn [path]
  (-> (slurp path)
      (parser/parse)))
(defn parse-pgn [s]
  (parser/parse s))
