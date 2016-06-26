(ns enigma.core
  (:require [enigma.static-parts :as parts]
            [enigma.input-output :as translate]
            [enigma.rotor-ops    :as rotor])
  (:gen-class))

(defn encode-string [string rotatoes]
  "Takes two arguments, a string to encrypt and an atom containing all three rotors."
  (apply str (map #(translate/single-lap % (swap! rotatoes translate/step)) (translate/validate-str string))))

(defn -main
  [& rest]
  (println (apply str "Encoded message: " (encode-string (apply str rest) parts/rotor-atom))
           (apply str "  Window chars: " (translate/rotor-windows (deref parts/rotor-atom)))))

;; (defn translate-string [string rotatoes]
;;   (loop [remaining-letters  (translate/validate-str string)
;;          encoded-letters    []
;;          rotors             (translate/step rotatoes)]
;;     (if-not (seq remaining-letters)
;;       (apply str encoded-letters)
;;       (let [[first-char & rest] remaining-letters]
;;         (recur rest (conj encoded-letters (translate/single-lap first-char rotors)) (translate/step rotors))))))
