(ns aoc22.one.main
  (:require [clojure.string :as str]))

(def input
  (str/split (slurp "src/aoc22/one/input") #"\n\n"))

(defn get-calories [coll]
  (->> (str/split coll #"\n")
       (map #(Integer/parseInt %))))

(defn get-smurf-calories [coll]
 (map #(reduce + (get-calories %)) coll))

(defn fatest-smurf [coll]
  (apply max (get-smurf-calories coll)))

(defn fatest-n-smurfs [n coll]
  (->> (get-smurf-calories coll)
       (sort >)
       (take n)
       (reduce +)))

(comment
  (fatest-smurf input)
  (fatest-n-smurfs 3 input)
  )
