(ns suurjako.view.show
  (:require [suurjako.view :as v]
            [suurjako.routing :as r]
            [suurjako.groups :as g]
            [suurjako.loc :refer [loc]]))

(defmethod v/render-view :show [{:keys [group-index] :as d}]
  [:div
   [:h1 "show:" group-index]])

(defmethod r/route-change :show [route]
  (reset! r/current-route {:id :show, :group-index (:group-index route)}))
