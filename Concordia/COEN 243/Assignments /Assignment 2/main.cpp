/*******************Programming Assignment 2***************/
/********************N Queens Problem**********************/

/*****************YOU MUST NOT EDIT THIS FILE**************/

/*********Place N Queens on an NXN board such that*********/
/************none of the queens is under attack************/
#include <iostream>
using namespace std;
//#include "queens.h"
//#include "studentID.h"


int main () {
	// define the board size
	int BoardSize;
	
	// top level loop
	while (1) {
		cout << "Please enter the board size (or -1 to exit)" << endl;
		// read in the board size from user
		cin >> BoardSize;

		// if the user is done (size == -1), then exit
		if (BoardSize == -1) break;

		// Build the board of given size
		Board B(BoardSize);

		// solve for N Queens
		B.Solve();

		// print out the solutions
		B.PrintSolutions();
	} // end while loop
} // end main function

