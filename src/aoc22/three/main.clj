(ns aoc22.three.main
  (:require
   [clojure.set :refer [intersection]]
   [clojure.string :refer [split-lines]]))

(def input (-> "src/aoc22/three/input" slurp split-lines))

(defn split-in-half [s]
  (split-at (/ (count s) 2) s))

(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

(def alphabet
  (merge
   (zipmap (char-range \a \z) (range 1 27))
   (zipmap (char-range \A \Z) (range 27 53))))

(defn rucksack-duplicate [s]
  (let [halves (map set (split-in-half s))]
    (first (apply intersection halves))))

(defn find-badge [group]
  (first (apply intersection (map #(set (map char %)) group))))

(comment
  (->> input
       (map (comp alphabet rucksack-duplicate))
       (reduce +))

  (->> input
       (partition 3)
       (map (comp alphabet find-badge))
       (reduce +))
  )
