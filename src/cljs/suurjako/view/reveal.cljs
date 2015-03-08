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
      (r/update-uri! :show {:group-index "0"}))))

(defmethod v/render-view :reveal [_]
  (let [{:keys [group-index participant-index]} @data
        groups       @g/groups
        group        (nth groups group-index)
        participant  (nth group participant-index)]
    [:div#reveal.container
     [:div.row.header
      [:div.col-md-1]
      [:div.col-md-10.text-center (str (loc :group) " " (inc group-index))]
      [:div.col-md-1 [:a {:href "#" :on-click forward} [:i.fa.fa-arrow-circle-o-right]]]]
     [:div.row
      [:div.col-md-12.text-center.participant [:a.silent {:href "#" :on-click forward} (str participant)]]]
     [:div.row.participants
      (map-indexed (fn [i p]
                     (let [visible? (<= i participant-index)
                           active? (= i participant-index)
                           clazz (cond
                                   active?   "active"
                                   visible?  nil
                                   :else     "grayed")]
                       [:span.ball {:key p, :class clazz}
                        (if visible? (str p))]))
                   group)]]))

(defmethod r/route-change :reveal [_]
  (reset! data {:group-index        0
                :participant-index  0})
  (reset! r/current-route {:id :reveal}))
