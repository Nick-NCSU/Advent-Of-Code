package AOC.Y2021.D15;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class part1_15 {
    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D15/data.txt");
            Scanner scanner = new Scanner(file);
            int[][] grid = new int[100][100];
            int n = 0;
            while (scanner.hasNextLine()) {
                int[] line = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
                grid[n++] = line;
            }
            scanner.close();
            PriorityQueue<Node> queue = new PriorityQueue<>();
            Set<Point> seen = new HashSet<>();
            queue.add(new Node(new Point(0,0), 0));
            int best = Integer.MAX_VALUE;
            while(!queue.isEmpty()) {
                Node node = queue.remove();
                Point p = node.point;
                int x = p.x;
                int y = p.y;
                if(node.value < best) {
                    for(int i = x - 1; i <= x + 1; i += 2) {
                        if(i >= 0 && i < 100) {
                            if(i == 99 && y == 99) {
                                System.out.println(Math.min(best, node.value + grid[i][y]));
                                return;
                            }
                            if(!seen.contains(new Point(i, y))) {
                                seen.add(new Point(i, y));
                                queue.add(new Node(new Point(i, y), node.value + grid[i][y]));
                            }
                        }
                    }
                    for(int i = y - 1; i <= y + 1; i += 2) {
                        if(i >= 0 && i < 100) {
                            if(x == 99 && i == 99) {
                                System.out.println(Math.min(best, node.value + grid[x][i]));
                                return;
                            }
                            if(!seen.contains(new Point(x, i))) {
                                seen.add(new Point(x, i));
                                queue.add(new Node(new Point(x, i), node.value + grid[x][i]));
                            }
                        }
                    }
                }
            }
            System.out.println(best);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

