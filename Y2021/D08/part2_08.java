package AOC.Y2021.D08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class part2_08 {
	public static void main(String[] args) {
		try {
			File file = new File("/home/nick/Desktop/aoc/src/AOC/Y2021/D08/data.txt");
			Scanner scanner = new Scanner(file);
			List<String[]> lines = new ArrayList<>();
			while(scanner.hasNextLine()) {
				lines.add(scanner.nextLine().split(" \\| "));
			}
			scanner.close();
			int count = 0;
			for(String[] s : lines) {
				String[] input = s[0].split(" ");
				String one = Arrays.stream(input).filter(x -> x.length() == 2).toArray(String[]::new)[0];
				String four = Arrays.stream(input).filter(x -> x.length() == 4).toArray(String[]::new)[0];
				String six = Arrays.stream(input).filter(x -> x.length() == 6 && match(x, one) == 1).toArray(String[]::new)[0];
				String zero = Arrays.stream(input).filter(x -> x.length() == 6 && match(x, four) == 3 && x != six).toArray(String[]::new)[0];
				String nine = Arrays.stream(input).filter(x -> x.length() == 6 && x != six && x != zero).toArray(String[]::new)[0];
				String five = Arrays.stream(input).filter(x -> x.length() == 5 && match(x, six) == 5).toArray(String[]::new)[0];
				String three = Arrays.stream(input).filter(x -> x.length() == 5 && match(x, one) == 2).toArray(String[]::new)[0];
				String two = Arrays.stream(input).filter(x -> x.length() == 5 && x != five && x != three).toArray(String[]::new)[0];

				String answer = "";
				for(String output : s[1].split(" ")) {
					switch(output.length()) {
						case 2:
							answer += 1;
							break;
						case 3:
							answer += 7;
							break;
						case 4:
							answer += 4;
							break;
						case 7:
							answer += 8;
							break;
						case 5:
							if(match(output, five) == 5)
								answer += 5;
							else if(match(output, three) == 5)
								answer += 3;
							else if(match(output, two) == 5)
								answer += 2;
							break;
						case 6:
							if(match(output, zero) == 6)
								answer += 0;
							else if(match(output, six) == 6)
								answer += 6;
							else if(match(output, nine) == 6)
								answer += 9;
							break;
					}
				}
				count += Integer.parseInt(answer);
			}
			System.out.println(count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int match(String s1, String s2) {
		int count = 0;
		for(char l1 : s1.toCharArray()) {
			for(char l2 : s2.toCharArray()) {
				if(l1 == l2) {
					count++;
				}
			}
		}
		return count;
	}
}
