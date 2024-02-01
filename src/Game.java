import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Game {

    private final int[][] board;
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public Game(int[][] board) {
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
}
