/*******************Programming Assignment 1***************/
/****************Caesar's substitution cipher**************/

/*****************YOU MUST NOT EDIT THIS FILE**************/

/****Substitute alphabets in a string using rotation key***/
/****Leave all other characters unchanged******************/
/****Confirm correct encryption by decrypting**************/
/****to recover original text******************************/

#include "encrypt.h"
#include "studentID.h"

using namespace std;


int main () {
	// define the rotation index variable and 
	//the new rotation index variable to recover the text
	int rot, newrot;
	
	// define the input string (input), encrypted string (output)
	// and the recovered string (decrypted)
	string input, output, decrypted;

	// top level loop to perform encryptions
	while (1) {
		std::cout << "Please enter rotation key (or -1 to exit)" << endl;
		// read in the rotation index from user
		std::cin >> rot;

		// if the user is done (rot == -1), then exit
		if (rot == -1) break;

		// read in the text to be encrypted (terminated by new line)
		// use "ignore" method of stream cin to get rid of 
		// accumulated new line characters
		std::cout << "Please enter text to be encrypted" << endl;
		cin.ignore();
		getline(std::cin, input);

		// encrypt the input text
		output = encrypt(input, rot);
		// print out the encrypted text
		cout << endl << "The encrypted text is " << endl << output << endl;

		// calculate a new rotation value to recover original text
		// from the encrypted text -- used for testing only
		newrot = 26 - (rot%26);
		// recover original text by calling encrypt with new rotation value
		decrypted = encrypt(output, newrot);

		// print out the recovered text -- should match original
		cout << endl << "The recovered text is " << endl << decrypted << endl << endl;
	} // end while loop
} // end main function

