package AOC.Y2021.D09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class part1_09 {
    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D09/data.txt");
            Scanner scanner = new Scanner(file);
            int[][] map = new int[100][100];
            int line = 0;
            while(scanner.hasNextLine()) {
                String[] stringArr = scanner.nextLine().split("");
                int[] intArr = Stream.of(stringArr).mapToInt(Integer::parseInt).toArray();
                map[line++] = intArr;
            }
            scanner.close();
            int risk = 0;
            for(int i = 0; i < 100; i++) {
                for(int j = 0; j < 100; j++) {
                    if(i > 0) {
                        if(map[i - 1][j] <= map[i][j]) {
                            continue;
                        }
                    }
                    if(j > 0) {
                        if(map[i][j - 1] <= map[i][j]) {
                            continue;
                        }
                    }
                    if(i < 99) {
                        if(map[i + 1][j] <= map[i][j]) {
                            continue;
                        }
                    }
                    if(j < 99) {
                        if(map[i][j + 1] <= map[i][j]) {
                            continue;
                        }
                    }
                    risk += 1 + map[i][j];
                }
            }
            System.out.println(risk);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

}
