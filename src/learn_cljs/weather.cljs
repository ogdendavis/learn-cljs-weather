(ns ^:figwheel-hooks learn-cljs.weather
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))

(println "This text is printed from src/learn_cljs/weather.cljs. Go ahead and edit it and see reloading in action.")

(defn multiply [a b] (* a b))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "This is a lot like Webpack with hot reload!"}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn hello-world []
  [:p
   [:h1 "Simon says: " (:text @app-state)]
   [:h3 "Edit this in src/learn_cljs/weather.cljs and watch it change."]])

(defn greeter []
  [:div
   [:p "Hello, <YOUR_NAME>"]
   [:h1 "THE TITLE IS BELOW A PARAGRAPH"]
   [:ul
    [:li "I am a banana"]
    [:li "My spoon is too big"]]])

(defn mount [el]
  (rdom/render [greeter] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
