package Generation;

public class PopulateSquare implements Runnable {

    private final int row;
    private final int col;
    private final int[][] board;

    public PopulateSquare(int row, int col, int[][] board) {
        this.row = row;
        this.col = col;
        this.board = board;
    }

    // Fills a 3x3 square with numbers 1-9 randomly
    @Override
    public void run() {
        int num;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                do
                {
                    num = generateNum();
                }
                while (!validateSquare(row, col, num));

                synchronized (board) {
                    board[row + i][col + j] = num;
                }
            }
        }
    }

    // Generates random number between 1-9 inclusive
    private int generateNum() {
        return (int) Math.floor((Math.random() * 9 + 1));
    }

    // Returns false if given 3x3 square contains given number
    private boolean validateSquare(int rowStart, int colStart, int num) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }
}
