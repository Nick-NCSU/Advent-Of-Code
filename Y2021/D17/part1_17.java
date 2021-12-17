package AOC.Y2021.D17;

public class part1_17 {
    public static void main(String[] args) {
        int x1 = 257;
        int x2 = 286;
        int y1 = -57;
        int y2 = -101;

        int x, y;

        int xVel, yVel;

        int maxY = 0;
        for(int yinc = 0; yinc < 2500; yinc++) {
            for(int xinc = 0; xinc < 300; xinc++) {
                x = 0;
                y = 0;
                xVel = xinc;
                yVel = yinc;
                int tempMaxY = 0;
                while (x + xVel <= x2 && y + yVel >= y2) {
                    tempMaxY = Math.max(y, tempMaxY);
                    x += xVel;
                    xVel = xVel == 0 ? 0 : xVel - 1;
                    y += yVel;
                    yVel--;
                }
                if(x >= x1 && x <= x2 && y <= y1 && y >= y2) {
                    maxY = Math.max(tempMaxY, maxY);
                }
            }
        }
        System.out.println(maxY);
    }
}

