(ns aoc22.fourth.main
  (:require
   [clojure.set :refer [subset?]]
   [clojure.string :refer [split-lines split]]))

(def input (-> "src/aoc22/fourth/input" slurp split-lines))

(defn strs-to-ints [strs]
  (map #(Integer/parseInt %) strs))

(defn inclusive-range [[start end]]
  (set (range start (inc end))))

(defn fully-contains? [line]
  (let [line-ranges (split line #",")
        parsed-ranges (map #(strs-to-ints (split % #"-")) line-ranges)
        ranges (map inclusive-range parsed-ranges)]
    (or (apply subset? ranges)
        (apply subset? (reverse ranges)))))

(comment
  (->> input
       (map fully-contains?)
       (remove false?)
       count)
  )
