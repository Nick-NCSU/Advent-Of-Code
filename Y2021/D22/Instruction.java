package AOC.Y2021.D22;

import java.util.ArrayList;
import java.util.List;

public class Instruction {
    int xmin, ymin, zmin, xmax, ymax, zmax;
    long size;
    int on;
    public Instruction(int xmin, int ymin, int zmin, int xmax, int ymax, int zmax, boolean on) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.zmin = zmin;
        this.zmax = zmax;
        this.on = on ? 1 : -1;
        size = (long) (xmax - xmin + 1) * (ymax - ymin + 1) * (zmax - zmin + 1);
    }

    public void add() {
        List<Instruction> newLines = new ArrayList<>();
        for(Instruction instruction : part2_22.instructions) {
            int x1 = Math.max(instruction.xmin, xmin);
            int x2 = Math.min(instruction.xmax, xmax);
            int y1 = Math.max(instruction.ymin, ymin);
            int y2 = Math.min(instruction.ymax, ymax);
            int z1 = Math.max(instruction.zmin, zmin);
            int z2 = Math.min(instruction.zmax, zmax);
            if(x1 <= x2 && y1 <= y2 && z1 <= z2) {
                newLines.add(new Instruction(x1, y1, z1, x2, y2, z2, instruction.on == -1));
            }
        }
        part2_22.instructions.addAll(newLines);
    }
}
