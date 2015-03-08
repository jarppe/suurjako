(ns suurjako.view.index
  (:require [clojure.string :as s]
            [reagent.core :as reagent :refer [atom]]
            [suurjako.view :as v]
            [suurjako.routing :as r]
            [suurjako.groups :refer [groups] :as g]
            [suurjako.loc :refer [loc]]))

(def valid? (comp (partial re-matches #"\d+") s/trim))

(defn all-valid? [{:keys [participant-count group-count]}]
  (and (not (:error participant-count))
       (not (:error group-count))
       (>= (:value participant-count) (:value group-count))))

(defn input [form-data id]
  (let [data (id @form-data)]
    [:div.form-group {:class (if (:error data) "has-error")}
     [:label {:for (str id)} (loc [:view :index id])]
     [:input.form-control {:id (str id)
                           :type "number"
                           :value (:value data)
                           :on-change (fn [e]
                                        (let [new-value (-> e .-target .-value)
                                              error?    (not (valid? new-value))]
                                          (swap! form-data assoc id {:value new-value
                                                                     :error error?})))}]]))

(defn start [form-data]
  (fn [e]
    (.preventDefault e)
    (let [data @form-data
          participant-count (-> data :participant-count :value js/parseInt)
          group-count (-> data :group-count :value js/parseInt)]
      (g/init! participant-count group-count)
      (r/update-uri! :reveal))))

(def form-data (atom {:participant-count {:value 32
                                           :error false}
                      :group-count {:value 4
                                    :error false}}))

(defmethod v/render-view :index [_]
  [:div.container.start-form
   [:h1.text-center (loc :view.index.title)]
   [:form {:on-submit (start form-data)}
    [input form-data :participant-count]
    [input form-data :group-count]
    [:button.btn.btn-default
     {:type "submit"
      :disabled (not (all-valid? @form-data))}
     (loc :view.index.start)]]])
