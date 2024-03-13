import java.util.Arrays;
import java.util.Scanner;

public class SudokuEngine {
    public static void main(String[] args) {

        Game game = new Game();

        displayUnsolved(game);

        boolean isValid = game.validate();
        if (isValid) {
            System.out.println("Valid board generated.");
        } else {
            System.out.println("Error.");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Type solve to see the solution.");

        String command = scan.nextLine();
        if (command.equals("solve")) {
            System.out.println(Arrays.deepToString(game.solution));
        }
    }

    private static void displayUnsolved(Game game) {
        System.out.println("Starting board:");
        System.out.print("\n");

        game.show();
    }
}