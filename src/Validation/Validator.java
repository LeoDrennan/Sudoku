package Validation;

public class Validator {
    protected int[][] board;

    protected boolean ValidateCell(int number, int x, int y) {
        boolean validRow = CheckRow(number, x, y);
        boolean validColumn = CheckColumn(number, x, y);
        boolean validSquare = CheckSquare(number, x, y);

        return validRow && validColumn && validSquare;
    }

    private boolean CheckRow(int number, int x, int y){
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == number && i != y) {
                return false;
            }
        }
        return true;
    }

    private boolean CheckColumn(int number, int x, int y){
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == number && i != x) {
                return false;
            }
        }
        return true;
    }

    private boolean CheckSquare(int number, int x, int y) {
        int squareX = x - (x % 3);
        int squareY = y - (y % 3);

        for (int i = squareX; i < squareX + 3; i++) {
            for (int j = squareY; j < squareY + 3; j++) {
                if (board[i][j] == number ) {
                    if (i == x && j == y) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
