(ns aoc22.two.main
  (:require
    [clojure.string :as str]))

(def input
  (-> (slurp "src/aoc22/two/input")
      (str/split #"\n")))

(def point-mapping {"A" 1 "B" 2 "C" 3
                    "X" 1 "Y" 2 "Z" 3})

(defn calculate-points [round]
  (let [[enemy player] (mapv point-mapping (str/split round #" "))]
    (condp apply [enemy player]
      = (+ player 3)
      < (+ player 6)
      > player)))

(comment
  (reduce + (map calculate-points input))
  )
