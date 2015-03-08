(ns suurjako.view.reveal
  (:require [reagent.core :as reagent :refer [atom]]
            [suurjako.view :as v]
            [suurjako.routing :as r]
            [suurjako.loc :refer [loc]]
            [suurjako.groups :as g]))

(defonce data (atom nil))

(defmethod v/render-view :reveal [_]
  (let [{:keys [group-index participant-index]} @data
        groups       @g/groups
        group        (nth groups group-index)
        participant  (nth group participant-index)]
    [:div
     [:h1 (str "reveal: " participant " [" participant-index "/" group-index "]")]
     [:button.btn.btn-default {:disabled (not (< current group-count))
                               :on-click (fn [_] (next-participant))}
      "next >"]]))

(defmethod r/route-change :reveal [_]
  (reset! data {:group-index        1
                :participant-index  0})
  (reset! r/current-route {:id :reveal}))
