(ns clj-pgn.core
  (:require [clj-pgn.parser :as parser]))

(defn load-pgn [path]
  (parser path))
