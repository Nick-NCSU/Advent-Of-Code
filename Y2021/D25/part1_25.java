package AOC.Y2021.D25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part1_25 {
    public static void main(String[] args) {
        char[][] cucumbers = new char[0][0];
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D25/data.txt");
            Scanner scanner = new Scanner(file);
            List<char[]> cucumberList = new ArrayList<>();
            while(scanner.hasNextLine()) {
                cucumberList.add(scanner.nextLine().toCharArray());
            }
            cucumbers = cucumberList.toArray(new char[0][0]);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int step = 0;
        boolean moved = true;
        while(moved) {
            step++;
            moved = false;
            for(int i = 0; i < cucumbers.length; i++) {
                boolean movedFirst = false;
                for(int j = 0; j < cucumbers[0].length; j++) {
                    if(cucumbers[i][j] == '>') {
                        if(j < cucumbers[0].length - 1 && cucumbers[i][j + 1] == '.') {
                            if(j == 0) {
                                movedFirst = true;
                            }
                            moved = true;
                            cucumbers[i][j] = '.';
                            cucumbers[i][j + 1] = '>';
                            j++;
                        } else if (j == cucumbers[0].length - 1 && !movedFirst && cucumbers[i][0] == '.') {
                            moved = true;
                            cucumbers[i][j] = '.';
                            cucumbers[i][0] = '>';
                        }
                    }
                }
            }
            for(int j = 0; j < cucumbers[0].length; j++) {
                boolean movedFirst = false;
                for(int i = 0; i < cucumbers.length; i++) {
                    if(cucumbers[i][j] == 'v') {
                        if(i < cucumbers.length - 1 && cucumbers[i + 1][j] == '.') {
                            if(i == 0) {
                                movedFirst = true;
                            }
                            moved = true;
                            cucumbers[i][j] = '.';
                            cucumbers[i + 1][j] = 'v';
                            i++;
                        } else if (i == cucumbers.length - 1 && !movedFirst && cucumbers[0][j] == '.') {
                            moved = true;
                            cucumbers[i][j] = '.';
                            cucumbers[0][j] = 'v';
                        }
                    }
                }
            }
        }
        System.out.println(step);
    }
}
