(ns ^:figwheel-hooks hello-world.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]))


;; This command will cause our printlns to also show up in the console's log,
;; which can sometimes be useful.
(enable-console-print!)


(println "This text is printed from src/hello_world/core.cljs. Go ahead and edit it and see reloading in action.")


(defn multiply [a b] (* a b))



;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello class!"}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn hello-world []
  [:div

   [:h1 (:text @app-state)]
   [:h3 "To infinity, and beyond! "(/ 1 0)]
   [:h3 "We're not in Kansas anymore: (+ 1 nil) " (+ 1 nil) " should be a type error..."]
   [:p "Go watch this talk: " [:a {:href  "https://www.destroyallsoftware.com/talks/wat"} "Wat!?"]]
   [:h3 "Edit this in src/hello_world/core.cljs and WATCH IT CHANGE!"]])

(defn mount [el]
  (reagent/render-component [hello-world] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
