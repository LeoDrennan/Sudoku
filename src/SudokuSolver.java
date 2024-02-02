public class SudokuSolver {
    public static void main(String[] args) {

        Game board = initializeGame();

        boolean isValid = board.Validate();

        if (!isValid) {
            System.out.println("Invalid board provided.");
        }
        else {
            boolean solved = board.Solve();
            if (solved) {
                System.out.println("Your solution is:");
                System.out.print("\n");
                board.Show();
            }
            else
            {
                System.out.println("Board could not be solved.");
            }
        }
    }

    private static Game initializeGame() {
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

        System.out.println("Starting board:");
        System.out.print("\n");

        game.Show();

        return game;
    }
}