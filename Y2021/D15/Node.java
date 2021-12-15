package AOC.Y2021.D15;

import java.awt.*;

public class Node implements Comparable {
    Point point;
    int value;

    Node(Point point, int value) {
        this.point = point;
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        return this.value - ((Node) o).value;
    }
}
