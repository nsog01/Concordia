#include <iostream>

int main() {
	const int a[4] = { 0, 1, 2, 3 };
	const int *x = &(a[3]);
	x--;
	cout << *x;
}
