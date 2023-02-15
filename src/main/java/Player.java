import java.util.*;

public class Player {
    private String name;
    private List<Ship> shipList;
    private String[][] board;
    private GameSetter setter;
    private List<String> moveList;

    public Player(String name){
        this.name = name;
        setter = new GameSetter();
        moveList = new ArrayList<>();
        board = setter.beginArray();
        shipList = new LinkedList<>();
        shipList.add(new Ship(" <M4> ", 4));
        shipList.add(new Ship(" <M3> ", 3));
        shipList.add(new Ship(" <M3> ", 3));
        shipList.add(new Ship(" <M2> ",2));
        shipList.add(new Ship(" <M2> ",2));
        shipList.add(new Ship(" <M2> ",2));
        shipList.add(new Ship(" <M1> ",1));
        shipList.add(new Ship(" <M1> ",1));
        shipList.add(new Ship(" <M1> ",1));
        shipList.add(new Ship(" <M1> ",1));



    }

    public String getName() {
        return name;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public String[][] getBoard() {
        return board;
    }

    public void changeBoard(String coordinate, String outcome) {
        List<Character> charList = new GameHelper().getCharList();
        int row = (Integer.parseInt(coordinate.substring(1)) - 1);
        int column = charList.indexOf(coordinate.charAt(0));

        board[row][column] = outcome;

    }
    public void boardReset () {
        board = setter.beginArray();
    }
    public void removeShip (Ship ship) {
        shipList.remove(ship);
    }
    public void addMove(String move){
        moveList.add(move);
    }

    public List<String> getMoveList() {
        return moveList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!Objects.equals(name, player.name)) return false;
        if (!Objects.equals(shipList, player.shipList)) return false;
        if (!Arrays.deepEquals(board, player.board)) return false;
        return Objects.equals(setter, player.setter);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (shipList != null ? shipList.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(board);
        result = 31 * result + (setter != null ? setter.hashCode() : 0);
        return result;
    }
}
