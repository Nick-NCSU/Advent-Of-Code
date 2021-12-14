package AOC.Y2021.D14;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class part2_14 {
	public static void main(String[] args) {
		try {
			File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D14/data.txt");
			Scanner scanner = new Scanner(file);
			Dictionary<String, String> pairs = new Hashtable<>();
			String template = scanner.nextLine();
			scanner.nextLine();
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(" -> ");
				pairs.put(line[0], line[1]);
			}
			scanner.close();
			Map<String, Long> pairMap = new HashMap<>();
			for(int i = 0; i < template.length() - 1; i++) {
				String sequence = template.substring(i, i + 2);
				pairMap.merge(sequence, 1L, Long::sum);
			}
			for(int i = 0; i < 40; i++) {
				Map<String, Long> newPairMap = new HashMap<>();
				for(Map.Entry<String, Long> entry : pairMap.entrySet()) {
					if(pairs.get(entry.getKey()) != null) {
						String key = entry.getKey();
						Long value = entry.getValue();
						newPairMap.merge(key.charAt(0) + pairs.get(key), value, Long::sum);
						newPairMap.merge(key, -value, Long::sum);
						newPairMap.merge(pairs.get(key) +  key.charAt(1), value, Long::sum);
					}
				}
				for(Map.Entry<String, Long> entry : newPairMap.entrySet()) {
					String key = entry.getKey();
					Long value = entry.getValue();
					pairMap.merge(key, value, Long::sum);
				}
			}
			Map<Character, Long> elementMap = new HashMap<>();
			for(Map.Entry<String, Long> entry : pairMap.entrySet()) {
				char key1 = entry.getKey().charAt(0);
				char key2 = entry.getKey().charAt(1);
				Long value = entry.getValue();
				elementMap.merge(key1, value, Long::sum);
				elementMap.merge(key2, value, Long::sum);
			}
			elementMap.merge(template.charAt(0), 1L, Long::sum);
			elementMap.merge(template.charAt(template.length() - 1), 1L, Long::sum);
			long[] elementArr = elementMap.values().stream().mapToLong(num -> num / 2).toArray();
			Arrays.sort(elementArr);
			System.out.println(elementArr[elementArr.length - 1] - elementArr[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
