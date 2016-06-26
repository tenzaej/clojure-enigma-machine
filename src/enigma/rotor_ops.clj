(ns enigma.rotor-ops)

(defn disc-into-wheel [disc]
  (seq disc))

(defn rotate-wheel [wheel]
  (concat (rest wheel) [(first wheel)]))

(defn rotate-rotor [rotor]
  {:alphabet (rotate-wheel (rotor :alphabet)),
   :wheel (rotate-wheel (rotor :wheel)),
   :notch (rotor :notch)})

(defn set-ground [char rotor]
  (loop [char char
        rotor rotor])
          (if (= (first (rotor :alphabet)) char)
          rotor
          (recur char (rotate-rotor rotor))))

(defn ground-all
  "Sets the ground for all three rotors; right, center, left"
  [rotor-vector]
  [(set-ground \K (rotor-vector 0)),
   (set-ground \C (rotor-vector 1)),
   (set-ground \M (rotor-vector 2))])

(defn set-ground-all
  "Ground all rotors to a given set of positions"
  [rotor-vector position-vector]
  [(set-ground (position-vector 0) (rotor-vector 0))
   (set-ground (position-vector 1) (rotor-vector 1))
   (set-ground (position-vector 2) (rotor-vector 2))])
