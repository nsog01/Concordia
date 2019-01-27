/* 
 * File:   IOdriver.cpp
 * Author: Nathan
 * Modified by: Eduard
 * Created on April 5, 2014, 5:11 PM
 * Modified on April 10, 2014, 3:05 PM
 */

#include <cstdlib>
#include <fstream>
#include <iostream>
#include <sstream>

#include "DataStorage.h"

using namespace std;

int main(int argc, char** argv) {
	
	
	//Line count -------------------------------------------START
	int number_of_lines = 0;
	string line;
	try {
		ifstream file("config.dat");
		if (!file)
			throw "File not found";
		while (getline(file, line))
			++number_of_lines;
		file.close();
	}
	catch (char * str){
		cout << "Exception: " << str << endl;
		cout << "Enter any digit to exit" << endl;
		std::cin >> number_of_lines;
		exit(1);
	}

	
	//Line count -------------------------------------------END
	
	DataStorage *dsPtr = new DataStorage[number_of_lines];
	DataStorage *point;
	ifstream inFile("config.dat");

	string typeOfDS;
	int count;

	int tempI;
	float tempF;
	char tempC;

	//scrolling through the number of lines the file has
	for (int i = 0; i < number_of_lines; i++){
		
		//grabing type and count
		inFile >> typeOfDS;
		inFile >> count;

		//changing the type of the storage unit
		dsPtr[i].changeType(typeOfDS, count);

		//sending every element to the storage unit
		//universal method "addThing()"
		for (int j = 0; j<count; j++){
			if (typeOfDS == "int"){
				inFile >> tempI;
				//cout << "Storing an int: " << tempI << endl;
				dsPtr[i].addThing(tempI, 0, 0, j);
			}
			else if (typeOfDS == "float"){
				inFile >> tempF;
				//cout << "Storing a float: " << tempF << endl;
				dsPtr[i].addThing(0, tempF, 0, j);
			}
			else {
				inFile >> tempC;
				//cout << "Storing a char: " << tempC << endl;
				dsPtr[i].addThing(0, 0, tempC, j);
			}
		}

	}

	inFile.close();

	//display loop
	for (int i = 0; i < number_of_lines; i++){
		dsPtr[i].print();
	}

	cout << endl << "Input any key to exit" << endl;
	cin >> count;

	
    return 0;
}