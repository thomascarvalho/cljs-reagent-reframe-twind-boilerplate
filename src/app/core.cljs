(ns app.core
  (:require
   [re-frame.core :as rf]
   [reagent.core :as r]
   [reagent.dom :as rdom]
   [app.events :as events]
   [app.subs :as subs]
   ["react-helmet" :refer [Helmet]]
   ["react-router-dom" :refer (Route Link NavLink) :rename {BrowserRouter Router}]
   [app.ui :refer [tw setup styled]]))

(defn hero []
  (let [name (rf/subscribe [::subs/name])]
    [:div {:className (tw "p-12 rounded text-center bg-blue-200 text-blue-900 text-xl shadow-md font-normal")}
     "Hello " @name " !"]))

(defn index []
  [:div
   [:> Helmet [:title "Home"]]
   [:h2 "Home"]])

(defn users []
  [:div
   [:> Helmet [:title "Users"]]
   [:h2 "Users"]])

(defn about []
  [:div
   [:> Helmet [:title "About"]]
   [:h2 "About"]])

;; react-router wants react component classes
(def Index (r/reactify-component index))
(def Users (r/reactify-component users))
(def About (r/reactify-component about))

(defn root []
  [:> Router
   [:div {:className (tw "p-6")}
    [:nav
     [:ul {:className (tw "flex flex-row space-x-4")}
      [:li
       [:> NavLink {:to "/"} "Home"]]
      [:li
       [:> NavLink {:to "/about/"} "About"]]
      [:li
       [:> NavLink {:to "/users/"} "Users"]]]]
    [:div {:className (tw "container mx-auto mt-12 px-6")}
     [:> Route {:path "/" :exact true :component Index}]
     [:> Route {:path "/about/" :component About}]
     [:> Route {:path "/users/" :component Users}]]]])

(defn mount-root
  []
  (rdom/render [root] (js/document.getElementById "main")))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (setup))

(defn ^:dev/after-load reload
  []
  (rf/clear-subscription-cache!)
  (mount-root))

(defn main
  []
  (init)
  (mount-root))
