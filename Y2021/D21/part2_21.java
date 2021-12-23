package AOC.Y2021.D21;

public class part2_21 {
	static long p1Wins = 0;
	static long p2Wins = 0;
	static int[][] possibleRolls = {
			{3,1},
			{4,3},
			{5,6},
			{6,7},
			{7,6},
			{8,3},
			{9,1}
	};
	public static void main(String[] args) {
		roll(10, 0, 8, 0, 1, true);
		System.out.println(p1Wins + ", " + p2Wins);
	}
	public static void roll(int curPos, int curScore, int otherPos, int otherScore, long numUniverses, boolean p1Turn) {
		if(otherScore >= 21) {
			if(p1Turn)
				p2Wins += numUniverses;
			else
				p1Wins += numUniverses;
		} else {
			for(int i = 0; i < 7; i++) {
				int newPos = (curPos + possibleRolls[i][0]) % 10;
				roll(otherPos, otherScore, newPos, curScore + (newPos == 0 ? 10 : newPos), numUniverses * possibleRolls[i][1], !p1Turn);
			}
		}
	}
}