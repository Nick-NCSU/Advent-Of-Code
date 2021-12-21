package AOC.Y2021.D21;

public class Universe {
    Player p1, p2;
    boolean firstTurn;

    public Universe(int pos1, int pos2) {
        p1 = new Player(pos1, 0);
        p2 = new Player(pos2, 0);
        this.firstTurn = true;
    }

    public Universe(Universe universe) {
        p1 = new Player(universe.p1);
        p2 = new Player(universe.p2);
        firstTurn = universe.firstTurn;
    }

    public boolean move(int spaces) {
        if(firstTurn) {
            int score = p1.move(spaces);
            if(score >= 21) {
                part2_21.p1Wins++;
                return false;
            }
        } else {
            int score = p2.move(spaces);
            if(score >= 21) {
                part2_21.p2Wins++;
                return false;
            }
        }
        firstTurn = !firstTurn;
        return true;
    }
}
