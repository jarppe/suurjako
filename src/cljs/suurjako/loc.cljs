(ns suurjako.loc
  (:require [clojure.string :as s]))

(def terms {:view {:index {:title "Suurjako"}
                   :404 {:title "Sivua ei löydy"}}})

(defn loc [k]
  (or (get-in terms (-> k name (s/split #"\.") (->> (map keyword))))
      (do
        (js/console.log "MISSING TERM:" (pr-str k))
        (str "**MISSING" k "**"))))
