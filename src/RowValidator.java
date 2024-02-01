import java.util.concurrent.Callable;

public class RowValidator implements Callable<Boolean> {

    public int[][] board;
    public int row;

    public RowValidator(int[][] board, int row) {
        this.board = board;
        this.row = row;
    }

    @Override
    public Boolean call() {
        return ValidateRow();
    }

    private Boolean ValidateRow(){

        for (int i = 0; i < 9; i++) {
            if (board[row][i] != 0) {
                boolean valid = ValidateCell(board[row][i], i);
                if (!valid) return false;
            }
        }
        return true;
    }

    private boolean ValidateCell(int number, int column) {
        boolean validRow = CheckRow(number, row, column);
        boolean validColumn = CheckColumn(number, row, column);
        boolean validSquare = CheckSquare(number, row, column);

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

