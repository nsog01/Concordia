/**************Programming Assignment 2***************/
/********************N Queens Problem*****************/

/*****************YOU MUST EDIT THIS FILE*************/
/***********Header file to declare the Board class****/

#ifndef _QUEENS_H
#define _QUEENS_H

#include <iostream> 
#include <vector>
// add other headers below as needed

using namespace std;

class Board {
public:
	Board(int BoardSize); // constructor takes the board size as input 
	void Solve(int);
	vector<vector<int> > GetSolutions();
	void PrintSolutions();

	// add any additional public members below........

private:
	static int N; // Private variable to keep board size
	vector<int> Column; // A temporary assignment; Column[i]=j means queen placed at row i, column j
	vector<vector<int> > Solutions; // Set of all solutions

	// add any additional private members below........  
         
        // Method to recursively check values row by row
        void Iterate(int);
        // Method to check if row/column combination is available
        bool Check(int, int);
        // Method to save required combination
        void Save();
        
};

#endif

