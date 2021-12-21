package AOC.Y2021.D18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part2_18 {
    public static void main(String[] args) {
        List<String> snailfishStr = new ArrayList<>();
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D18/data.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                snailfishStr.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Number> snailfish = parseLines(snailfishStr);
        int max = 0;
        for(int i = 0; i < snailfish.size(); i++) {
            for(int j = 0; j < snailfish.size(); j++) {
                snailfish = parseLines(snailfishStr);
                if(i != j) {
                    max = Math.max(max, Number.add(snailfish.get(i), snailfish.get(j)).magnitude());
                }
            }
        }
        System.out.println(max);
    }

    public static List<Number> parseLines(List<String> lines) {
        List<Number> snailfish = new ArrayList<>();
        for(String numberStr : lines) {
            Number number = new Number();
            int depth = 0;
            for(char c : numberStr.toCharArray()) {
                switch(c) {
                    case '[':
                        depth++;
                        break;
                    case ']':
                        depth--;
                        break;
                    default:
                        if(c != ',')
                            number.addSnailfish(Character.getNumericValue(c), depth);
                        break;
                }
            }
            snailfish.add(number);
        }
        return snailfish;
    }
}

