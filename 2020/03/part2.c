#include <stdio.h>
#include <string.h>
char lines[1024][1024];
int count = 0;
void checkPath(int x1, int y1) {
	int x = 0;
  int trees = 0;
  for(int y = 0; y < count; y += y1) {
      if(lines[y][x] == '#') {
      	trees++;
      }
      x = (x+x1) % strlen(lines[0]);
  }
  printf("%d\n", trees);
}
int main() {
  FILE *fp = fopen("data.txt", "r");
  while(fscanf(fp, "%s\n", lines[count++]) == 1);
  checkPath(1, 1);
  checkPath(3, 1);
  checkPath(5, 1);
  checkPath(7, 1);
  checkPath(1, 2);
  return 0;
}
