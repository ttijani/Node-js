#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char date[11];

//gets the date given from the command line.
void readDate () 
{
	//char date[10];  //buffer to store the dates.
	gets(date); //reads the line containing dates.
}

int isLeap (int year, int month) 
{
	if (year % 4 == 0) //must be divisible by 4 to be leap.
	{
		if (year % 100 != 0) //if it is indivisible by 100, it is leap
		{
			if (month == 2) //if the date is in feb, special case.
				return 1;
		}
		else 
		{
			if (year % 400 == 0) //if it is divisible by 400 too
		    {
				if (month == 2) //if the date is in feb, special case.
					return 1;
		    }
		}	
	}
	return 0;
}

int contains (int month, int *group) 
{
	int monthIdx = 0;
	while (monthIdx < sizeof(group))
	{
		if (month == group[monthIdx])
			return 1;
		monthIdx++;
	}
	return 0;
}

int getDays (int month, int day)
{
	int group1[] = {1, 3, 5, 7, 8, 10, 12};
	int group2[] = {4, 6, 9, 11};
	int group3[] = {2};

	if (contains (month, group1))
	{
		return 31;
	}
	else if (contains (month, group2))
	{
		return 30;
	}
	return 28;
}

void printArray (char *array)
{
	for(int i = 0; i < 10; i++) {
        printf("%c ", array[i]);
	    printf("\n");
	} 
}

int main () 
{
	printf("HERE!\n");
	readDate();

	int month, day, year;
	
	printArray (date);

	//gets the month value
	strcat (&date[0], &date[1]);
	printf("%c\n", date[0]);
	month = atoi (&date[0]);  
	//gets the day value
	strcat (&date[3], &date[4]);
	day = atoi (&date[3]);
	//gets the year value
	strcat (&date[6], &date[7]);
	strcat (&date[6], &date[8]);
	strcat (&date[6], &date[9]);
	year = atoi (&date[6]);

	printf("AFTER date!\n");

	//today's date for calculations.
	int monthT, dayT, yearT;
	monthT = 1;
	dayT = 8; //3
	yearT = 2017;
	int dayNum, monthNum, yearNum = 0;
	int monthIdx = month;

	
	if (isLeap (year, month)) 
	{
		if (day == 29) //calculate the age in 4-year intervals.
		{

		}
	} 
	else 
	{
		yearNum = yearT - year - 1; //minus 1 to account for incomplete month cycle.
		monthNum = monthT - month; //the number of months is approx. present month minus the birthday month. 
		//if month num is less than 0, then the number of months left is less than a year!

		if (monthNum < 0) 
		{
			monthNum *= -1; //convert the dofference to positive.
			monthNum -= 1; //subtract 1 to account for incomplete final month 
			dayNum += dayT; //add today's date to dayNum
			dayNum += getDays (monthT - 1, dayT) - day; //add the days left after the birthday day from the previous month.
			if (dayT == day) //if today is the birthday
			{
				monthNum += 1;  //increment the month count
				dayNum = 0; //set the day count to 0.
			}
			else if (dayT > day)  //it is past a complete month, 
			{
				monthNum += 1;
				dayNum -= day;  //subtract today's date from the birth date.
			}
		}
	} 

}