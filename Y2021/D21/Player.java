package AOC.Y2021.D21;

public class Player {
    int score;
    int position;

    public Player(int position, int score) {
        this.score = score;
        this.position = position;
    }

    public Player(Player p) {
        this(p.position, p.score);
    }

    public int move(int spaces) {
        position = (position + spaces) % 10;
        score += position == 0 ? 10 : position;
        return score;
    }
}
