import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import Generation.Generator;
import Validation.*;

public class Game {
    private final Validator validator = new Validator();
    private final Generator generator = new Generator();
    public int[][] solution;
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public Game() {
        generate();
    }

    public void show() {
        for (int[] row : validator.board) {
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

    public void solution() {
        for (int[] row : solution) {
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

    // Validates that a board does not violate the laws of sudoku
    public boolean validate() {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        try {
            for (int i = 0; i < 9; i++) {
                tasks.add(new RowValidator(validator.board, i));
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

    // Solves the given puzzle
    // First optimises the solution for improved performance
    public boolean solve() {
        optimise();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (validator.board[row][column] == 0){
                    for (int num = 1; num <= 9; num++) {
                        if (validator.validateCell(num, row , column)){
                            validator.board[row][column] = num;
                            if (solve()) return true;
                            else {
                                validator.board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Recursive function that fills in any cells that have only 1 possibility
    private void optimise() {
        ArrayList<Integer> possible = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (validator.board[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (validator.validateCell(k, i, j)) {
                            possible.add(k);
                        }
                    }
                    if (possible.size() == 1) {
                        validator.board[i][j] = possible.getFirst();
                        optimise();
                    }
                    possible.clear();
                }
            }
        }
    }

    // Generate a random sudoku
    private void generate() {
        // Fills three 3x3 squares diagonally
        try {
            validator.board = generator.generateDiagonals();
        } catch (InterruptedException exception) {
            throw new RuntimeException();
        }


        // Fill remaining cells
        boolean isSolved = solve();

        // If the randomly generated puzzle is not possible, start again
        if (!isSolved){
            generator.board = new int[9][9];
            generate();
        } else {
            this.solution = saveSolution();
            generator.removeNumbers();
            validator.board = generator.board;
        }
    }

    // Creates a deep copy of the board - saving the generated solution
    public int[][] saveSolution() {
        int[][] newArray = new int[validator.board.length][];
        for ( int i = 0; i < newArray.length; i++ ) {
            newArray[i] = new int[validator.board[i].length];
            System.arraycopy(validator.board[i], 0, newArray[i], 0, validator.board[i].length);
        }

        return newArray;
    }
}
