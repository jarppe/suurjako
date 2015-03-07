(ns suurjako.view.show
  (:require [suurjako.view :as v]
            [suurjako.loc :refer [loc]]))

(defmethod v/render-view :show [{:keys [group-id]}]
  [:div
   [:h1 "show:" group-id]])
