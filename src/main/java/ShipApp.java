import java.util.Scanner;

public class ShipApp {
    public static void main(String[] args) {
        boolean end = false;
        TheGame game = new TheGame();
        Scanner scanner = new Scanner(System.in);
        while ((!end)) {
            game.gameRunner();
            System.out.println("Thank's for playing");
            System.out.println("Choose x for close and any other key for new game.");
            String answer = scanner.nextLine();
            if(answer.equals("x")){
                end = true;
            }else {
                game.gameRunner();
            }
        }
    }
}
