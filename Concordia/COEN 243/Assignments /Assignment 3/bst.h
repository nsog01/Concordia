/*******************Programming Assignment 3***************/
/********************Binary Search Tree**********************/

/*****************YOU MUST NOT EDIT THIS FILE**************/
/*****************YOU MUST NOT SUBMIT THIS FILE**************/

/*********Definition of node and bst classes**************************/
/************You are NOT allowed to change class definitions************/

#include <iostream>
#include <string>
using namespace std;


class node{
public:
    int ID;
    string name;
    class node *left, *right, *parent;
    node (string StudentName, int IDNumber) {
        name = StudentName;
        ID = IDNumber; // key value
        left = NULL;
        right = NULL;
		parent = NULL;
    }
}; // end class node

class bst {
	class node *root; // root of the tree

	// print in ascending order of IDs
    void printInOrder(class node *n) {
		if (n == NULL) {
			//nothing to print
			return;
		}
		// recursively print left sub-tree
		printInOrder(n->left);
		// print this node;
		cout << "<" << n->ID << ", " << n->name << ">" << endl;
		// recursively print right subtree
		printInOrder(n->right);
    }

public:
    bst() {
        root = NULL;
    }
	void print() {
		printInOrder(root);
	}

    bool insert(string StudentName, int IDNumber); 
	// returns true if successfully inserted 
	// otherwise returns false (if matching ID exists in BST)
	
    bool remove(int StudentID); 
	// returns true if successfully deleted 
	// otherwise returns false (if matching ID is not found)
}; // end class bst