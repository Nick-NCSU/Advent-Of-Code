package AOC.Y2021.D19;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class part2_19 {
	public static void main(String[] args) {
		try {
			int[][][] scannerArr = Arrays.stream(Files.readString(Path.of("/home/nick/Desktop/Advent-Of-Code/Y2021/D19/data.txt")).replaceAll("--- scanner \\d+ ---\n", "").split("\\n\\n")).map(s -> s.split("\n")).map(s -> Stream.of(s).map(s2 -> Stream.of(s2.split(",")).mapToInt(Integer::parseInt).toArray()).collect(Collectors.toList()).toArray(new int[][] {})).collect(Collectors.toList()).toArray(new int[][][] {});
			List<Scanner> scanners = new ArrayList<>();
			for(int[][] scanner : scannerArr) {
				Scanner s = new Scanner();
				for(int[] beacon : scanner) {
					s.add(beacon[0], beacon[1], beacon[2]);
				}
				scanners.add(s);
			}
			List<Scanner> known = new ArrayList<>();
			scanners.get(0).location = new Point3D(0, 0, 0);
			known.add(scanners.remove(0));
			loop:
			while(scanners.size() != 0) {
				for(Scanner scanner : known) {
					for(Scanner scanner2 : scanners) {
						if(scanner.compare(scanner2)) {
							known.add(scanner2);
							scanners.remove(scanner2);
							continue loop;
						}
					}
				}
			}
			scanners = known;
			int max = 0;
			for(Scanner s : scanners) {
				for(Scanner s2 : scanners) {
					max = Math.max(max, Point3D.manhattan(s.location, s2.location));
				}
			}
			System.out.println(max);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
