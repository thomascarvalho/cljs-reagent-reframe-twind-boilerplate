(ns app.subs
  (:require [re-frame.core :as re-frame]))

(def default-db
  {:name "Shadow-cljs - Reagent/Reframe - Twind boilerplate"})

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))
