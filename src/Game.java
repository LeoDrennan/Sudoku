public class Game {

    public int[][] board;

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

    public boolean Validate(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0){
                    boolean valid = CheckCell(board[i][j], i, j);
                    if (!valid) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean CheckCell(int number, int x, int y) {

        boolean row = CheckRow(number, x, y);
        boolean column = CheckColumn(number, x, y);
        boolean square = CheckSquare(number, x, y);

        return row && column && square;
    }

    private boolean CheckRow(int number, int x, int y){
        for (int i = 0; i < y; i++) {
            if (board[x][i] == number){
                return false;
            }
        }
        return true;
    }

    private boolean CheckColumn(int number, int x, int y){
        for (int i = 0; i < x; i++) {
            if (board[i][y] == number){
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
                    if (i == x && j == y){
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
