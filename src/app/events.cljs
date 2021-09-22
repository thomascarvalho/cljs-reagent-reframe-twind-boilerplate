(ns app.events
  (:require [re-frame.core :as re-frame]
            [app.subs :as subs]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   subs/default-db))