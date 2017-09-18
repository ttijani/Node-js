#include <stdio.h>
#include <stdlib.h>

int primeCheck(int value) {
	int div;
	int result;
	if (value > 0 && value < 4) {
		return 1;
	}	
	for (div = 2; div < value; div++) {
		if (value % div == 0) {
			return 0;
		}
	}
	return 1;
}

int main() {	
	int value = primeCheck(10);
	if (value) {
		printf("Number is Prime.\n");
	} else {
		printf("Number is NOT Prime.\n");
	}
	return 0;
}
