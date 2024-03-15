package Generation;

import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int num = generateNum(nums);
                synchronized (board) {
                    board[row + i][col + j] = num;
                }
            }
        }
    }

    // Randomly chooses a number from the given ArrayList
    private int generateNum(ArrayList<Integer> nums) {
        int index = (int) Math.floor((Math.random() * nums.size()));
        int num = nums.get(index);
        nums.remove(index);

        return num;
    }
}
