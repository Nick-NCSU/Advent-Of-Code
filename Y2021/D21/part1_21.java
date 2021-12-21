package AOC.Y2021.D21;

public class part1_21 {
    public static void main(String[] args) {
        int score1 = 0;
        int score2 = 0;
        int pos1 = 10;
        int pos2 = 8;
        boolean firstTurn = true;
        int die = -2;
        while(score1 < 1000 && score2 < 1000) {
            die += 3;
            if(firstTurn) {
                pos1 = (pos1 + 3*die + 3) % 10;
                score1 += pos1 == 0 ? 10 : pos1;
            } else {
                pos2 = (pos2 + 3*die + 3) % 10;
                score2 += pos2 == 0 ? 10 : pos2;
            }
            firstTurn = !firstTurn;
        }
        System.out.println(Math.min(score1, score2) * (die + 2));
    }
}
