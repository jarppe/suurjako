(ns suurjako.view.reveal
  (:require [reagent.core :as reagent :refer [atom]]
            [suurjako.view :as v]
            [suurjako.routing :as r]
            [suurjako.loc :refer [loc]]
            [suurjako.groups :as g]))

(defonce data (atom nil))

(defn forward [e]
  (.preventDefault e)
  (let [{:keys [group-index participant-index]} @data
        groups  @g/groups
        group   (nth groups group-index)]
    (cond
      (< (inc participant-index) (count group))
      (swap! data update-in [:participant-index] inc)

      (< (inc group-index) (count groups))
      (reset! data {:group-index       (inc group-index)
                    :participant-index 0})

      :else
      (r/update-uri! :show))))

(defn back-to-index [e]
  (.preventDefault e)
  (r/update-uri! :index))

(defmethod v/render-view :reveal [_]
  (let [{:keys [group-index participant-index]} @data
        groups       @g/groups
        group        (nth groups group-index)
        participant  (nth group participant-index)]
    [:div#reveal
     [:div.header
      [:div.l [:a {:on-click back-to-index} [:i.fa.fa-close]]]
      [:div.c (str (loc :group) " " (inc group-index))]
      [:div.r [:a {:on-click forward} [:i.fa.fa-arrow-circle-o-right]]]]
     [:div
      [:div.col-md-12.text-center.participant [:a.silent {:href "#" :on-click forward} (str participant)]]]
     [:div.participants
      (map-indexed (fn [i p]
                     [:span.ball {:key p
                                  :class (if (<= i participant-index) "revealed")}
                      (str p)])
                   group)]]))

(defmethod r/route-change :reveal [_]
  (reset! data {:group-index        0
                :participant-index  0})
  (reset! r/current-route {:id :reveal}))
