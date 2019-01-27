/*******************Programming Assignment 4***************/
/********************Text Justification**********************/

/*****************YOU MUST NOT SUBMIT THIS FILE**************/

#include "studentID.h"
#include "justify.h"

int main() {
	ERROR result;
	unsigned int width;
	string input;
	PARA output;

	while (1) {
		// first clear the output string
		output.clear();
		cout << endl<< "Welcome to text justification!" << endl << endl;
		cout << "Enter the output line width (between " << MIN_COL_WIDTH << " and " << MAX_COL_WIDTH << ", or 0 to exit): ";
		cin >> width;
		if (width == 0)
			break;
		cout << "Enter the string to justify below:" << endl << endl;

		// read in the input string 
		/*cin.ignore();

		getline(cin, input);*/

		input = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

		// justify the input for given column width
		result = justify(width, input, &output);

		switch(result) {
			case ERROR_NONE: 
				cout << endl << "The justified text is:" << endl << endl;
				for (unsigned int i = 0; i < output.size(); i++)
					cout << output[i] << endl;
				break;
			case ERROR_NO_CHARS:
				cout << "ERROR: No characters in input para!" << endl;
				break;
			case ERROR_ILLEGAL_WIDTH:
				cout << "ERROR: Column width out of range!" << endl;
				break;
			default:
				cout << "ERROR: Internal error!" << endl;
				break;
		}
	} // enter interactive loop
}
