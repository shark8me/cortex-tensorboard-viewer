(ns cortex-tensorboard-viewer.core
  (:require [clojure.java.io :as io]
            [tfevent-sink.event-io :as eio]
            [clojure.core.async :as async :refer [>! <! <!! >!! chan close! go alts!]]))

(defn tf-event-sink
  "starts appending tensorflow events to file-path. num-msgs messages are read from
  the core.async channel. returns a core.async channel"
  [file-path channel num-msgs]
  (do
    (eio/create-event-stream file-path)
    (go (dotimes [n num-msgs]
          (let [[v _] (alts! [channel])]
            (eio/append-events file-path v))))))

(defn scalar-event-xform [prefix]
  "returns a xform function that creates scalar tensorflow events. The prefix argument 
  is the name prefix for the metric being reported"
  (fn [xf]
    (fn
    ;; START
      ([]          (xf))
    ;; STOP
      ([result]    (xf result))
    ;; PROCESS
      ([result input]
       (xf result (mapv (fn [[k v]] (eio/make-event (str prefix (name k)) v)) input))))))
