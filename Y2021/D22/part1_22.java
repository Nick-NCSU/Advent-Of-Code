package AOC.Y2021.D22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part1_22 {
    public static void main(String[] args) {
        boolean[][][] cuboid = new boolean[101][101][101];
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D22/data.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                boolean on = line[0].charAt(1) == 'n';
                int xmin = 50, ymin = 50, zmin = 50, xmax = 50, ymax = 50, zmax = 50;
                Pattern r = Pattern.compile("x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)");
                Matcher m = r.matcher(line[1]);
                m.find();
                xmin += Integer.parseInt(m.group(1));
                xmax += Integer.parseInt(m.group(2));
                ymin += Integer.parseInt(m.group(3));
                ymax += Integer.parseInt(m.group(4));
                zmin += Integer.parseInt(m.group(5));
                zmax += Integer.parseInt(m.group(6));
                xmin = Math.max(xmin, 0);
                ymin = Math.max(ymin, 0);
                zmin = Math.max(zmin, 0);
                xmax = Math.min(xmax, 101);
                ymax = Math.min(ymax, 101);
                zmax = Math.min(zmax, 101);
                for(int i = xmin; i <= xmax; i++) {
                    for(int j = ymin; j <= ymax; j++) {
                        for(int k = zmin; k <= zmax; k++) {
                            cuboid[i][j][k] = on;
                        }
                    }
                }
            }
            long count = 0;
            for(boolean[][] width : cuboid) {
                for(boolean[] height : width) {
                    for(boolean depth : height) {
                        if(depth)
                            count++;
                    }
                }
            }
            System.out.println(count);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
