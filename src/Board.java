import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import Validation.*;

public class Board extends Validator {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public Board(int[][] board) {
        this.board = board;
    }

    public void Show(){
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean Validate() {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        try {
            for (int i = 0; i < 9; i++) {
                tasks.add(new RowValidator(board, i));
            }
            List<Future<Boolean>> results = executor.invokeAll(tasks);
            for (Future<Boolean> result : results) {
                if (!result.get()) return false;
            }
            return true;
        }
        catch (InterruptedException | ExecutionException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            executor.shutdown();
        }
    }

    public void Optimise() {
        ArrayList<Integer> possible = new ArrayList<Integer>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (ValidateCell(k, i, j)) {
                            possible.add(k);
                        }
                    }
                    if (possible.size() == 1) {
                        board[i][j] = possible.getFirst();
                        Optimise();
                    }
                    possible.clear();
                }
            }
        }
    }

    public boolean Solve() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board[row][column] == 0){
                    for (int num = 1; num <= 9; num++) {
                        if (ValidateCell(num, row , column)){
                            board[row][column] = num;
                            if (Solve()) return true;
                            else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


}
