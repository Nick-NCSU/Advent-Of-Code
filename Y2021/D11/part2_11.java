package AOC.Y2021.D11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class part2_11 {
	public static void main(String[] args) {
		try {
			File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D11/data.txt");
			Scanner scanner = new Scanner(file);
			int[][] oct = new int[10][10];
			int c = 0;
			while(scanner.hasNextLine()) {
				int[] line = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
				oct[c++] = line;
			}
			scanner.close();
			for(int k = 0; k < 1000000; k++) {
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						oct[i][j]++;
					}
				}
				boolean change = true;
				int[][] flashed = new int[10][10];
				while (change) {
					change = false;
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							if (oct[i][j] > 9) {
								oct[i][j] = -10;
								flashed[i][j] = 1;
								change = true;
								for (int l = i - 1; l <= i + 1; l++) {
									for (int m = j - 1; m <= j + 1; m++) {
										if (l < 0 || l > 9 || m < 0 || m > 9) {
											continue;
										}
										oct[l][m]++;
									}
								}
							}
						}
					}
				}
				boolean all = true;
				for (int i = 0; i < 10 && all; i++) {
					for (int j = 0; j < 10 && all; j++) {
						if (flashed[i][j] == 0) {
							all = false;
						}
					}
				}
				if (all) {
					System.out.println(k);
					return;
				}
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (flashed[i][j] == 1) {
							oct[i][j] = 0;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
