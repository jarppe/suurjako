(ns suurjako.routing
   (:require [reagent.core :as reagent :refer [atom]]
             [domkm.silk :as silk]))

(def routes (silk/routes [[:index [[]]]
                          [:reveal [["reveal"]]]
                          [:show [["show" :group-index]]]]))

(defonce current-route (atom nil))

(defmulti route-change :domkm.silk/name)

(defmethod route-change :default [route]
  (reset! current-route {:id (:domkm.silk/name route)}))

(defn- update-route! [_]
  (let [uri (subs js/location.hash 1)]
    (js/console.log "update-route!" uri (pr-str (silk/arrive routes uri)))
    (route-change (or (silk/arrive routes uri)
                      {:domkm.silk/name :default}))))

(defn init! []
  (set! js/window.onhashchange update-route!)
  (update-route! nil))

(defn href [k & [params]]
  (try
    (str "#" (silk/depart routes k params))
    (catch js/Error e
      (js/console.log "Couldn't create href: k:" (pr-str k) "params:" (pr-str params))
      (js/console.log "Error:" e)
      nil)))

(defn update-uri! [k & [params]]
  (set! js/window.location.hash (href k params)))
