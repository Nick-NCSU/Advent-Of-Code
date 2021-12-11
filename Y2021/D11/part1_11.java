package AOC.Y2021.D11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class part1_11 {
    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D11/data.txt");
            Scanner scanner = new Scanner(file);
            int[][] oct = new int[10][10];
            int c = 0;
            while(scanner.hasNextLine()) {
                int[] line = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
                oct[c++] = line;
            }
            scanner.close();
            int flash = 0;
            for(int k = 0; k < 100; k++) {
                for(int i = 0; i < 10; i++) {
                    for(int j = 0; j < 10; j++) {
                        oct[i][j]++;
                    }
                }
                boolean change = true;
                int[][] flashed = new int[10][10];
                while(change) {
                    change = false;
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (oct[i][j] > 9) {
                                flash++;
                                oct[i][j] = -10;
                                flashed[i][j] = 1;
                                change = true;
                                for(int l = i - 1; l <= i + 1; l++) {
                                    for(int m = j - 1; m <= j + 1; m++) {
                                        if(l < 0 || l > 9 || m < 0 || m > 9) {
                                            continue;
                                        }
                                        oct[l][m]++;
                                    }
                                }
                            }
                        }
                    }
                }
                for(int i = 0; i < 10; i++) {
                    for(int j = 0; j < 10; j++) {
                        if(flashed[i][j] == 1) {
                            oct[i][j] = 0;
                        }
                    }
                }
            }
            System.out.println(flash);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
