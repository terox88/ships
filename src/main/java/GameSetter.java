import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameSetter implements Serializable {

    private GameHelper helper;
    private ConsoleHandler handler;
    private static final long serialVersionUID = 4L;


    public GameSetter() {
        helper = new GameHelper();
        handler = new ConsoleHandler();
    }

    List<Character> charList = new GameHelper().getCharList();

    public String[][] beginArray() {
        String[][] array = new String[10][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = ("  " + charList.get(j) + (i + 1) + "  ");
            }
        }
        for (int i = 9; i < 10; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = ("  " + charList.get(j) + (i + 1) + " ");
            }

        }
        return array;
    }

    public Player setGameForPerson() {

        String name = handler.getName();
        Player player = new Player(name);
        List<String> moveList = new ArrayList<>();

        for (Ship ship : player.getShipList()) {
            boolean isCorrect = false;
            List<String> tempList = new ArrayList<>();
            while (!isCorrect) {
                ConsoleHandler.printBoard(player);
                System.out.println("Enter " + ship.getSize() + " coordinates for you ship");
                while (tempList.size() < ship.getSize()) {

                    String coordinate = handler.getCoordinate();
                    boolean haveNeighbor = helper.neighborCheck(coordinate, moveList);
                    if (!haveNeighbor && !moveList.contains(coordinate)) {
                        tempList.add(coordinate);
                    } else {
                        System.out.println("Ship can' t touch other ships. Enter different coordinate");
                    }
                }
                boolean settingCheck = helper.shipSettingCheck(tempList);
                if (settingCheck) {
                    isCorrect = true;
                } else {
                    System.out.println("Wrong ship setting try again");
                    tempList.clear();
                }
            }
            for (String temp : tempList) {
                moveList.add(temp);
                ship.addCoordinates(temp);
                player.changeBoard(temp, ship.getName());
            }
            tempList.clear();

        }
        player.boardReset();

        return player;
    }
}