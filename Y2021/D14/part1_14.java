package AOC.Y2021.D14;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class part1_14 {
    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D14/data.txt");
            Scanner scanner = new Scanner(file);
            Dictionary<String, String> pairs = new Hashtable<>();
            String template = scanner.nextLine();
            scanner.nextLine();
            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" -> ");
                pairs.put(line[0], line[1]);
            }
            scanner.close();
            for(int i = 0; i < 10; i++) {
                String temp = "";
                for(int j = 0; j < template.length() - 1; j++) {
                    String t = template.substring(j, j + 2);
                    if(pairs.get(t) != null) {
                        temp += template.charAt(j) + pairs.get(t);
                    } else {
                        temp += template.charAt(j);
                    }
                }
                template = temp + template.charAt(template.length() - 1);
            }
            Map<Character, Integer> chars = new HashMap<>();
            for(char c : template.toCharArray()) {
                if(chars.containsKey(c)) {
                    chars.put(c, chars.get(c) + 1);
                } else {
                    chars.put(c, 1);
                }
            }
            System.out.println(Arrays.toString(chars.entrySet().toArray()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
