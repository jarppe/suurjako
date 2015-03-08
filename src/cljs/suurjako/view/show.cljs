(ns suurjako.view.show
  (:require [suurjako.view :as v]
            [suurjako.routing :as r]
            [suurjako.groups :as g]
            [suurjako.loc :refer [loc]]))

(defn to-index [e]
  (.preventDefault e)
  (r/update-uri! :index))

(defmethod v/render-view :show [_]
  [:div#show
   [:a {:on-click to-index} [:i.fa.fa-smile-o.fa-2x]]
   [:div (loc :smile)]])
