(ns js-idiomatic-symbols.macros
  (:require [js-idiomatic-symbols.core :as jsid]))

(defmacro defjs-idiomatic
  "(defjs-idiomatic this<-me->you?! foo)
   ;=> (def ^:export isThisFromMeToYou foo)"
  [sym & body]
  `(def ^:export ~(jsid/js-idiomatize-sym sym) ~@body))

(defmacro defjs-idiomatic-export
  "(defjs-idiomatic this<-me->you?!)
   ;=> (def ^:export isThisFromMeToYou this<-me->you?!)"
  [sym]
  `(def ^:export ~(jsid/js-idiomatize-sym sym) ~sym))
