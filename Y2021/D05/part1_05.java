package AOC.Y2021.D05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class part1_05 {
    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D05/data.txt");
            Scanner scanner = new Scanner(file);
            int[][] board = new int[1000][1000];
            while(scanner.hasNextLine()) {
                String[] string = scanner.nextLine().split(" -> ");
                int x1, x2, y1, y2;
                x1 = Integer.parseInt(string[0].split(",")[0]);
                x2 = Integer.parseInt(string[1].split(",")[0]);
                y1 = Integer.parseInt(string[0].split(",")[1]);
                y2 = Integer.parseInt(string[1].split(",")[1]);
                if((x1!=x2) && (y1!=y2)) {
                    continue;
                } else if(x1 != x2) {
                    for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                        board[x][y1]++;
                    }
                } else {
                    for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                        board[x1][y]++;
                    }
                }
            }
            scanner.close();
            int count = 0;
            for(int[] row : board) {
                for(int point : row) {
                    if(point >= 2) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

}
