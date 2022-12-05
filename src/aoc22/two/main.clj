(ns aoc22.two.main
  (:require
   [clojure.string :as str]))

(declare end-strategy)

(def input (-> "src/aoc22/two/input" slurp str/split-lines))

(def letter->rps
  {"A" :rock     "X" :rock
   "B" :paper    "Y" :paper
   "C" :scissors "Z" :scissors})

;; Rock > Scissors 1 > 3
;; Paper > Rock 2 > 1
;; Scissors > Paper 3 > 2
(defn play-mapping
  ([_ player] (play-mapping player))
  ([player]
   (player {:rock 1
            :paper 2
            :scissors 3})))

(defn win-round [enemy]
  (case enemy
    :rock :paper
    :paper :scissors
    :scissors :rock))

(defn lose-round [enemy]
  (case enemy
    :rock :scissors
    :paper :rock
    :scissors :paper))

(defn end-mapping [enemy player]
  (case (end-strategy enemy player)
    :won (-> enemy win-round play-mapping)
    :lost (-> enemy lose-round play-mapping)
    (play-mapping enemy)))


(defn play-strategy [enemy player]
  (let [win-condition (or (and (= player :rock) (= enemy :scissors))
                          (and (= player :paper) (= enemy :rock))
                          (and (= player :scissors) (= enemy :paper)))]
    (cond
      win-condition :won
      (= enemy player) :draw
      :else :lost)))

(defn end-strategy [_ player]
  (player {:rock :lost
           :paper :draw
           :scissors :won}))

(defn calculate-points [round strategy point-mapping]
  (let [[enemy player] (mapv letter->rps (str/split round #" "))
        player-points (point-mapping enemy player)]
    (case (strategy enemy player)
      :won  (+ player-points 6)
      :draw (+ player-points 3)
      :lost player-points)))

(comment
  ((comp play-mapping letter->rps) "A")

  (->> input
       (map #(calculate-points % play-strategy play-mapping))
       (reduce +))

  (->> input
       (map #(calculate-points % end-strategy end-mapping))
       (reduce +))
  )
