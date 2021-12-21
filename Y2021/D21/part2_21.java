package AOC.Y2021.D21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class part2_21 {
	public static long p1Wins = 0;
	public static long p2Wins = 0;
	public static List<Universe> universes = Collections.synchronizedList(new ArrayList<>());

	public static void main(String[] args) throws InterruptedException {
		Universe universe = new Universe(4, 8);
		universes.add(universe);
		evalUniverse();
	}

	public static void evalUniverse() {
		Universe u = universes.remove(0);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					Universe u2 = new Universe(u);
					if(u2.move(i + j + k)) {
						universes.add(u2);
						evalUniverse();
					}
				}
			}
		}
	}
}