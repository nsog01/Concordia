(defun bseq (n) 
	(if (< n 0)
		'()
		(cons n (bseq (- n 1)))
	)
)

(defun cubeit (n)
	(if	n 
		(cons (* 
				(car n) 
				(car n) 
				(car n)
			) 
			(cubeit (cdr n))
		)
		'()
	)
)

(defun removeit (l n)
	(if (= (car l) n)
		(removeit (cdr l) n)
		(cons (car l) (removeit (cdr l) n))
		'()
	)
)

(defun setbag (l)
	(if (= (car (cdr l)) (car l))
		(setbag (cdr l)
		(cons (car l) (setbag (cdr l)))
	)
	'()
)