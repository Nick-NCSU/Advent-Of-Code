package AOC.Y2021.D16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class part2_16 {
    static String binaryString = "";

    public static void main(String[] args) {
        try {
            File file = new File("/home/nick/Desktop/Advent-Of-Code/Y2021/D16/data.txt");
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            for(char c : line.toCharArray()) {
                String b = Integer.toBinaryString(Integer.parseInt(c + "", 16));
                for(int i = b.length(); i < 4; i++) {
                    binaryString += 0;
                }
                binaryString += b;
            }
            scanner.close();
            Packet outer = getPackets();
            System.out.println(outer.sumVersion());
            System.out.println(outer.getValue());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Packet getPackets() {
        int version = getInt(3);
        int type = getInt(3);
        long literal = 0;
        List<Packet> subPackets = new ArrayList<>();
        if(type == 4) {
            String groups = "";
            while(getInt(1) == 1) {
                groups += getString(4);
            }
            groups += getString(4);
            literal = Long.parseLong(groups, 2);
        } else {
            int lenType = getInt(1);
            if(lenType == 0) {
                int totalLength = getInt(15);
                int currentLength = binaryString.length();
                while(currentLength - binaryString.length() < totalLength) {
                    subPackets.add(getPackets());
                }
            } else {
                int numSubPackets = getInt(11);
                for(int i = 0; i < numSubPackets; i++) {
                    subPackets.add(getPackets());
                }
            }
        }
        return new Packet(version, type, literal, subPackets);
    }

    public static int getInt(int num) {
        int result = Integer.parseInt(binaryString.substring(0, num), 2);
        binaryString = binaryString.substring(num);
        return result;
    }

    public static String getString(int num) {
        String result = binaryString.substring(0, num);
        binaryString = binaryString.substring(num);
        return result;
    }
}

