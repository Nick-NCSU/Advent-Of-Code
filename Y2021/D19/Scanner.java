package AOC.Y2021.D19;

import java.util.*;
import java.util.stream.Stream;

public class Scanner {
    Set<Point3D> beacons;
    Point3D location;

    public Scanner() {
        this.beacons = new HashSet<>();
    }

    public void add(int x, int y, int z) {
        beacons.add(new Point3D(x, y, z));
    }

    public boolean compare(Scanner s) {
        int[][] rotations = {
                {1,1,1},
                {-1,1,1},
                {1,-1,1},
                {1,1,-1},
                {-1,-1,1},
                {1,-1,-1},
                {-1,1,-1},
                {-1,-1,-1}
        };
        int[][] orientations = {
                {0,1,2},
                {0,2,1},
                {1,0,2},
                {1,2,0},
                {2,0,1},
                {2,1,0}
        };
        for(int[] rotation : rotations) {
            for(int[] orientation : orientations) {
                Map<String, Integer> map = new HashMap<>();
                for(Point3D a : beacons) {
                    for(Point3D b : s.beacons) {
                        int[] arr = new int[3];
                        for(int i = 0; i < 3; i++) {
                            arr[i] = a.toIntArray()[i] + rotation[i] * b.toIntArray()[orientation[i]];
                        }
                        map.merge(Arrays.toString(arr), 1, Integer::sum);
                    }
                }

                for(Map.Entry<String, Integer> entry : map.entrySet()) {
                    if(entry.getValue() >= 12) {
                        int[] location = Stream.of(entry.getKey().substring(1, entry.getKey().length() - 1).split(", ")).mapToInt(Integer::parseInt).toArray();
                        s.location = new Point3D(location[0], location[1], location[2]);
                        s.reorient(rotation, orientation);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void reorient(int[] rotation, int[] orientation) {
        Set<Point3D> newBeacons = new HashSet<>();
        for(Point3D point : beacons) {
            int[] arr = new int[3];
            for(int i = 0; i < 3; i++) {
                arr[i] = location.toIntArray()[i] - rotation[i] * point.toIntArray()[orientation[i]];
            }
            newBeacons.add(new Point3D(arr[0], arr[1], arr[2]));
        }
        beacons = newBeacons;
    }
}
