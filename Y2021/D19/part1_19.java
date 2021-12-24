package AOC.Y2021.D19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class part1_19 {
    public static void main(String[] args) {
        try {
            int[][][] scannerArr = Arrays.stream(Files.readString(Path.of("/home/nick/Desktop/Advent-Of-Code/Y2021/D19/data.txt")).replaceAll("--- scanner \\d+ ---\n", "").split("\\n\\n")).map(s -> s.split("\n")).map(s ->Stream.of(s).map(s2 -> Stream.of(s2.split(",")).mapToInt(Integer::parseInt).toArray()).collect(Collectors.toList()).toArray(new int[][] {})).collect(Collectors.toList()).toArray(new int[][][] {});
            List<Scanner> scanners = new ArrayList<>();
            for(int[][] scanner : scannerArr) {
                Scanner s = new Scanner();
                for(int[] beacon : scanner) {
                    s.add(beacon[0], beacon[1], beacon[2]);
                }
                scanners.add(s);
            }
            Set<Integer> known = new HashSet<>();
            known.add(0);
            scanners.get(0).location = new Point3D(0, 0, 0);
            while(known.size() != scanners.size()) {
                Set<Integer> found = new HashSet<>();
                for(int scanner : known) {
                    for(int i = 0; i < scanners.size(); i++) {
                        if(known.contains(i)) {
                            continue;
                        }
                        if(scanners.get(scanner).compare(scanners.get(i))) {
                            found.add(i);
                        }
                    }
                }
                known.addAll(found);
            }
            Set<String> totalSet = new HashSet<>();
            for(Scanner s : scanners) {
                for(Point3D beacon : s.beacons) {
                    totalSet.add(beacon.toString());
                }
            }
            System.out.println(totalSet.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
