package AOC.Y2021.D15;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class part2_15 {
	public static void main(String[] args) {
		try {
			File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D15/data.txt");
			Scanner scanner = new Scanner(file);
			int[] line = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
			int nLine = 0;
			int[][] tempGrid = new int[line.length][line.length];
			tempGrid[nLine++] = line;
			while (scanner.hasNextLine()) {
				tempGrid[nLine++] = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
			}
			scanner.close();
			int[][] increments =  {
					{0,1,2,3,4},
					{1,2,3,4,5},
					{2,3,4,5,6},
					{3,4,5,6,7},
					{4,5,6,7,8}
			};
			int[][] grid = new int[5*tempGrid.length][5*tempGrid.length];
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					for(int k = 0; k < tempGrid.length; k++) {
						for(int l = 0; l < tempGrid.length; l++) {
							int n = tempGrid[k][l] + increments[i][j];
							if(n >= 10) {
								n++;
							}
							grid[i*tempGrid.length+ k][j*tempGrid.length+l] = n % 10;
						}
					}
				}
			}
			PriorityQueue<Node> queue = new PriorityQueue<>();
			queue.add(new Node(new Point(0,0), 0));
			Set<Point> seen = new HashSet<>();
			int min = Integer.MAX_VALUE;
			while(!queue.isEmpty()) {
				Node node = queue.remove();
				Point p = node.point;
				int x = p.x;
				int y = p.y;
				if(node.value < min) {
					int[][] neighbors = {
							{-1,0},
							{1,0},
							{0,-1},
							{0,1}
					};
					for(int[] neighbor : neighbors) {
						int i = x + neighbor[0];
						int j = y + neighbor[1];
						if(i >= 0 && i < grid.length && j >= 0 && j < grid.length) {
							if(i == grid.length - 1 && j == grid.length - 1) {
								System.out.println(Math.min(min, node.value + grid[i][j]));
								return;
							}
							if(!seen.contains(new Point(i, j))) {
								seen.add(new Point(i, j));
								queue.add(new Node(new Point(i, j), node.value + grid[i][j]));
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
