# clj-pgn

A Clojure parser for PGN ([Portable Game
Notation](https://en.wikipedia.org/wiki/Portable_Game_Notation)). It transforms
a PGN database into a vector of maps. It's in a very early stage.

## Installation

Add the necessary dependency to your project:

``` clojure
[clj-pgn "0.1.1-SNAPSHOT"]
```

## Usage

Load from a file:

```clojure
user> (require '[clj-pgn.core :as clj-pgn])
user> (clj-pgn/load-pgn "/path/to/file.pgn")
```

Load from a string:

```clojure
user> (require '[clj-pgn.core :as clj-pgn])
user> (clj-pgn/parse-pgn "1.e4 e5 1/2-1/2")
```

## Future features

- [ ] Support for variations (#1)
- [ ] Export [FEN](https://en.wikipedia.org/wiki/Forsyth–Edwards_Notation) notation for a
given move (#2)
- [ ] Improve documentation

## Example

Here's a small example of the parsing of a very short game: Frank Melville Teed
vs Eugene Delmar, New York, 1896:

```
[Event "New York"]
[Site "New York, NY USA"]
[Date "1896.??.??"]
[EventDate "?"]
[Round "?"]
[Result "1-0"]
[White "Frank Melville Teed"]
[Black "Eugene Delmar"]
[ECO "A80"]
[WhiteElo "?"]
[BlackElo "?"]
[PlyCount "15"]

1.d4 f5 2.Bg5 h6 3.Bf4 g5 4.Bg3 f4 5.e3 h5 6.Bd3 Rh6 7.Qxh5+
Rxh5 8.Bg6# 1-0
```

becomes:

```clojure
[{:headers
  [{:Event "\"New York\""}
   {:Site "\"New York, NY USA\""}
   {:Date "\"1896.??.??\""}
   {:EventDate "\"?\""}
   {:Round "\"?\""}
   {:Result "\"1-0\""}
   {:White "\"Frank Melville Teed\""}
   {:Black "\"Eugene Delmar\""}
   {:ECO "\"A80\""}
   {:WhiteElo "\"?\""}
   {:BlackElo "\"?\""}
   {:PlyCount "\"15\""}],
  :movelist
  [{:white {:move "d4"}, :black {:move "f5"}}
   {:white {:move "Bg5"}, :black {:move "h6"}}
   {:white {:move "Bf4"}, :black {:move "g5"}}
   {:white {:move "Bg3"}, :black {:move "f4"}}
   {:white {:move "e3"}, :black {:move "h5"}}
   {:white {:move "Bd3"}, :black {:move "Rh6"}}
   {:white {:move "Qxh5+"}, :black {:move "Rxh5"}}
   {:white {:move "Bg6#"}, :black nil}],
  :result [:WHITEWINS]}]
```

## License

Copyright © 2017 Marco Dalla Stella

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
