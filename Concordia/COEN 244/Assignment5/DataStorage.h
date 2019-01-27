/* 
 * File:   DataStorage.h
 * Author: Nathan
 * Modified by: Eduard
 * Created on April 5, 2014, 4:39 PM
 * Modified on April 10, 2014, 4:00 PM
 */
#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;


#ifndef DATASTORAGE_H
#define	DATASTORAGE_H
class DataStorage{
private:
    int *iPtr;
    float *fPtr;
    char *cPtr; 
    int size;    
	int typeOfArray;
public:
    DataStorage();
    DataStorage(string, int);
    ~DataStorage();
    void changeType(string, int);
    void addThing(int i=0, float f=0.0, char c=' ', int pos=0);
    void print();
    
};

#endif	/* DATASTORAGE_H */

