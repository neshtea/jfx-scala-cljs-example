(ns jfxtest.simple
  (:require [reacl.core :as reacl :include-macros true]
            [reacl.dom :as dom :include-macros true]))

(reacl/defclass jfx-simple-app this [exit]
  render
  (dom/div
   (dom/button {:onClick #(.apply exit)} "Beenden")))

(defn simple-app [scala-args]
  (reacl/render-component
   (.getElementById js/document "content")
   jfx-simple-app nil (first scala-args)))
