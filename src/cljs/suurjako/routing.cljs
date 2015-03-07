(ns suurjako.routing
   (:require [reagent.core :as reagent :refer [atom]]
             [domkm.silk :as silk]))

(def routes (silk/routes [[:index [[]]]
                          [:reveal [["reveal"]]]
                          [:show [["show" :group-id]]]]))

(defonce current-route (atom nil))

(defmulti route-change :domkm.silk/name)

(defmethod route-change :default [route]
  (reset! current-route {:id (:domkm.silk/name route)}))

(defn- update-route! [_]
  (let [uri (subs js/location.hash 1)]
    (route-change (or (silk/arrive routes uri)
                      {:domkm.silk/name :default}))))

(defn init! []
  (set! js/window.onhashchange update-route!)
  (update-route! nil))
