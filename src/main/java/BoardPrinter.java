public class BoardPrinter {

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
