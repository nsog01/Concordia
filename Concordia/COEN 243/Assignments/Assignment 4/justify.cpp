/*******************Programming Assignment 4***************/
/********************Text Justification**********************/

/*****************YOU MUST EDIT THIS FILE**************/
/*****************YOU MUST SUBMIT THIS FILE**************/

/*********Implement the justify function below***************/

#include "justify.h"

// implement the justify function below
// Hint: use push_back method of string class to insert a new character
// at the end of a string
// You can terminate a string by calling push_back on it with '\0'.
// Use the clear method of string class to remove all the
// characters from the string.

ERROR justify(unsigned int width, string in, PARA *out) {

	//error checking
	if (in == "" || in == " "){
		return 1;
	}
	if (!(width >= MIN_COL_WIDTH && width <= MAX_COL_WIDTH)){
		return 2;
	}	

	//max counter
	int total = 0;
	while (in[total] != '\0'){
		total++;
	}
	int overcounter = 0;

	//temp line
	string buffer;
	int linecounter = 0;
	
	char alpha = NULL;
	char beta = NULL;
	char gamma = NULL;
	

	//justifier
	while (overcounter != total){
		buffer += in[overcounter];
		char alpha = NULL;
		char beta = NULL;

		if (total != (overcounter + 1)){
			alpha = in[overcounter + 1];
			if (total != (overcounter + 2)){
				beta = in[overcounter + 2];
			}		
		}

		//one last letter before line end
		if (linecounter == (width - 1)){
			if ((alpha > 64 && alpha < 91) || (alpha > 96 && alpha < 123)){
				if ((beta > 64 && beta < 91) || (beta > 96 && beta < 123)){
				}
			} else if (alpha == 32){
				overcounter++;
			}
			else{
				buffer += alpha;
				overcounter += 2;
			}

		}

		if (linecounter == (width - 2)){
			if (alpha != NULL && beta != NULL){

				if (alpha == 32){
					if ((beta > 64 && beta < 91) || (beta > 96 && beta < 123)){
						//buffer += alpha;
						linecounter+=3;
						overcounter++;
					}
				} else if ((alpha > 64 && alpha < 91) || (alpha > 96 && alpha < 123)){
					if ((beta > 64 && beta < 91) || (beta > 96 && beta < 123)){
						linecounter++;
						buffer += '-';
					} 
				}else {
					
				}

			}
		}

		overcounter++;
		linecounter++;

		
		
		//max width reached;
		if (linecounter >= width){

			//error correction for " -" ending 
			if (buffer[buffer.length() - 1] == '-'){
				if (buffer[buffer.length() - 2] == ' '){
					buffer.pop_back();
					buffer.pop_back();
				}
			}


			out->push_back(buffer);
			linecounter = 0;
			buffer = "";
		}

	}

	out->push_back(buffer);

	return 0;
}
