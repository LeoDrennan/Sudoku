package Generation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Generator {
    public int[][] board = new int[9][9];
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    // Populates the top left, middle, and bottom right squares
    // These squares are independent of each other and can be naively filled
    public int[][] generateDiagonals() {

        for (int i = 0; i < 9;  i = i + 3) {
            executor.execute(new PopulateSquare(i, i, board));
        }

        return this.board;
    }

    // Generates random number between 1-9 inclusive
    private int generateNum() {
        return (int) Math.floor((Math.random() * 9 + 1));
    }

    // Generates a random set of co-ordinates within the sudoku board
    private int[] randomCellGenerator() {
        int x = generateNum() - 1;
        int y = generateNum() - 1;

        return new int[] {x, y};
    }

    public void removeNumbers() {
        int count = 20;
        while (count != 0)
        {
            int[] cell = randomCellGenerator();
            int x = cell[0];
            int y = cell[1];

            if (board[x][y] != 0)
            {
                count--;
                board[x][y] = 0;
            }
        }
    }
}
