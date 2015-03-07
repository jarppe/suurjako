(ns suurjako.view.reveal
  (:require [suurjako.view :as v]
            [suurjako.routing :as r]
            [suurjako.loc :refer [loc]]))

(defmethod v/render-view :reveal [_]
  [:div
   [:h1 "reveal"]])

(defmethod r/route-change :show [{:keys [group-id]}]
  (reset! r/current-route {:id        :show
                           :group-id  group-id}))
