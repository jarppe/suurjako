(ns suurjako.view.not-found
  (:require [suurjako.view :refer [render-view]]
            [suurjako.loc :refer [loc]]))

(defmethod render-view :default [_]
  [:div
   [:h1 (loc :view.not-found.title)]])
