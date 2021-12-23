package AOC.Y2021.D22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part2_22 {
	public static List<Instruction> instructions = new ArrayList<>();
	public static void main(String[] args) {
		try {
			File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D22/data.txt");
			Scanner scanner = new Scanner(file);
			int n = 0;
			while (scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(" ");
				boolean on = line[0].charAt(1) == 'n';
				int xmin, ymin, zmin, xmax, ymax, zmax;
				Pattern r = Pattern.compile("x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)");
				Matcher m = r.matcher(line[1]);
				m.find();
				xmin = Integer.parseInt(m.group(1));
				xmax = Integer.parseInt(m.group(2));
				ymin = Integer.parseInt(m.group(3));
				ymax = Integer.parseInt(m.group(4));
				zmin = Integer.parseInt(m.group(5));
				zmax = Integer.parseInt(m.group(6));
				Instruction i = new Instruction(xmin, ymin, zmin, xmax, ymax, zmax, on);
				i.add();
				if(on)
					instructions.add(i);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long size = 0;
		for(Instruction i : instructions) {
			size += i.size * i.on;
		}
		System.out.println(size);
	}
}