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

(comment
  (do
    (resize-canvas (* 3 5 8) (* 5 8))
    (scale 5 5))
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
          (fill-rect x y 1 1))))))

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


(defn rand-px []
  {:x (rand-int 8)
   :y (rand-int 8)
   :r (rand-int 255)
   :g (rand-int 255)
   :b (rand-int 255)})

(def pixels (take 42 (repeatedly rand-px)))

(defn sprite-sheet-alpha [pxls n]
  (resize-canvas (* 8 n) 8)
  (doseq [i (range n)]
    (doseq [p pxls]
      (fill-style (str "rgb("
                       (:r p) " "
                       (:g p) " "
                       (:b p) " / "
                       (/ 1 (inc i)) ")"))
      (fill-rect (:x p) (:y p) 1 1))
    (translate 8 0)))

(comment
  (sprite-sheet-alpha pixels 5)
  )

(defn draw-image [img sx sy sw sh dx dy dw dh]
  (.drawImage ctx img sx sy sw sh dx dy dw dh))

(def img (js/Image.))

(defn animate []
  (clear-rect 0 0 128 128)
  (draw-image img (* (rand-int 5) 128) 0 128 128 0 0 128 128)
  (.requestAnimationFrame js/window #(animate)))

(comment
  (draw-image img (* (rand-int 5) 128) 0 128 128 0 0 128 128)
  )

(defn points [w h]
  (iterate
   (fn [v]
     (let [n (rand-int 2)]
       (assoc v n
              ((if (pos? (rand-int 2)) inc dec)
               (nth v n)))))
   [(rand-int w) (rand-int h)]))

(def ps (set (take 1000000 (points 256 256))))

(def moves [[-1 -1] [-1 0] [-1 1]
            [0 -1] [0 1]
            [1 -1] [1 0] [1 1]])

(comment
  (do (resize-canvas 512 512)
      (scale 1 1)
      (fill-style "FireBrick"))

  (animate)
  )

(defn inc-or-dec []
  (if (pos? (rand-int 2)) inc dec))

(defn animate []
  (clear-rect -256 -256 1024 1024)
  (apply translate (rand-nth moves))
  (doseq [p ps] (fill-rect (first p) (second p) 1 1))
  (.requestAnimationFrame js/window #(animate)))


(defn z [x y] (Math/sqrt (- 4 (+ (* x x) (* y y)))))

(comment

  (do 
    (resize-canvas (* 5 128) 128)
    (scale 16 16)
    (translate 3 3))
  
  (def pixels (for [x (range -2 3 1) y (range -2 3 1)
                    :when (>= 4 (+ (* x x) (* y y)))]
                [x y (z x y)]))

  (doseq [px (nth sprites 4)]
    (let [c (+ 80 (* 30 (nth px 2)))]
      (fill-style (str "rgb(" (join \space [c 15 50]) ")")))
    (fill-rect (first px) (second px) 1 1))

  (def sprites (take 5 (iterate
                        (fn [coll] (map #(update % 2 rand) coll))
                        pixels)))  

  (resize-canvas 64 8)
  (doseq [z (range 28 256 28)]
    (fill-style (color z z z))
    (doseq [x (range 8) y (range 8)]
      (fill-rect x y 1 1))
    (translate 8 0)
    )


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


  (defn f [x y]
    (cond (or (= x 0) (= x 7) (= y 0) (= y 7)) 127
          :else 16))
  
  (def ps (for [x (range 8) y (range 8)]
            [x y (f x y)]))

  (doseq [p ps]
    (let [c (p 2)] (fill-style (color c c c)))
    (fill-rect (p 0) (p 1) 1 1))

  (resize-canvas 64 64)
  (scale 8 8)
  

    


  )

(defn color [r g b]
  (str "rgb(" (join \space [r g b]) ")"))
