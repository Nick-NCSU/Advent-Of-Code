#include <stdio.h>
#include <string.h>

int main() {
    FILE *fp = fopen("data.txt", "r");
    char lines[1024][1024];
    int count = 0;
    while(fscanf(fp, "%s\n", lines[count++]) == 1);
    int x = 0;
    int trees = 0;
    for(int y = 0; y < count; y++) {
        if(lines[y][x] == '#') {
        	trees++;
        }
        x = (x+3) % strlen(lines[0]);
    }
    printf("%d\n", trees);
    return 0;
}
