(defproject cortex-tensorboard-viewer "0.1.0-SNAPSHOT"
  :description "Enables the monitoring (using Tensorboard) of neural networks training with Cortex "
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.shark8me/tfevent-sink "0.1.2-SNAPSHOT"]
                 [thinktopic/cortex "0.9.9-SNAPSHOT"]
                 ;;build off the https://github.com/thinktopic/cortex/pull/172 branch
                 [thinktopic/experiment "0.9.10-SNAPSHOT"]
                 ])
