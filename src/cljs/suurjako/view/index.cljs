(ns suurjako.view.index
  (:require [suurjako.view :as v]
            [suurjako.loc :refer [loc]]))

(defmethod v/render-view :index [_]
  (js/console.log "index:")
  [:div
   [:h1 (loc :view.index.title)]])
