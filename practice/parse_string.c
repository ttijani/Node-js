#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "stdbool.h"



int
parse_path (const char *path, char **dir, char *file_name, bool absolute)
{

  char *token, *save_ptr, *last_token = "";
  int length = strlen (path);
  char *temp = (char *) malloc (sizeof(char) * (length + 1));
  memcpy (temp, path, sizeof(char) * (length + 1));

  /* Check if absolute path */
  if (path [0] == '/')
    absolute = true;

  token = strtok_r (temp, "/", &save_ptr);

  int i = 0;

  while (token != NULL)
  {
    if (strlen (last_token) <= 14)
    {
      /* Make the index point to the current token */
      dir[i] = token;
    }

    printf("token in %d : %s\n", i, dir[i]);
    i++;
    last_token = token;
    
    token = strtok_r (NULL, "/", &save_ptr);
  }
  // *dir = '\0';
  printf("HERE  AA KKK in America \n");
  /* Make a copy of the file/directory name */
  memcpy (file_name, last_token, sizeof(char) * (strlen (last_token) + 1));
  free (temp);

  return i - 1;
}


/* David's shenanigans */
void
get_dir_from_path (const char *path, char **dir, char *file_name, bool absolute)
{

  int index = parse_path (path, dir, file_name, absolute);
  int i = 0;
  printf("HERE\n");
  if (absolute)
    printf("absolute\n");
  while (i <= index)
  {
    printf("%s is at index %d\n", dir[i], i);
    char *val = dir[i];

    i++;
  }
}

int 
main ()
{
  char *path = "a/b/b/b/b/bb/b/bb/mm";
  int length = 9;
  char **dir = (char **) malloc (sizeof (char *) * length);
  char *filename = (char *) malloc (sizeof (char *) * 14);
  bool absolute = false;

  get_dir_from_path (path, dir, filename, absolute);

  printf("filename is %s\n", filename);

}
