(ns suurjako.groups
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce groups (atom nil))

(defn make-participants [participant-count]
  (shuffle (range 1 (inc participant-count))))

(defn group-size [participants-left groups-left]
  (-> (/ (double participants-left) (double groups-left))
      Math/ceil
      int))

(defn make-groups [participants group-count]
  (loop [groups []
         participants participants
         group-count group-count]
    (if (zero? group-count)
      groups
      (let [size (group-size (count participants) group-count)]
        (recur (conj groups (vec (take size participants)))
               (drop size participants)
               (dec group-count))))))

(defn init! [participant-count group-count]
  (reset! groups (make-groups (make-participants participant-count)
                              group-count))
  (js/console.log "groups:" (pr-str @groups)))

