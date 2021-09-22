(ns app.ui
  (:require 
   [shadow.cljs.modern :refer (js-template)]
   ["twind/css" :as twind-css]
   ["twind" :as twind]))

(def tw twind/tw)

(defn css [css-text]
  (js-template twind-css/css css-text))

(defn setup []
  (twind/setup #js {:preflight true
                    :mode "strict"
                    :hash true
                    :sheet (twind/cssomSheet {:target (new js/CSSStyleSheet)})
                    :darkMode "class"}))