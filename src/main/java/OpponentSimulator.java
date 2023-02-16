import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpponentSimulator {
    private GameHelper helper;
    private Random generator;
    private List<String> movesList;
    private List<Character> charList;
    private boolean hit;
    private boolean drawn;

    public OpponentSimulator() {
        helper = new GameHelper();
        generator = new Random();
        movesList = new ArrayList<>();
        charList = new GameHelper().getCharList();

    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public String shoot() {
        boolean fire = false;
        String shoot ="";
        while (!fire) {
            shoot = charList.get(generator.nextInt(charList.size())) + "" + generator.nextInt(1, 11);
            if(!movesList.contains(shoot)) {
                fire = true;
            }
        }
        movesList.add(shoot);
        return shoot;
    }

    public Player getBoardForComputer() {
        Player computerPlayer = new Player("Computer");
        List<String> tempList = new ArrayList<>();
        List<String> helpList = new ArrayList<>();


        for (Ship ship : computerPlayer.getShipList()) {
            boolean correct = false;
            boolean vertical = generator.nextBoolean();
            while (!correct) {

                boolean newMove = false;
                String coord0 = charList.get(generator.nextInt(11 - ship.getSize())) + "" + generator.nextInt(1, 12 - ship.getSize());
                while (!newMove) {
                    if (!helpList.contains(coord0) && !helper.neighborCheck(coord0, helpList)) {
                        newMove = true;

                    } else {
                        coord0 = charList.get(generator.nextInt(11 - ship.getSize())) + "" + generator.nextInt(1, 12 - ship.getSize());
                    }
                }
                int coord0Column = charList.indexOf(coord0.charAt(0));
                int coordRow = Integer.parseInt(coord0.substring(1));
                tempList.add(coord0);

                if (tempList.size() < ship.getSize() && vertical) {
                    for (int j = 1; j < ship.getSize(); j++) {
                        String coord = charList.get(coord0Column + j) + "" + coordRow;
                        if (!helper.neighborCheck(coord, helpList)) {
                            tempList.add(coord);
                        } else {
                            tempList.clear();
                            break;
                        }
                    }
                }
                if (tempList.size() < ship.getSize() && !vertical) {
                    for (int j = 1; j < ship.getSize(); j++) {
                        String coord = charList.get(coord0Column) + "" + (coordRow + j);
                        if (!helper.neighborCheck(coord, helpList)) {
                            tempList.add(coord);
                        } else {
                            tempList.clear();
                            break;
                        }
                    }
                }

                vertical = !vertical;

                if (tempList.size() == ship.getSize()) {
                    for (String temp : tempList) {
                        helpList.add(temp);
                        ship.addCoordinates(temp);
                        computerPlayer.changeBoard(temp, ship.getName());
                    }
                    tempList.clear();
                    correct = true;
                }
            }

        }
        return computerPlayer;
    }

    public String movesSimulator() {
        if (drawn) {
            return shoot();
        }
        int column = 0;
        int row = 0;
        if (hit) {
            column = charList.indexOf(movesList.get(movesList.size() - 1).charAt(0));
            row = Integer.parseInt(movesList.get(movesList.size() - 1).substring(1));
        }

        String move = "";

        if (column > 0 && hit) {
            move = charList.get(column - 1) + "" + row;
            if (!movesList.contains(move)) {
                movesList.add(move);
                return move;
            }
        }
        if (column < 9 && hit) {
            move = charList.get(column + 1) + "" + row;
            if (!movesList.contains(move)) {
                movesList.add(move);
                return move;
            }
        }
        if (row > 1 && hit) {
            move = charList.get(column) + "" + (row - 1);
            if (!movesList.contains(move)) {
                movesList.add(move);
                return move;
            }
        }
        if (row < 10 && hit) {
            move = charList.get(column) + "" + (row + 1);
            if (!movesList.contains(move)) {
                movesList.add(move);
                return move;
            }
        }
        return shoot();
    }






    public void addMove(String move) {
        movesList.add(move);
    }
    public void receiveOutcome (int outcome) {
        switch (outcome) {
            case 1:// Hit
                hit = true;
                break;
            case 2: //Hit and drawn
                hit = true;
                drawn = true;
                break;
            case 3: // Miss
                hit = false;
                drawn = false;

        }
    }
}
