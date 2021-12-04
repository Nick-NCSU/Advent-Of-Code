#include <stdio.h>

int main() {
    FILE *fp = fopen("data.txt", "r");
    int nums[1024];
    int count = 0;
    int num;
    while(fscanf(fp, "%d\n", &num) == 1) {
        nums[count++] = num;
    }
    printf("%d\n", count);
    for(int i = 0; i < count - 1; i++) {
        for(int j = i + 1; j < count; j++) {
            for(int k = j + 1; k < count; k++) {
		if(nums[i] + nums[j] + nums[k] == 2020) {
		        printf("%d\n", nums[i] * nums[j] * nums[k]);
		        return 0;
		}
	     }
	}
    }
    return 0;
}
