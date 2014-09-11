(ns team-code-off.core
  (:require [org.httpkit.client :as http])
  (:gen-class))


(def token "679dceaf-b4f4-419f-9d1d-e7d2ef8b482e/")
(def address_root "http://ec2-54-77-13-3.eu-west-1.compute.amazonaws.com:8802/")

(defn options [response]
	[(re-find #"north" response)
	 (re-find #"east" response)
	 (re-find #"south" response)
	 (re-find #"west" response)
		])

(defn -main [& args]
	(let [response1 (http/get (str address_root token "look" ))]
  	(println (options (:body @response1)))))
