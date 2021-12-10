package AOC.Y2021.D08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part1_08 {
    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D08/data.txt");
            Scanner scanner = new Scanner(file);
            List<String[]> lines = new ArrayList<>();
            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine().split(" \\| "));
            }
            scanner.close();
            int count = 0;
            for(String[] s : lines) {
                for(String s2 : s[1].split(" ")) {
                    switch(s2.length()) {
                        case 2:
                        case 4:
                        case 3:
                        case 7:
                            count++;
                            break;
                    }
                }
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

}
