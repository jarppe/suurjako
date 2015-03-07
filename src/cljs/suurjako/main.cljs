(ns suurjako.main
   (:require [reagent.core :as reagent]
             [suurjako.routing :as r]
             [suurjako.view :as v]
             [suurjako.views]))

(defn main-view []
  (v/render-view @r/current-route))

(defn init! []
  (r/init!)
  (reagent/render [main-view] (js/document.getElementById "app")))

(init!)
