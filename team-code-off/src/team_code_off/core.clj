(ns team-code-off.core
  (:require [org.httpkit.client :as http])
  (:gen-class))


(def token "679dceaf-b4f4-419f-9d1d-e7d2ef8b482e/")
(def address_root "http://ec2-54-77-13-3.eu-west-1.compute.amazonaws.com:8802/")

(defn options [response]
	(into #{} (remove #(nil? %1) (map keyword [(re-find #"north" response)
	 (re-find #"east" response)
	 (re-find #"south" response)
	 (re-find #"west" response)
		]))))

;position [x,y]
(defn look []
	(let [response (http/get (str address_root token "look" ))]
  	  (options (:body @response))))

(def start {
	{:x 0  :y 0} {:exits (look) :visits 1 :pos {:x 0 :y 0}}
	})

(defn won? [response]
	(re-find #"complete" response))

(defn option2coord [option current]
	(cond 
		(= :north option) {:x (:x current)       :y (inc (:y current))}
		(= :east option)  {:x (inc (:x current)) :y (:y current)}
		(= :south option) {:x (:x current)       :y (dec (:y current))}
		(= :west option)  {:x (dec (:x current)) :y (:y current)}
		))

(defn go [kw]
	(let [response (http/post (str address_root token (name kw)))
	      body (:body response)
		  status (:status response)]
		(cond 
			(not (= 200 status )) (throw (Exception. (str "Invalid response code!" status)))
			(won? response) (do (println "DONE") (System/exit 0))
			:else (options response))))

(defn solve [maze current]
	(let [visits (:visits (maze current))]
		(cond 
			(nil? visits) nil
			(>= 1 visits) nil)))

(defn choose [maze options coord]
	(cond (count options)
	(first 
		(sort-by :visits 
			(map 
				#(assoc % :visits (maze (:pos %))) 
				(map #(hash-map :dir % :pos (option2coord % maze)) options)))))

(defn -main [& args]
	(choose start #{:east} {:x 0 :y 0})
	;(start)
	)
