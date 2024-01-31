public class SudokuSolver {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        int[][] board = new int[][]{
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}
        };

        Game game = new Game(board);

        game.Show();

        boolean valid = game.Validate();

        System.out.println(valid);

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.00;  //divide by 1000000 to get milliseconds.

        System.out.println(duration);
    }
}