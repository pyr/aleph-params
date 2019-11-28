aleph-params: Netty-based query string parameters decoding
==========================================================

[![Build Status](https://secure.travis-ci.org/pyr/aleph-params.png)](http://travis-ci.org/pyr/aleph-params)
[![cljdoc badge](https://cljdoc.org/badge/spootnik/aleph-params)](https://cljdoc.org/d/spootnik/aleph-params/CURRENT)


```clojure
    [spootnik/aleph-params "0.1.0"]
```

For Netty-based HTTP servers (for instance:
[aleph](https://aleph.io)), provides query string parameter parsing
with no additional dependencies.

## Usage

### Ring style

Wrapped requests will have get-params at the `:get-params`
key when applicable.

```clojure
(require '[aleph.http.params :refer [wrap-params]])

(def handler
  (-> (constantly {:status 200 :body ""})
      (wrap-params)))
```

### Interceptor

An interceptor is provided at `aleph.http.params/interceptor`, it provides
a single `:enter` key and expects the request at the `:request` key in the
context. Parameters are added at the `:get-params` key for downstream
interceptors.

### Plain parser

```clojure
(require '[aleph.http.params :refer [parse-params]])

(parse-params "?foo=bar") ;; => {:foo "bar"}
```



