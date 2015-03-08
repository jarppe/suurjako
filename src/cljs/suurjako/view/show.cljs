(ns suurjako.view.show
  (:require [suurjako.view :as v]
            [suurjako.routing :as r]
            [suurjako.groups :as g]
            [suurjako.loc :refer [loc]]))

(defmethod v/render-view :show [_]
  [:div#show
   [:i.fa.fa-smile-o.fa-2x]
   [:div (loc :smile)]])
