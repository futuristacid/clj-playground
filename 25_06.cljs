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

(do
  (resize-canvas 0 0)
  (scale 1 1))
(do
  (let [colors []
        xs (range 8)
        ys (range 8)]
    (doseq [x xs y ys]
      (when (< (rand) 0.2)
        (fill-style (str "rgb("
                         (rand-int 255) " "
                         (rand-int 255) " "
                         (rand-int 255) " / "
                         (rand 0.5) ")"))
        (fill-rect x y 1 1)))))
