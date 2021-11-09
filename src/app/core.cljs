(ns app.core
  (:require
   [re-frame.core :as rf]
   [reagent.core :as r]
   [reagent.dom :as rdom]
   [app.events :as events]
   [app.subs :as subs]
   ["react-helmet" :refer [Helmet]]
   ["react-router-dom" :refer (Route BrowserRouter Outlet NavLink Routes)]
   [app.ui :refer [tw setup css theme]]))


(defn hero []
  (let [name (rf/subscribe [::subs/name])]
    [:div {:className (tw "p-12 rounded text-center bg-blue-200 shadow-md font-normal")}
     [:h2 {:className (tw "text-xl" (css "color: red"))}
      "Hello " @name " !"]
     [:h3 {:className (tw (css {:color "blue"}))}
      "Welcome"]]))

(defn index []
  [:div
   [:> Helmet [:title "Home"]]
   [:h2 "Home"]
   [hero]])

(defn users []
  [:div
   [:> Helmet [:title "Users"]]
   [:h2 "Users"]])

(defn about []
  [:div
   [:> Helmet [:title "About"]]
   [:h2 "About"]])

(defn navigation-link [label path]
  [:> NavLink {:to path :end true} label])

(defn navigation-bar [links]
  [:nav {:className (tw
                     (css
                      {"a"
                       {:color (theme "colors.blue.500")
                        "&.active" {:color (theme "colors.blue.900")}}}))}
   [:ul {:className (tw "flex flex-row space-x-4")}
    (map
     (fn [{:keys [path label]}]
       ^{:key path} [:li (navigation-link label path)]) links)]])

(def links [{:path "/" :index true :label "Home" :element index}
            {:path "/about" :label "About" :element about}
            {:path "/users" :label "Users" :element users}])

(defn layout []
  [:div {:className (tw "p-6")}
   [navigation-bar links]
   [:div {:className (tw "container mx-auto mt-12 px-6")}
    [:> Outlet]]])

(defn pages [links]
  (map
   (fn [link]
     ^{:key (:label link)}
     [:> Route {:path (:path link)
                :index (:index link)
                :element (r/as-element [:> (r/reactify-component (:element link))])}])
   links))

(defn root []
  [:> BrowserRouter
   [:> Routes
    [:> Route {:path "/" :element (r/as-element [:> (r/reactify-component layout)])}
     (pages links)]]])

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
