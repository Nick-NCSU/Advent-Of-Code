package AOC.Y2021.D04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class part2_04 {
	public static void main(String[] args) {
		try {
			File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D04/data.txt");
			Scanner scanner = new Scanner(file);
			List<int[][]> boards = new ArrayList<>();
			for (int i = 0; i < file.length(); i++) {
				if(scanner.hasNextLine()) {
					String[] string;
					int[][] board = new int[5][5];
					for(int j = 0; j < 5; j++) {
						string = scanner.nextLine().strip().split("\\s+");
						for(int k = 0; k < 5; k++) {
							board[j][k] = Integer.parseInt(string[k]);
						}
					}
					boards.add(board);
					if(scanner.hasNextLine()) {
						scanner.nextLine();
					}
				}
			}
			scanner.close();
			file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D04/data2.txt");
			scanner = new Scanner(file);
			String[] numbers = scanner.nextLine().split(",");
			for(String numberStr : numbers) {
				int number = Integer.parseInt(numberStr);
				nextBoard:
				for(int b = 0; b < boards.size(); b++) {
					int[][] board = boards.get(b);
					for(int i = 0; i < 5; i++) {
						for(int j = 0; j < 5; j++) {
							if(board[i][j] == number) {
								board[i][j] = -1;
							}
						}
					}
					for(int i = 0; i < 5; i++) {
						for (int j = 0; j <= 5; j++) {
							if (j == 5) {
								if(boards.size() == 1) {
									for(int k = 0; k < 5; k++) {
										System.out.println(Arrays.toString(boards.get(0)[k]));
									}
									System.out.println(number);
									return;
								}
								boards.remove(b--);
								continue nextBoard;
							}
							if (board[i][j] != -1) {
								break;
							}
						}
					}
					for(int i = 0; i < 5; i++) {
						for(int j = 0; j <= 5; j++) {
							if(j == 5) {
								if(boards.size() == 1) {
									for(int k = 0; k < 5; k++) {
										System.out.println(Arrays.toString(boards.get(0)[k]));
									}
									System.out.println(number);
									return;
								}
								boards.remove(b--);
								continue nextBoard;
							}
							if(board[j][i] != -1) {
								break;
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
