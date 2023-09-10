package ee.bertim.springGame.models;

import java.util.Random;

public class Game {
    private static int lastIndex = -1;
    private static final Random random = new Random();

    private final int index;
    private final int numberToGuess;
    private int attemptsAmount = 0;
    private boolean isOver = false;

    public Game() {
        lastIndex++;
        index = lastIndex;
        this.numberToGuess = random.nextInt(100) + 1;
    }

    public static int getLastIndex() {
        return lastIndex;
    }

    public int getIndex() {
        return index;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }

    public int getAttemptsAmount() {
        return attemptsAmount;
    }

    public boolean isOver() {
        return isOver;
    }

    public boolean guess(int number) {
        attemptsAmount++;
        if (number == numberToGuess) {
            isOver = true;
        }

        return isOver;
    }
}
