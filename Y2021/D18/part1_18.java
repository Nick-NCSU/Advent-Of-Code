package AOC.Y2021.D18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part1_18 {
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
        Number combinedNumber = snailfish.get(0);
        for(int i = 1; i < snailfish.size(); i++) {
            combinedNumber = Number.add(combinedNumber, snailfish.get(i));
        }
        System.out.println(combinedNumber.magnitude());
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

