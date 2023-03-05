import java.io.Serializable;
import java.util.Scanner;

public class ConsoleHandler implements Serializable {

    private transient Scanner scanner;
    private GameHelper helper;
    private static final long serialVersionUID = 5L;

    public ConsoleHandler() {
        scanner = new Scanner(System.in);
        helper = new GameHelper();
    }

    public String getCoordinate() {
        String coordinate = "";
        boolean correct = false;
        while (!correct) {
            coordinate = scanner.nextLine();
            coordinate = coordinate.toUpperCase();
            if (coordinate.length() > 1 && coordinate.length() <= 3) {
                int row;
                try {
                    row = Integer.parseInt(coordinate.substring(1));
                } catch (NumberFormatException e) {
                    row = 99;
                }
                char column = coordinate.charAt(0);
                if (helper.getCharList().contains(column) && row > 0 && row <= 10) {
                    correct = true;
                } else {
                    System.out.println("Wrong coordinate, try again. Range from A1 to J10");
                }
            } else {
                System.out.println("Wrong coordinate try again. Range from A1 to J10");
            }
        }
        return coordinate;
    }
    public boolean loadGame () {
        System.out.println("Enter C for continue saved game or any other key for new game");
        String answer = scanner.nextLine().toUpperCase();
        if(answer.equals("C")) return true;
        return false;
    }
    public boolean saveGame () {
        System.out.println("Enter S if you want end game with saving or other key for continue");
        String answer = scanner.nextLine().toUpperCase();
        if(answer.equals("S")) return true;
        return false;
    }

    public String getName() {
        System.out.println("Enter your name");

        return scanner.nextLine();
    }

    public int getNumberOfPlayers() {
        System.out.println("Choose number of players 1 or 2");
        int number = 0;
        boolean correct = false;
        while (!correct) {
            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                number = 0;
            }
            if (number == 1 || number == 2) {
                correct = true;
            } else {
                System.out.println("Wrong command. Type 1 for single game or 2 for multi game");
            }
        }
        return number;
    }
    public static void printBoard(Player player) {
        System.out.println(player.getName() + " turn");
        System.out.println();

        for(int i = 0; i < player.getBoard().length; i ++){
            for(int j = 0; j < player.getBoard().length; j++) {
                System.out.print( player.getBoard()[i][j] + "|");
            }
            System.out.println();
            System.out.println();

        }
    }
    public static void gameInformation() {
        System.out.println("You board has size 10x10");
        System.out.println("You have following ships on you command:");
        System.out.println("One four masted ship on board   -> <M4>");
        System.out.println("Two three masted ships on board -> <M3>");
        System.out.println("Three two masted ships on board -> <M2>");
        System.out.println("Four one masted ships on board  -> <M1>");
    }
    public static void printOutcome(int outcome, String shoot) {
        switch (outcome){
            case 1:
                System.out.println("HIT!" + " -> " + shoot);
                break;
            case 2:
                System.out.println("HIT AND SUNK!" + " -> " + shoot);
                break;
            case 3:
                System.out.println("MISS!" + " -> " + shoot);
                break;
            case 4:
                System.out.println( shoot +" You've already aimed there! Pay more attention sailor. Cannon balls are very expensive");
        }
    }
}
