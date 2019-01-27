male(bob).
male(steve).
male(dave).
male(chris).

female(amanda).
female(daria).
female(lynn).
female(lisa).

sibling(bob, amanda).
sibling(bob, daria).
sibling(steve, lynn).
sibling(steve, lisa).

cousin(dave, amanda).
cousin(dave, lynn).
cousin(chris, lisa).
cousin(chris, amanda).


canmarry(A, B) :- male(A),female(B),A \= B,not(sibling(A,B)),not(cousin(A,B)).

% Question1
% mylast(L,N).
% mylast([1,2,3,4]),N) ===> 4
mylast([T|[]],T).
mylast([_|T],N):-mylast(T,N).

% Question2
% mysize([1,2,3,4],N) ===> N = 4
mysize([],0).
mysize([_|T],N):-mysize(T,X),N is X + 1.

% Question3
% range(3,6,N) ===> N = [3,4,5,6]
range(A,A,[A]).
range(A,B,[A|T]):- A < B,X is A + 1, range(X,B,T).




















