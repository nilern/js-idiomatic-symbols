(ns js-idiomatic-symbols.core
  (:require [clojure.string :as string]))

;;; Replacer functions
;;; ============================================================================

;;; The characters ! and * are just removed (since JS embraces mutability...)

(defn- replace-!
  "`\"set!\" ;=> \"set\"`"
  [s]
  (string/replace s #"!" ""))

(defn- replace-*
  "`\"*dynamic*\" ;=> \"dynamic\"`"
  [s]
  (string/replace s #"\*" ""))

;;; **private** in Lisp would be __private__ in JavaScript

(defn- replace-**
  "`\"**private**\" ;=> \"__private__\"`"
  [s]
  (string/replace s #"\*\*" "__"))

;;; Operators are not allowed in JS symbol names (because they don't need to be
;;; surrounded with whitespace).

(defn- replace-=
  "`\"all-humans-are-created=and-free\"
   ;=> \"all-humans-are-created-equal-and-free\"`"
  [s]
  (string/replace s #"=" "-equal-"))

(defn- replace-+
  "`\"apples+oranges\" ;=> \"apples-plus-oranges\"`"
  [s]
  (string/replace s #"\+" "-plus-"))

(defn- replace-?
  "`\"even?\" ;=> \"is-even\"`"
  [s]
  (let [s1 (string/replace s #"\?" "")]
    (if (re-find #"\?" s)
      (str "is-" s1)
      s1)))

(defn- replace->
  "`\"rope->str\" ;=> \"rope-to-str\"`"
  [s]
  (string/replace s #"->" "-to-"))

(defn- replace<-
  "`\"rope<-str\" ;=> \"rope-from-str\"`"
  [s]
  (string/replace s #"<-" "-from-"))

(def ^:private replace-arrows-and-punctuation
  "`\"this<-me->you?!\" ;=> \"is-this-from-me-to-you\"`"
  (comp #(string/replace % #"^-|-$" "")
        replace<- replace->
        replace-?
        replace-+ replace-=
        replace-** replace-*
        replace-!))

;; clojure.string/capitalize lower-cases the tail of the string, which isn't
;; necessarily desirable for our use-case.
(defn- just-capitalize
  "`\"naSTySTring\" ;=> \"NaSTySTring\"`"
  [[first-letter & tail]]
  (apply str (string/upper-case first-letter) tail))

;;; API
;;; ============================================================================

(defn js-idiomatize-str
  "`\"this<-me->you?!\" ;=> \"isThisFromMeToYou\"`"
  [s]
  (let [[head & tail] (-> (replace-arrows-and-punctuation s)
                          (string/split #"-"))]
    (apply str head (map just-capitalize tail))))

(defn js-idiomatize-sym
  "Like js-idiomatize-str, but takes and returns a symbol:
   `'this<-me->you?! ;=> 'isThisFromMeToYou`"
  [sym]
  (symbol (js-idiomatize-str (str sym))))
