#include <stdio.h>
#include <string.h>

int main() {
	FILE *fp = fopen("data.txt", "r");
	int min, max;
	char c;
	char password[100];
	int count = 0;
	while(fscanf(fp, "%d-%d %c: %s\n", &min, &max, &c, password) == 4) {
		int match = 0;
		if(password[min - 1] == c) {
			match++;
		}
		if(password[max - 1] == c) {
			match++;
		}
		if(match == 1) {
			count++;
		}
	}
	printf("%d\n", count);
	return 0;
}
