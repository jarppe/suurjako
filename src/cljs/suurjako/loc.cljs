(ns suurjako.loc
  (:require [clojure.string :as s]))

(def terms {:view {:index {:title                "Suurjako"
                           :participant-count    "Osallistujien määrä:"
                           :group-count          "Ryhmien määrä:"
                           :start                "Aloita"}
                   :not-found {:title "Sivua ei löydy"}}
            :group "Ryhmä"})

(defn loc [k]
  (or (get-in terms (if (keyword? k)
                      (-> k name (s/split #"\.") (->> (map keyword)))
                      k))
      (do
        (js/console.log "MISSING TERM:" (pr-str k))
        (str "**MISSING" k "**"))))
