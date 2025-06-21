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


(defn rand-px []
  {:x (rand-int 8)
   :y (rand-int 8)
   :r (rand-int 255)
   :g (rand-int 255)
   :b (rand-int 255)})

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

