(ns cptnapi.core
  (:require [cheshire.core :as json]
            [org.httpkit.server :refer [run-server]]
            [ring.middleware.params :refer :all]
            [compojure.route :refer :all] 
            [cptnapi.views :as v]
            [hiccup.core :refer :all]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [compojure.core :refer :all]
            [monger.json])
  (:gen-class :main true))

;; connect to local mongodb and connect to captains db
(defn inc-captain-vote
  [captain]
  (let [conn (mg/connect)
        db (mg/get-db conn "captains")
        coll "captains"]
    (mc/update db "captains" {:name captain} {$inc {:votes 1}})))

(defn add-captain
  [params]
  (let [{:keys [name pic-url source]} params
        conn  (mg/connect)
        db (mg/get-db conn "captains")
        coll "captains"]
    (mc/save-and-return db coll {:name name :votes 1 :image pic-url :source source})))

(defn get-captains
  []
  (let [conn  (mg/connect)
        db (mg/get-db conn "captains")
        coll "captains"]
    (json/encode (mc/find-maps db coll))))

(defn show-captains
  []
  (v/home-page))

(defroutes app
  (GET "/" [] (clojure.java.io/resource "public/index.html"))
  (GET "/api/captains" [] (get-captains))
  (POST "/api/captain" {params :params} (add-captain params))
  (resources "/")
  (not-found "not found"))

(defn -main []
  (run-server (wrap-params app) {:port 3447}))
