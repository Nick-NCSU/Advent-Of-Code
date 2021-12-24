package AOC.Y2021.D19;

import java.util.Objects;

public class Point3D {
    int x, y, z;
    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int[] toIntArray() {
        return new int[] {
                x, y, z
        };
    }

    @Override
    public boolean equals(Object o) {
        Point3D point3D = (Point3D) o;
        return x == point3D.x && y == point3D.y && z == point3D.z;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }

    public static int manhattan(Point3D p1, Point3D p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) + Math.abs(p1.z - p2.z);
    }
}
