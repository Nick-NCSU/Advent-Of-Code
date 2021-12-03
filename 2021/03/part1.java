import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part1 {
    public static void main(String[] args) {
        try {
            File file = new File("data.txt");
            Scanner scanner = new Scanner(file);
            int[] arr = new int[12];
            for (int i = 0; i < file.length(); i++) {
                if(scanner.hasNextLine()) {
                	String string = scanner.nextLine();
                	for(int j = 0; j < 12; j++) {
                		arr[j] += Character.getNumericValue(string.charAt(j));
                	}
                }
            }
            String s1 = "";
            String s2 = "";
            for(int i = 0; i < 12; i++) {
            	if(arr[i] > 500) {
            		s1 += 1;
            		s2 += 0;
            	} else {
            		s1 += 0;
            		s2 += 1;
            	}
            }
            System.out.println(s1);
            System.out.println(s2);
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

}
