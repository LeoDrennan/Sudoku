package Validation;

import java.util.concurrent.Callable;

public class RowValidator extends Validator implements Callable<Boolean> {

    private final int row;

    public RowValidator(int[][] board, int row) {
        this.board = board;
        this.row = row;
    }

    @Override
    public Boolean call() {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != 0) {
                boolean valid = ValidateCell(board[row][i], row, i);
                if (!valid) return false;
            }
        }
        return true;
    }
}

