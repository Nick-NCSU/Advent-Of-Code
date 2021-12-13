package AOC.Y2021.D13;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class part1_13 {
    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D13/data.txt");
            Scanner scanner = new Scanner(file);
            Set<Point> graph = new HashSet<>();
            List<String[]> folds = new ArrayList<>();
            String line = scanner.nextLine();
            while(line.matches("\\d+,\\d+")) {
                int[] coords = Stream.of(line.split(",")).mapToInt(Integer::parseInt).toArray();
                graph.add(new Point(coords[0], coords[1]));
                line = scanner.nextLine();
            }
            while(scanner.hasNextLine()) {
                String[] fold = scanner.nextLine().split("=");
                folds.add(new String[] {fold[0].substring(fold[0].length() - 1), fold[1]});
            }
            scanner.close();
            for(String[] fold : folds) {
                if (fold[0].equals("x")) {
                    graph = x(graph, Integer.parseInt(fold[1]));
                } else {
                    graph = y(graph, Integer.parseInt(fold[1]));
                }
                System.out.println(graph.size());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Set<Point> y(Set<Point> s, int y) {
        Set<Point> set = new HashSet<>();
        for(Point p : s) {
            if(p.y < y) {
                set.add(p);
            } else {
                Point p2 = new Point(p.x, 2*y-p.y);
                set.add(p2);
            }
        }
        return set;
    }

    public static Set<Point> x(Set<Point> s, int x) {
        Set<Point> set = new HashSet<>();
        for(Point p : s) {
            if(p.x < x) {
                set.add(p);
            } else {
                Point p2 = new Point(2*x-p.x, p.y);
                set.add(p2);
            }
        }
        return set;
    }
}
