package AOC.Y2021.D20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class part1_20 {
    static boolean state = false;
    static String algorithm = "";

    public static void main(String[] args) {
        List<List<String>> lines = new ArrayList<>();
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D20/data.txt");
            Scanner scanner = new Scanner(file);
            algorithm = scanner.nextLine().replaceAll("#", "1").replaceAll("\\.", "0");
            scanner.nextLine();
            while(scanner.hasNextLine()) {
                lines.add(new ArrayList<>(Arrays.asList(scanner.nextLine().replaceAll("#", "1").replaceAll("\\.", "0").split(""))));
            }
            grow(lines);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int t = 0; t < 2; t++) {
            List<List<String>> temp = new ArrayList<>();
            for(int i = 0; i < lines.size(); i++) {
                List<String> line = new ArrayList<>();
                for (int j = 0; j < lines.size(); j++) {
                    String num = "";
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k < 0 || k >= lines.size() || l < 0 || l >= lines.size()) {
                                num += state ? algorithm.substring(0, 1) : "0";
                            } else {
                                num += lines.get(k).get(l);
                            }
                        }
                    }
                    int idx = Integer.parseInt(num, 2);
                    line.add(algorithm.substring(idx, idx + 1));
                }
                temp.add(line);
            }
            state = !state;
            grow(temp);
            lines = temp;
        }
        int count = 0;
        for(List<String> line : lines) {
            for(String c : line) {
                if(c.equals("1")) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void grow(List<List<String>> list) {
        String s = state ? algorithm.substring(0, 1) : "0";
        int n = list.size();
        List<String> first = new ArrayList<>();
        List<String> last = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            first.add(s);
            last.add(s);
        }
        list.add(0, first);
        list.add(last);
        for(List<String> line : list) {
            line.add(0, s);
            line.add(s);
        }
    }
}
