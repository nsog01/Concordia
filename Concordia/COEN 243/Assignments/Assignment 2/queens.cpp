/**************Programming Assignment 2***************/
/********************N Queens Problem*****************/

/*****************YOU MUST EDIT THIS FILE*************/
/*********Source file to implement the Board class****/

#include "queens.h"

Board::Board(int BoardSize) {
	N = BoardSize;
}

vector<vector<int> > Board::GetSolutions() {
	return Solutions;
}

// Display the solutions
void Board::PrintSolutions()
{
	cout << "Total number of solutions for board size " << N << "X" << N << " are " << Solutions.size() << endl << endl;
	for (unsigned int j = 0; j < Solutions.size(); j++) {
		cout << j+1; 
		for (int i=0; i<N; i++){
			cout << " (" << i+1 << "," << Solutions[j][i]+1 << ")";
                }
		cout << endl;
	}
	cout << endl << endl;
}

// --------------DO NOT MODIFY THE ABOVE------------------------------------

//===========ADD REMAINING IMPLEMENTATION OF BOARD CLASS BELOW==============

void Board::Solve(int col = 0)
{
    if(col == N)
    {
       this->Save();
        return;
    }
    
    for(int i = 0; i < N; i++)
    {
        if(Check(col, i))
        {
            Column.push_back(i);
            this->Iterate(col+1);
        }
        //right as soon as it gets out of an iteration, 
        //set the required position to false
        Column.pop_back();
    }
}

void Board::Save()
{
    Solutions.push_back(Column);
}

bool Board::Check(int row, int col)
{
    int i,j;
    
    // row check from 0 to N
    for(i = 0; i < N; i++)
    {
        if(Column[i] == row)
            return false;
    }
    
    //diagonal left up;
    for(i = col - 1 , j = row -1; i >= 0 && j >= 0; i--, j--)
    {
        if(Column[i] == j)
            return false;
    }
    
    //diagonal left down;
    for(i = col - 1 , j = row -1; i >= 0 && j < N; i--, j++)
    {
        if(Column[i] == j)
            return false;
    }
    
    //column self check by definition
    //diagonal right up checked by definition
    //diagonal right down checked by definition
    
    return true;

}