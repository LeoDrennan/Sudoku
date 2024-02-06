public class SudokuSolver {
    public static void main(String[] args) {

        Board board = initializeBoard();

        long startTime = System.nanoTime();

        board.Validate();

        board.Optimise();

        board.Solve();

        board.Show();

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;

        System.out.println(duration);
    }

    private static Board initializeBoard() {
        int[][] grid = new int[][]{
                {0, 0, 0, 6, 0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 9, 0, 6, 0},
                {0, 8, 0, 0, 0, 5, 0, 0, 3},
                {1, 0, 0, 4, 0, 0, 9, 0, 0},
                {8, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 6, 0, 0, 0},
                {0, 0, 0, 0, 6, 0, 0, 0, 0},
                {2, 5, 0, 3, 0, 7, 0, 9, 0},
                {0, 0, 1, 0, 0, 0, 0, 8, 4}
        };

        Board board = new Board(grid);

        System.out.println("Starting board:");
        System.out.print("\n");

        board.Show();

        return board;
    }
}