import java.util.Scanner;

public class ConsoleHandler {

    private Scanner scanner;
    private GameHelper helper;

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

    public String getName() {

        return scanner.nextLine();
    }

    public int getNumberOfPlayers() {
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
}
