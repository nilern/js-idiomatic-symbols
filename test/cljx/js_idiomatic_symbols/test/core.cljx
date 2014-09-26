(ns js-idiomatic-symbols.test.core
  #+clj (:require [js-idiomatic-symbols.core :as jsid]
                  [clojure.test :refer [deftest is]])
  #+cljs (:require [js-idiomatic-symbols.core :as jsid]
                   [cemerick.cljs.test :as t])
  #+cljs (:require-macros [cemerick.cljs.test :refer (deftest is)]))

(deftest unit-tests
  (is (= (#'jsid/replace-! "set!")
         "set"))
  (is (= (#'jsid/replace-* "*dynamic*")
         "dynamic"))
  (is (= (#'jsid/replace-** "**private**")
         "__private__"))
  (is (= (#'jsid/replace-= "all-humans-are-created=and-free")
         "all-humans-are-created-equal-and-free"))
  (is (= (#'jsid/replace-+ "apples+oranges")
         "apples-plus-oranges"))
  (is (= (#'jsid/replace-? "even?")
         "is-even"))
  (is (= (#'jsid/replace-> "rope->str")
         "rope-to-str"))
  (is (= (#'jsid/replace<- "rope<-str")
         "rope-from-str"))
  (is (= (#'jsid/just-capitalize "naSTySTring")
         "NaSTySTring")))

(deftest integration-tests
  (is (= (#'jsid/replace-arrows-and-punctuation "this<-me->you?!")
         "is-this-from-me-to-you"))
  (is (= (jsid/js-idiomatize-str "this<-me->you?!")
         "isThisFromMeToYou"))
  (is (= (jsid/js-idiomatize-sym 'this<-me->you?!)
         'isThisFromMeToYou)))
