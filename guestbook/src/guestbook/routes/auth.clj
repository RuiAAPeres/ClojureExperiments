(ns guestbook.routes.auth
  (:require [compojure.core :refer [defroutes GET POST]]
            [guestbook.views.layout :as layout]
            [hiccup.form :refer [form-to label text-field password-field submit-button]]
            [noir.response :refer [redirect]]
            [noir.session :as session]))

(defn control [field name text]
  (list (label name text)
        (field name)
        [:br]))

(defn registration-page []
  (layout/common
    (form-to [:post "/register"]
             (control text-field "id" "screen name")
             (control password-field "pass" "password")
             (control password-field "pass1" "retype password")
             (submit-button "create account"))))

(defn login-page []
  (layout/common
   (form-to [:post "/login"]
      (control text-field :id "screen name")
      (control password-field :pass "Password")
      (submit-button "login"))))

(defroutes auth-routes
  (GET "/register" [] (registration-page))
  (POST "/register" [id pass pass1]
        (handle-registration id pass pass1))

  (GET "/login" [] (login-page))
  (POST "/login" [id pass]
    (handle-login id pass)))
