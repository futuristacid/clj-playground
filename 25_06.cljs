(first '(1 2 3))
(rest '(1 2 3))
(cons 0 '(1 2 3))

(first [1 2 3])
(rest [1 2 3])
(cons 0 [1 2 3])

(seq? (rest [1 2 3]))

(first {:fname "Mau" :lname "Nunez"})
(rest {:fname "Mau" :lname "Nunez"})
(cons [:mname "J"] {:fname "Mau" :lname "Nunez"})

(first #{:the :quick :brown :fox})
(rest #{:the :quick :brown :fox})
(cons :jumped #{:the :quick :brown :fox})

(sorted-set :the :quick :brown :fox)
(sorted-map :c 3 :b 2 :a 1)

(conj '(1 2 3) :a)
(into '(1 2 3) '(:a :b :c))

(conj [1 2 3] :a)
(into [1 2 3] [:a :b :c])

(def canvas (.getElementById js/document "canvas"))
(def ctx (.getContext canvas "2d"))
(defn fill-style [c] (set! (.-fillStyle ctx) c))
(defn fill-rect [x y w h] (.fillRect ctx x y w h))
(defn scale [x y] (.scale ctx x y))
(defn translate [x y] (.translate ctx x y))
(defn clear-rect [x y w h] (.clearRect ctx x y w h))
(defn resize-canvas [w h]
  (do (set! (.-width canvas) w)
      (set! (.-height canvas) h)))


(range 10)
(range 10 20)
(range 1 25 2)
(range 0 -1 -0.25)

(repeat 5 1)
(repeat 10 "x")

(take 10 (iterate inc 1))

(def whole-numbers (iterate inc 1))

(take 20 (repeat 1))
(interleave whole-numbers ["A" "B" "C" "D" "E"])
(apply str (interpose "," ["apples" "bananas" "grapes"]))

(require '[clojure.string :refer [join]])
(join \, ["apples" "bananas" "grapes"])

(set [1 2 3])
(hash-set 1 2 3)
(vec (range 3))

(take 10 (filter even? whole-numbers))
(take 10 (filter odd? whole-numbers))

(def vowel? #{\a \e \i \o \u})
(def consonant? (complement vowel?))

(take-while consonant? "the-quick-brown-fox")
(drop-while consonant? "the-quick-brown-fox")
(split-at 5 (range 10))
(split-with #(<= % 10) (range 0 20 2))
(take 10 (cycle (range 3)))


(defn rand-px [s]
  {:x (rand-int s)
   :y (rand-int s)
   :r (rand-int 255)
   :g (rand-int 255)
   :b (rand-int 255)})

(defn draw-image [img sx sy sw sh dx dy dw dh]
  (.drawImage ctx img sx sy sw sh dx dy dw dh))


(defn color [r g b]
  (str "rgb(" (join \space [r g b]) ")"))



(comment

  (resize-canvas 256 256)
  (scale 1 1)
  (.addEventListener
   img "load"
   (doseq [x (range 0 256 8) y (range 0 256 8)]
     (draw-image img
                 (* 8 (rand-int 8)) 0 8 8
                 x y 8 8)
     )
   )
  (set! (.-src img) "a.png")

  ;; -1/C (Ax + By + D)
  ;; A and B change direction
  ;; D moves surface
  ;; C changes inclination
  (defn f [x y]
    (* -1 (+  (* 1 x) (* 1 y) -127)))

  (doseq [[x y z] (for [x (range -64 64) y (range -64 64)]
                    [x y (int (f x y))])]
    (when-not (or (< z 0) (>= z 256))
      (fill-style (color z z z))
      (fill-rect x y 1 1)))

  (clear-rect -256 -256 512 512)
  (resize-canvas 512 512)
  (translate 256 256)
  (scale 1 -1)
  )


(every? odd? [1 3 5])
(every? odd? [1 3 5 8])
(some even? [1 2 3])
(some even? [1 3 5])
(some identity [nil false 1 nil 2])
(some #{3} (range 20))

(map #(str "<p>" % "</p>") ["the" "quick" "brown" "fox"])
(map #(str "<" %1 ">" %2 "</" %1 ">")
     ["h1" "h2" "h3" "h1"]
     ["the" "quick" "brown" "fox"])
(reduce + (range 1 11))
(reduce * (range 1 11))
(sort [42 1 7 11])
(sort-by #(str %) [42 1 7 11])
(sort > [42 1 7 11])
(sort-by :grade > [{:grade 83} {:grade 90} {:grade 77}])

(def x (for [i (range 1 3)] (do (println i) i)))
(doall x)
(dorun x)

(peek '(1 2 3))
(pop '(1 2 3))
(rest ())
(peek [1 2 3])
(pop [1 2 3])
(get [:a :b :c] 1)
(get [:a :b :c] 5)
([:a :b :c] 1)
(assoc [0 1 2 3 4] 2 :two)
(subvec [1 2 3 4 5] 3)

(keys {:sundance "spaniel" :darwin "beagle"})
(vals {:sundance "spaniel" :darwin "beagle"})
(get {:sundance "spaniel" :darwin "beagle"} :darwin)
(get {:sundance "spaniel" :darwin "beagle"} :snoopy)
({:sundance "spaniel" :darwin "beagle"} :darwin)
({:sundance "spaniel" :darwin "beagle"} :snoopy)
(:darwin {:sundance "spaniel" :darwin "beagle"})
(:snoopy {:sundance "spaniel" :darwin "beagle"})

(def score {:stu nil :joey 100})
(:stu score)
(contains? score :stu)
(get score :stu :score-not-found)
(get score :aaron :score-not-found)

(def song {:name "Agnus Dei"
           :artist "Krzysztof Penderecki"
           :album "Polish Requiem"
           :genre "Classical"})
(assoc song :kind "MPEG Audio File")
(dissoc song :genre)
(select-keys song [:name :artist])
(merge song {:size 8118166 :time 507245})
(merge-with concat
            {:rubble ["Barney"] :flintstone ["Fred"]}
            {:rubble ["Betty"] :flintstone ["Wilma"]}
            {:rubble ["Bam-Bam"] :flintstone ["Pebbles"]})

(def languages #{"java" "c" "d" "clojure"})
(def beverages #{"java" "chai" "pop"})
(require '[clojure.set :as s])
(s/union languages beverages)
(s/difference languages beverages)
(s/intersection languages beverages)
(s/select #(= 1 (count %)) languages)

(def compositions
  #{{:name "The Art of the Fugue" :composer "J. S. Bach"}
    {:name "Musical Offering" :composer "J. S. Bach"}
    {:name "Requiem" :composer "Giuseppe Verdi"}
    {:name "Requiem" :composer "W. A. Mozart"}})
(def composers
  #{{:composer "J. S. Bach" :country "Germany"}
    {:composer "W. A. Mozart" :country "Austria"}
    {:composer "Giuseppe Verdi" :country "Italy"}})
(def nations
  #{{:nation "Germany" :language "German"}
    {:nation "Austria" :language "German"}
    {:nation "Italy" :language "Italian"}})
(s/rename compositions {:name :title})
(s/select #(= (:name %) "Requiem") compositions)
(s/project compositions [:name])
(s/join compositions composers)
(s/project (s/join (s/select #(= (:name %) "Requiem")
                             compositions)
                   composers)
           [:country])


(defn fibo []
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(defn animate! []
  (clear-rect -128 -128 256 256)
  (draw)
  (reset! req (request-animation-frame #(animate!))))

(defn request-animation-frame [f]
  (.requestAnimationFrame js/window f))

(defn cancel-animation-frame [v]
  (.cancelAnimationFrame js/window v))

(defn draw []
  (let [xys @stt]
    (doseq [[x y] xys]
      (fill-rect x y 1 1))))


(defn draw []
  (let [pss @stt]
    (doseq [[i ps] (map list (range) pss)]
      (doseq [[x y] ps]
        (let [x (+ x (* 8 i))]
          (fill-rect x y 1 1))))))




(comment
  (resize-canvas 256 256)
  (translate 1 1)
  (scale 1 1)

  (def stt (atom [[0 -1] [0 1] [1 -2] [1 2]]))
  (def req (atom (request-animation-frame #(animate!))))



  (reset! stt
          (repeatedly 3 #(take (inc (rand-int 36))
                               (shuffle (for [x (range 1 7)
                                              y (range 1 7)]
                                          [x y])))))



  
  (def f (let [p [-5 -3]
               a 37
               m (Math/tan (/ (* Math/PI a) 180))]
           #(vector (int (+ (second p) (* m (- % (first p))))))))

  (let [xs (range -10 11)]
    (reset! stt
            (mapcat #(for [y %2] [%1 y])
                    xs (for [x xs] (f x)))))


  

  (cancel-animation-frame @req)
  )
