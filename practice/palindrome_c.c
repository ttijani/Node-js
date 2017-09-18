#include "stdlib.h"
#include "stdio.h"
#include "string.h"

int main ()
	{
		char *str = "American";
		char *str_temp = malloc (strlen (str) + 1);

		strlcpy (str_temp, str, strlen (str) + 1);
		char cur_char;
		
		char *temp = str_temp + (strlen (str_temp) * sizeof (char) - sizeof (char));
		cur_char = *temp;

		while (*str != NULL)
			{
				printf("current char is : %c, and str is : %c\n", cur_char, *str);
				if (cur_char != *str)
					{
						printf ("failed like a motherfucker!\n");
						return 0;
					}
				temp--;
				cur_char = *temp;
				str++;
			}	

		printf ("ISSA PALINDROME!\n");
		return 1;
	}