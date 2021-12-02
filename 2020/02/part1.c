#include <stdio.h>
#include <string.h>

int main() {
	FILE *fp = fopen("data.txt", "r");
	int min, max;
	char c;
	char password[100];
	int count = 0;
	while(fscanf(fp, "%d-%d %c: %s\n", &min, &max, &c, password) == 4) {
		int ccount = 0;
		for(int i = 0; i < strlen(password); i++) {
			if(password[i] == c) {
				ccount++;
			}
		}
		if(ccount >= min && ccount <= max) {
			count++;
		}
	}
	
	printf("%d\n", count);
	return 0;
}
