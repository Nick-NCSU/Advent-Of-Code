package AOC.Y2021.D09;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class part2_09 {
	public static void main(String[] args) {
		try {
			File file = new File("/home/nick/Desktop/aoc/src/AOC/Y2021/D09/data.txt");
			Scanner scanner = new Scanner(file);
			int[][] map = new int[100][100];
			int line = 0;
			while(scanner.hasNextLine()) {
				String[] stringArr = scanner.nextLine().split("");
				int[] intArr = Stream.of(stringArr).mapToInt(Integer::parseInt).toArray();
				map[line++] = intArr;
			}
			scanner.close();
			Set<Point> visits = new HashSet<>();
			List<Integer> basins = new ArrayList<>();
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					if (map[i][j] == 9) {
						continue;
					}
					Point point = new Point(i,j);
					if(!visits.contains(point)) {
						int size = 0;
						List<Point> list = new ArrayList<>();
						list.add(point);
						while(!list.isEmpty()) {
							Point p = list.remove(0);
							if(visits.contains(p)) {
								continue;
							}
							int x = p.x;
							int y = p.y;
							visits.add(p);
							size += 1;
							if(x > 0) {
								if(map[x - 1][y] != 9) {
									list.add(new Point(x - 1, y));
								}
							}
							if(y > 0) {
								if(map[x][y - 1] != 9) {
									list.add(new Point(x, y - 1));
								}
							}
							if(x < 99) {
								if(map[x + 1][y] != 9) {
									list.add(new Point(x + 1, y));
								}
							}
							if(y < 99) {
								if(map[x][y + 1] != 9) {
									list.add(new Point(x, y + 1));
								}
							}
						}
						basins.add(size);
					}
				}
			}
			Collections.sort(basins);
			int total = basins.size();
			System.out.println(basins.get(total - 1) * basins.get(total - 2) * basins.get(total - 3));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
