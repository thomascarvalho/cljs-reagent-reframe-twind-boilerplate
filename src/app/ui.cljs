(ns app.ui
  (:require 
   [shadow.cljs.modern :refer (js-template)]
   ["twind/css" :as twind-css]
   ["twind" :as twind]))

(def tw twind/tw)

(defn styled [css child]
  ((js-template twind-css/css css) child))

(defn setup []
  (twind/setup #js {:preflight true
                    :mode "strict"
                    :hash true
                    :sheet (twind/cssomSheet {:target (new js/CSSStyleSheet)})
                    :darkMode "class"}))