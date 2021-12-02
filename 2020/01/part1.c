#include <stdio.h>

int main() {
    FILE *fp = fopen("data.txt", "r");
    int nums[200];
    int count = 0;
    int num;
    while(fscanf(fp, "%d\n", &num) != 0) {
        nums[count++] = num;
    }
    printf("%d", 17);
    for(int i = 0; i < count - 1; i++) {
        for(int j = i + 1; j < count; j++) {
            if(nums[i] + nums[j] == 2020) {
                printf("%d", nums[i] * nums[j]);
                return 0;
            }
        }
    }

    return 0;
}