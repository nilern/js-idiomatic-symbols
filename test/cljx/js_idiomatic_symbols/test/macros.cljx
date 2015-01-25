(ns js-idiomatic-symbols.test.macros
  #+clj (:require [js-idiomatic-symbols.macros :as jsidm]
                  [clojure.test :refer [deftest is]])
  #+cljs (:require [cemerick.cljs.test :as t])
  #+cljs (:require-macros [js-idiomatic-symbols.macros :as jsidm]
                          [cemerick.cljs.test :refer (deftest is)]))

(deftest idiomatic-def
  (is (= (do (jsidm/defjs-idiomatic this<-me->you?! "Love, love, love.")
             isThisFromMeToYou)
         "Love, love, love.")))

(deftest idiomatic-extern
  (is (= (do (def this<-me->you?! "Love, love, love.")
             (jsidm/defjs-idiomatic-export this<-me->you?!)
             isThisFromMeToYou)
         "Love, love, love.")))
