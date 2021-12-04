package AOC.Y2021.D03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part2_03 {
    public static void main(String[] args) {


        List<ArrayList<Integer>> array = new ArrayList<>();
        List<ArrayList<Integer>> array2 = new ArrayList<>();

        try {
            File file = new File("data.txt");
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < file.length(); i++) {
                if(scanner.hasNextLine()) {
                	String string = scanner.nextLine();
                	ArrayList<Integer> a = new ArrayList<>();
                	for(int j = 0; j < 12; j++) {
                		a.add(Character.getNumericValue(string.charAt(j)));
                	}
                	array.add(a);
                	array2.add(a);
                }
            }
            for(int i = 0; i < 12; i++) {
            	int total1 = 0;
            	int total0 = 0;
            	for(ArrayList<Integer> arrayList : array) {
            		if(arrayList.get(i) == 1) {
            			total1++;
            		} else {
            			total0++;
            		}
        	}
            	if(total1 >= total0) {
            		for(int j = 0; j < array.size(); j++) {
            			ArrayList<Integer> arrayList = array.get(j);
            			if(arrayList.get(i) != 1) {
            				array.remove(j--);
            			}
            			if(array.size() == 1) {
            				System.out.println(array.get(0));
            				break;
            			}
            		}
            	} else {
            		for(int j = 0; j < array.size(); j++) {
            			ArrayList<Integer> arrayList = array.get(j);
            			if(arrayList.get(i) != 0) {
            				array.remove(j--);
            			}
            			if(array.size() == 1) {
            				System.out.println(array.get(0));
            				break;
            			}
            		}
            	}
            }
            array = array2;
            for(int i = 0; i < 12; i++) {
            	int total1 = 0;
            	int total0 = 0;
            	for(ArrayList<Integer> arrayList : array) {
            		if(arrayList.get(i) == 1) {
            			total1++;
            		} else {
            			total0++;
            		}
        		}
            	if(total1 >= total0) {
            		for(int j = 0; j < array.size(); j++) {
            			ArrayList<Integer> arrayList = array.get(j);
            			if(arrayList.get(i) != 0) {
            				array.remove(j--);
            			}
            			if(array.size() == 1) {
            				System.out.println(array.get(0));
            				break;
            			}
            		}
            	} else {
            		for(int j = 0; j < array.size(); j++) {
            			ArrayList<Integer> arrayList = array.get(j);
            			if(arrayList.get(i) != 1) {
            				array.remove(j--);
            			}
            			if(array.size() == 1) {
            				System.out.println(array.get(0));
            				break;
            			}
            		}
            	}
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

}
