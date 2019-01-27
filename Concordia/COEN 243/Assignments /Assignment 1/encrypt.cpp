/*******************Programming Assignment 1***************/
/****************Caesar's substitution cipher**************/

/*****************YOU MUST EDIT THIS FILE******************/
/************Complete function encrypt below***************/

#include "encrypt.h"

using namespace std;

string encrypt (string text, int rot) {
    
    //limits the rot to a full alphabetical rotation
    rot = (rot%26);
    
    //iterating through every char in the string
    for(int i = 0; i < text.length(); i++){
        //checks if char is a letter, else it moves on
        if((text[i] >= 65 && text[i] <=90) || (text[i] >= 97 && text[i] <=122)){  
            // rotates every char until complete rotation is achieved 
            for(int j = 0; j <rot; j++){
                text[i]++;
                // if the letter overflows the ASCII letter space, back to A or a
                if(text[i] == 91 || text[i] == 123){
                    text[i] -=26;
                } 
            }
        }
    }
    
    return text;
} //end function encrypt

