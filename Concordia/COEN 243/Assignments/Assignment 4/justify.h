/*******************Programming Assignment 4***************/
/********************Text Justification**********************/

/*****************YOU MUST NOT EDIT THIS FILE**************/
/*****************YOU MUST NOT SUBMIT THIS FILE**************/

/*********Definition of types, error codes and function prototype**************************/


#include <iostream>
#include <string>
#include <vector>
#include "ctype.h"

using namespace std;

// some useful type definitions
typedef vector<string> PARA;
typedef unsigned int ERROR;

#define MIN_COL_WIDTH 20
#define MAX_COL_WIDTH 60

// error codes
#define ERROR_NONE 0
#define ERROR_NO_CHARS 1
#define ERROR_ILLEGAL_WIDTH 2

// function prototype
ERROR justify(unsigned int width, string in, PARA *out);
// width is the desired width of line in output (justified) text.
// "in" is the input string.
// PARA is a vector of strings, where each element is one line
// in the output justified text.
// The first element of the PARA vector is the first line of the 
// output text, the second element is the second line, and so on.
// note that output text variable is declared in main function.
// it is passed by reference to "justify".
// The function returns one of the above error codes.