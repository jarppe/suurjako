(ns suurjako.main
   (:require [reagent.core :as reagent :refer [atom]]))

(defonce app (atom {:message "fofo"
                    :counter 0}))

(defn main-view []
  [:div
   [:h3 "Here we go!"]
   [:div
    [:span "Clicks:" (:counter @app)]]
   [:button {:on-click (fn [_] (swap! app update-in [:counter] inc))} "Paina mua!"]])

(reagent/render [main-view] (js/document.getElementById "app"))
