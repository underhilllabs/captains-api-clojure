(ns cptnapi.views
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn home-page
  []
  (html 
   [:head
    [:title "Vote for your Favorite Starship Captain"]
    (include-css "/css/bootstrap.css")
    (include-css "/css/app.css")
    (include-js "https://ajax.googleapis.com/ajax/libs/angularjs/1.0.6/angular.min.js")
    (include-js "https://ajax.googleapis.com/ajax/libs/angularjs/1.0.6/angular-resource.min.js")
    (include-js "/js/app.js")]
   [:body {:ng-app "CptnCounter"}
    [:div.container
     [:div#captains.span12 {:ng-controller "CaptainCtrl"}]        
     [:h1 "Vote for your Favorite Starship Captain"]
     [:div.row.span7
      "Search " [:input {:ng-model "query"}]
      [:div {:class "cptn-div" :ng-repeat "captain in $scope.captains"}
       [:h3 "{{captain.name}}"]
       [:p "{{captain.source}}"]
       [:span 
        [:img.img-rounded {:ng-src "{{captain.image}}" :width 100}]
        [:button {:ng-click "inc(captain.idx)"} (str "{{captain.votes}}")]]]]]]))
