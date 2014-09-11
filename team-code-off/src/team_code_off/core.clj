(ns team-code-off.core
  (:gen-class))


(def token "679dceaf-b4f4-419f-9d1d-e7d2ef8b482e/")
(def address_root "http://ec2-54-77-13-3.eu-west-1.compute.amazonaws.com:8802/")

(defn -main [& args]
	(let [response1 (http-kit/get (str address_root token "look" ))]
  	(println "status: " (:status @response1))))
