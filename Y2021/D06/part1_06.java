package AOC.Y2021.D06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part1_06 {
    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D06/data.txt");
            Scanner scanner = new Scanner(file);
            List<Integer> fish = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String[] string = scanner.nextLine().split(",");
                for(String s : string) {
                    fish.add(Integer.parseInt(s));
                }
            }
            scanner.close();
            for(int i = 0; i < 80; i++) {
                int size = fish.size();
                for(int j = 0; j < size; j++) {
                    if(fish.get(j) == 0) {
                        fish.set(j, 7);
                        fish.add(8);
                    }
                    fish.set(j, fish.get(j) - 1);
                }
            }
            System.out.println(fish.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

}
