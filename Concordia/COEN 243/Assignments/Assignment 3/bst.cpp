/*******************Programming Assignment 3***************/
/********************Binary Search Tree**********************/

/*****************YOU MUST EDIT THIS FILE**************/
/*****************YOU MUST SUBMIT THIS FILE**************/

/*********Implement the insert and remove functions of class bst***************/


#include "bst.h"

using namespace std;

bool bst::insert(string StudentName, int IDNumber) {
	node* temp = new node(StudentName, IDNumber);

	if (root == NULL){
		root = temp;
	}
	else {
		node* current = root;

		//as long as a proper place hasn't been found, keep searching
		while (true){
		
			if (current->ID == temp->ID){
				return false;
			}
			//if the temp ID is greater than the current node's ID, move right
			else if (current->ID < temp->ID){
				//if there's no right node, add it there.
				if (current->right == NULL){
					current->right = temp;
					temp->parent = current;
					return true;
				}
				else {
					current = current->right;
					continue;
				}
			}
			//the temp ID isn't equal nor bigger, so move left
			else {
				//if there's no left node, add it there.
				if (current->left == NULL){
					current->left = temp;
					temp->parent = current;
					return true;
				}
				else {
					current = current->left;
					continue;
				}
			}
		
		}
	
	}
	return true;

} // end insert

bool bst::remove(int StudentID) {
	bool right = true;
	node* parent = root;
	node* current = root;

	//searches for a reference to the node we're trying to delete
	while (current->ID != StudentID){
		parent = current;
		//goes left if smaller
		if (current->ID > StudentID){
			current = current->left;
			right = false;
		}
		//goes right if bigger
		else {
			current = current->right;
			right = true;
		}
		//if it runs into an empty pointer it tells us to go away
		if (current == NULL){
			return false;
		}
	}

	while (current != NULL){
		
		//leaf
		if (current->left == NULL && current->right == NULL){
			//if root, and root is leaf
			if (current == root){
				root = NULL;
				break;
			}
			else{
				if (right){
					parent->right = NULL;
					break;
				}
				else {
					parent->left = NULL;
					break;
				}
			}
		}

		//left child only
		if (current->left != NULL && current->right == NULL){
			if (current == root){
				root = current->left;
				break;
			}
			else {
				if (right){
					parent->right = current->left;
					break;
				}
				else {
					parent->left = current->left;
					break;
				}
			}
		}

		//right child only
		if (current->left == NULL && current->right != NULL){
			if (current == root){
				root = current->right;
				break;
			}
			else {
				if (right){
					parent->right = current->right;
					break;
				}
				else {
					parent->left = current->right;
					break;
				}
			}
		}

		//prioritize the left one because the right one is evil
		if (current->left != NULL && current->right != NULL){
			node* replaced = current->left;
			while (replaced->right != NULL){
				replaced = replaced->right;
			}

			//sending the left kid to their grand parents
			if (replaced->parent != current){
				replaced->parent->right = replaced->left;
			}
			

			//info about parent
			if (current == root){
				root = replaced;
				replaced->parent = NULL;
			}
			else {
				replaced->parent = current->parent;
			}

			//parent info about self
			if (current->parent != NULL){
				if (right){
					current->parent->right = replaced;
				}
				else {
					current->parent->left = replaced;
				}
			}

			//info about right child
			replaced->right = current->right;

			//right child info about self
			replaced->right->parent = replaced;

			cout << replaced->right->parent->name << endl;

			//info about left child
			if (current->left == replaced){
				cout << replaced->left->name << endl;
			}
			else {
				replaced->left = current->left;
			}

			//left child info about self
			replaced->left->parent = replaced;

			delete current;
			current = NULL;
		
		}

	}

	
	return true;

}// end remove function