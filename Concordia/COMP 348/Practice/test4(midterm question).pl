% write a Prolog program to:

% 1
check if a list contains a value
contains([1,2,3], 2). ===> true
contains([1,2,3], 4). ===> false
contains([alice,bob,eve], bob). ===> true

contains([H|_], H).
contains([H|T], V):- contains(T,V), H \= V.

% 2
find the third last element of a list
thlast([2,3,5,7,11], X) ===> X = 5

thlast([X,_,_],X).
thlast([_|T],X):- thlast(T,X).

% 3
check that one list is twice the size of the other
dblsize([1,2,3,4], [1,2]) ===> true
dblsize([a,a,a,a], [a,b]) ===> true
dblsize([a,a], [a,b,c,d]) ===> false

dblsize([],[]).
dblsize([_|[_|T]],[_|B]):- dblsize(T,B).

------------------------------------------------------------------------

clerk(jones).
clerk(smith).

typist(brown).

manager(patel).
manager(lee).

supervises(X,Y):- manager(X), clerk(Y).
supervises(X,Y):- clerk(X), typist(Y).
supervises(X,Y):- manager(X), typist(Y).

% Midterm Question: How does Prolog go through the unification / resolution execution process for supervises?
 
Follow: supervises(Supervisor, brown).
-------------------------------------------------------------------------
Follow: supervises(Supervisor,brown).

% resolution finds:
supervises(X,Y):- manager(X), clerk(Y).

% unification makes it:
supervises(Supervisor,brown):- manager(Supervisor), clerk(brown).
	? manager(Supervisor).
	manager(patel).
	manger(lee).
	? clerk(brown).
	false...
	
	supervises(Supervisor,brown):- clerk(Supervisor), typist(brown).
	? clerk(jones).
	clerk(smith).
	? typist(brown).
	true.
	
	Supervisor = jones.
	supervises(jones,brown).
	Supervisor = smith.
	supervises(smith, brown)
	
	supervises(Supervisor,brown):- manager(Supervisor), typist(brown).
	? manager(Supervisor)
	manager(patel).
	manager(lee).
	? typist(brown).
	true.
	
Supervisor = patel.
supervises(patel, brown)
Supervisor = lee.
supervises(lee, brown)
