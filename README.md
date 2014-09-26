js-idiomatic-symbols
====================

_Transform Clojure naming conventions to those of JavaScript._

More precisely, these transformations:

```clojurescript
  dash-delimited                   ;=> dashDelimited
  set!                             ;=> set
  *dynamic*                        ;=> dynamic
  **private**                      ;=> __private__
  all-humans-are-created=and-free  ;=> allHumansAreCreatedEqualAndFree
  apples+oranges                   ;=> applesPlusOranges
  even?                            ;=> isEven
  rope->str                        ;=> ropeToStr
  rope<-str                        ;=> ropeFromStr
```

Rationale
---------

Using Clojure/Lisp symbol names in ClojureScript but exporting them as 
idiomatic JavaScript symbols feels like the Right Thing. It also felt a bit
tedious, so I created this little library.

Usage
-----

Add the following entry to your project.clj `:dependencies` vector:

![Clojars Project](http://clojars.org/js-idiomatic-symbols/latest-version.svg)

Then, in your code, you can do these things:

```clojurescript
(ns js-friendly
  (:require [js-idiomatic-symbols.core :as jsid])
  (:require-macros [js-idiomatic-symbols.macros :as jsidm]))

;; Of course, the whole thing is based on messing with strings...
(jsid/js-idiomatize-str "this<-me->you?!") ;=> "isThisFromMeToYou"

;; ... but symbol manipulation is more important:
(jsid/js-idiomatize-sym 'this<-me->you?!) ;=> 'isThisFromMeToYou

;; You might ensure js-formatted names up front:
(jsidm/defjs-idiomatic this<-me->you?! "Love, love, love.")
isThisFromMeToYou ;=> "Love, love, love."
this<-me->you?! ; note that this will not be defined at all

;; Or you might just write normal ClojureScript
(def not->disencourage "But it's NOT so easy.")

;;; ...

;; ... and then define additional symbols for the public (JavaScript) API:
(jsidm/defjs-idiomatic-export not->disencourage)
notToDisencourage ;=> "But it's NOT so easy."

;;; Both macros also add the ^:export metadata for you.
```

And that's all there is to this.

Acknowledgements
----------------

The inspiration for this project can be largely attributed to 
[Gozala](https://github.com/Gozala)'s [Wisp](https://github.com/Gozala/wisp). 
Specifically, [this part](https://github.com/Gozala/wisp#conventions).

License
-------

Copyright © 2014 Pauli Jaakkola

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
