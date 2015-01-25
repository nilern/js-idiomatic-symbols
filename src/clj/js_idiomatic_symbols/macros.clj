(ns js-idiomatic-symbols.macros
  (:require [js-idiomatic-symbols.core :as jsid]))

(defmacro defjs-idiomatic
  "(defjs-idiomatic this<-me->you?! foo)
   ;=> (def ^:export isThisFromMeToYou foo)"
  [sym & body]
  `(def ~(vary-meta (jsid/js-idiomatize-sym sym) assoc :export true) ~@body))

(defmacro defjs-idiomatic-export
  "(defjs-idiomatic this<-me->you?!)
   ;=> (def ^:export isThisFromMeToYou this<-me->you?!)"
  [sym]
  `(def ~(vary-meta (jsid/js-idiomatize-sym sym) assoc :export true) ~sym))
