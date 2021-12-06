package AOC.Y2021.D06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part2_06 {
	public static void main(String[] args) {
		try {
			File file = new File("/home/nick/Desktop/aoc/src/AOC/Y2021/D06/data.txt");
			Scanner scanner = new Scanner(file);
			long[] fish = new long[9];
			while(scanner.hasNextLine()) {
				String[] string = scanner.nextLine().split(",");
				for(String s : string) {
					fish[Integer.parseInt(s)]++;
				}
			}
			scanner.close();
			for(int i = 0; i < 256; i++) {
				long zero = fish[0];
				for(int j = 0; j < 8; j++) {
					fish[j] = fish[j + 1];
				}
				fish[6] += zero;
				fish[8] = zero;
			}
			long count = 0;
			for(long i : fish) {
				count += i;
			}
			System.out.println(count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}



	}

}
