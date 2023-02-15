import java.util.*;

public class GameHelper {
    private List<Character> charList;

    public GameHelper() {
        charList = new ArrayList<>();
        charList.add('A');
        charList.add('B');
        charList.add('C');
        charList.add('D');
        charList.add('E');
        charList.add('F');
        charList.add('G');
        charList.add('H');
        charList.add('I');
        charList.add('J');
    }

    public List<Character> getCharList() {
        return charList;
    }

    public boolean neighborCheck(String coordinate, List<String> helpList) {

        int column = charList.indexOf(coordinate.charAt(0));
        int row = Integer.parseInt(coordinate.substring(1));

        String horizontalMinus ="";
        String horizontalPlus ="";
        if(column > 0) horizontalMinus = charList.get(column -1 ) + "" + row;
        if(column < 9) horizontalPlus = charList.get(column +1) +"" + row;

        String verticalMinus = charList.get(column) + "" + (row - 1);
        String verticalPlus = charList.get(column) + "" + (row + 1);

        String diagonal1 = "";
        String diagonal2 = "";
        String diagonal3 = "";
        String diagonal4 = "";

        if(column > 0) diagonal1 = charList.get(column -1 ) + "" + (row - 1);
        if(column < 9) diagonal2 = charList.get(column +1) +"" + (row - 1);
        if(column > 0) diagonal3 = charList.get(column -1 ) + "" + (row + 1);
        if(column < 9) diagonal4 = charList.get(column +1) +"" + (row + 1);

        boolean horizontal = helpList.contains(horizontalMinus) || helpList.contains(horizontalPlus);
        boolean vertical = helpList.contains(verticalMinus) || helpList.contains(verticalPlus);
        boolean diagonal = helpList.contains(diagonal1) || helpList.contains(diagonal2) || helpList.contains(diagonal3) || helpList.contains(diagonal4);
        return  horizontal || vertical || diagonal;

    }

    public boolean shipSettingCheck(List<String> tempCoordinate) {
        Set<Integer> columnSet = new HashSet<>();
        Set<Integer> rowSet = new HashSet<>();
        int result;
        int max;
        int min;

        for(String coord : tempCoordinate) {
            int colum = charList.indexOf(coord.charAt(0));
            columnSet.add(colum);
            int row = Integer.parseInt(coord.substring(1));
            rowSet.add(row);
        }
        if(rowSet.size() == 1) {
            max = columnSet.stream().mapToInt(n -> n).max().getAsInt();
            min = columnSet.stream().mapToInt(n -> n).min().getAsInt();
            result = max - min;
        } else if (columnSet.size() == 1) {
            max = rowSet.stream().mapToInt(n -> n).max().getAsInt();
            min = rowSet.stream().mapToInt(n -> n).min().getAsInt();
            result = max - min;
        } else { result = 10;}

        return result == (tempCoordinate.size() -1);
    }
}
