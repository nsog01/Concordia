/*
* File:   DataStorage.h
* Author: Nathan
* Modified by: Eduard
* Created on April 5, 2014, 4:39 PM
* Modified on April 10, 2014, 5:33 PM
*/

#include "DataStorage.h"

DataStorage::DataStorage(){
    iPtr = nullptr;
	fPtr = nullptr;
	cPtr = nullptr;
    typeOfArray = 0;
}

DataStorage::DataStorage(string newType, int size){
    this->size = size;

    if (newType=="int"){
		iPtr = new int[size];
        fPtr = nullptr;
		cPtr = nullptr;
        typeOfArray=1;
	} else if (newType=="float"){
		iPtr = nullptr;
		fPtr = new float[size];
		cPtr = nullptr;
        typeOfArray=2;
	} else if (newType=="char"){
		iPtr = nullptr;
		fPtr = nullptr;
		cPtr = new char[size];
        typeOfArray=3;
	}
}

void DataStorage::changeType(string newType, int size){
	this->size = size;

	if (newType == "int"){
		iPtr = new int[size];
		fPtr = nullptr;
		cPtr = nullptr;
		typeOfArray = 1;
	}
	else if (newType == "float"){
		iPtr = nullptr;
		fPtr = new float[size];
		cPtr = nullptr;
		typeOfArray = 2;
	}
	else if (newType == "char"){
		iPtr = nullptr;
		fPtr = nullptr;
		cPtr = new char[size];
		typeOfArray = 3;
	}   

}

void DataStorage::addThing(int i, float f, char c, int pos){

	switch (typeOfArray){
		case 1: iPtr[pos] = i;
			break;
		case 2: fPtr[pos] = f;
			break;
		case 3: cPtr[pos] = c;
			break;
		default: cout << "error";
			break;
	}
}

void DataStorage::print(){

	for (int i=0; i<size; i++){
		switch (typeOfArray){
			case 1: cout << iPtr[i] << " ";
				break;
			case 2: cout << fPtr[i] << " ";
				break;
			case 3: cout << cPtr[i] << " ";
				break;
			default: cout<<"error";
				break;
		}
    }

    cout<<endl;
	
}

DataStorage::~DataStorage(){}