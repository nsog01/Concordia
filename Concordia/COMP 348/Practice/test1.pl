parent(fred,sally).
parent(tina,sally).
parent(sally,john).
parent(sally,diane).
parent(sam,bill).

ancestor(A,X):-parent(A,X).
ancestor(A,X):-parent(A,C),ancestor(C,X).

% comment

go:-ancestor(X,Y), write(X), write(Y), write('->'), write(Y), nl, fail.

add(X,Y,Z):-X is A + B.

% gcd(a,0) = a.
% gcd(a,b) = gcd(b, a mod b).

gcd(A,0,A):-A > 0.
gcd(A,B,X):-B > 0, Z is A mod B, gcd(B, Z, X).