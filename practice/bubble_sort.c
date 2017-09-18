#include "stdlib.h"
#include "stdio.h"
#include "string.h"
#include "stdbool.h"

int main ()
	{
		int a[10] = {1,2,3,4,5,6,7,8,9,0};
		bool done = false;
	  int limit = sizeof (a) / sizeof (a[0]);
		int index = 1;  /* Start from 1 so because of the -1. */
		int temp;
		int idx;

		for (int i = 0; i < limit; i++)
			{
				printf("original i : %d\n", a[i]);
			}
		/* Keep going till the end of the array */
		while (index < limit)
		{
			/* if the element at index is greater than that before it, set done to false. */
			idx = index;
			while (a[idx] <= a[idx - 1] && idx > 0)
				{
					temp = a[idx]; /* store the smaller number in temp */ 
					a[idx] = a[idx - 1]; /* Move the larger value to position - index*/
					a[idx - 1] = temp; /* Move the smaller number to position - index - 1 */
					idx--;
				}

			index++; /* Advance index. */ 
		}
		for (int i = 0; i < limit; i++)
			{
				printf("i : %d\n", a[i]);
			}
		
		return 1;
	}
