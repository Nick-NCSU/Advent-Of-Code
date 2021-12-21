package AOC.Y2021.D18;

import java.util.ArrayList;
import java.util.List;

public class Number {
    List<Snailfish> snailfish = new ArrayList<>();

    public void addSnailfish(int num, int depth) {
        snailfish.add(new Snailfish(num, depth));
    }

    public static Number add(Number number1, Number number2) {
        Number number = new Number();
        number.snailfish.addAll(number1.snailfish);
        number.snailfish.addAll(number2.snailfish);
        for(Snailfish f : number.snailfish) {
            f.depth++;
        }
        loop:
        while(true) {
            for (int i = 0; i < number.snailfish.size(); i++) {
                Snailfish fish = number.snailfish.get(i);
                if (fish.depth > 4) {
                    fish.depth--;
                    if (i > 0) {
                        number.snailfish.get(i - 1).value += fish.value;
                    }
                    if (i < number.snailfish.size() - 2) {
                        number.snailfish.get(i + 2).value += number.snailfish.get(i + 1).value;
                    }
                    number.snailfish.get(i).value = 0;
                    number.snailfish.remove(i + 1);
                    continue loop;
                }
            }
            for (int i = 0; i < number.snailfish.size(); i++) {
                Snailfish fish = number.snailfish.get(i);
                if (fish.value >= 10) {
                    int value = fish.value;
                    int depth = fish.depth;
                    number.snailfish.set(i, new Snailfish((int) Math.ceil(value / 2.0), depth + 1));
                    number.snailfish.add(i, new Snailfish((int) Math.floor(value / 2.0), depth + 1));
                    continue loop;
                }
            }
            return number;
        }
    }

    public int magnitude() {
        loop:
        while(snailfish.size() != 1) {
            for(int i = 0; i < snailfish.size() - 1; i++) {
                if(snailfish.get(i).depth == snailfish.get(i + 1).depth) {
                    int val = snailfish.get(i).value * 3 + snailfish.get(i + 1).value * 2;
                    snailfish.set(i, new Snailfish(val, snailfish.get(i).depth - 1));
                    snailfish.remove(i + 1);
                    continue loop;
                }
            }
        }
        return snailfish.get(0).value;
    }
}
