(ns suurjako.main
   (:require [reagent.core :as reagent :refer [atom]]
             [domkm.silk :as silk]))

(defonce current-route (atom nil))

(defmulti route-change :domkm.silk/name)

(defmethod route-change :default [route]
  (js/console.log "route id:" (pr-str (:domkm.silk/name route)))
  (reset! current-route {:id (:domkm.silk/name route)}))

(def routes (silk/routes [[:index [[]]]
                          [:reveal [["reveal"]]]
                          [:show [["show" :group-id]]]]))

(defn update-route! [_]
  (let [uri (subs js/location.hash 1)
        r (or (silk/arrive routes uri) {:domkm.silk/name :default})]
    (js/console.log "r:" (pr-str r))
    (route-change r)))

(set! js/window.onhashchange update-route!)
(update-route! nil)

(defmulti render-view :id)

(defmethod render-view :default [_]
  [:div
   [:h1 "Sivua ei loydy"]])

(defmethod render-view :index [_]
  [:div
   [:h1 "index"]])

(defmethod render-view :reveal [_]
  [:div
   [:h1 "reveal"]])

(defmethod route-change :show [{:keys [group-id]}]
  (reset! current-route {:id        :show
                         :group-id  group-id}))

(defmethod render-view :show [{:keys [group-id]}]
  [:div
   [:h1 "show:" group-id]])

(defn main-view []
  (js/console.log "@current-route:" (pr-str @current-route))
  (render-view @current-route))

(reagent/render [main-view] (js/document.getElementById "app"))
