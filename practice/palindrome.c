#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int palindrome (char *str) {
	int size = strlen(str);
	char temp[size];
	printf("size: %i\n", size);
	for (int index = 0; index < size; index++) {	
		temp[index] = str[size - index - 1];
		printf("%c\n", temp[index]);
	}
	temp[size] = '\0';
	int result = strcmp(str, temp);
	return result;
}

int main() {
	char str[] = "bit";
	printf("sizeof: %lu\n", sizeof(str));
	int result = palindrome(str);
	printf("%i\n", result);
	return result;
}
	 
