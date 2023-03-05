import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpponentSimulator implements Serializable {
    private static final long serialVersionUID = 2L;
    private GameHelper helper;
    private List<String> movesList;
    private List<String> hitList;
    private List<Character> charList;
    private boolean hit;
    private boolean drawn;

    public OpponentSimulator() {
        helper = new GameHelper();
        movesList = new ArrayList<>();
        hitList = new ArrayList<>();
        charList = helper.getCharList();

    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public String shoot() {
        Random generator = new Random();
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
        Random generator = new Random();


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
        if (!hitList.isEmpty()) {
            column = charList.indexOf(hitList.get(hitList.size() - 1).charAt(0));
            row = Integer.parseInt(hitList.get(hitList.size() - 1).substring(1));
        }

        String move = "";

        if (column > 0 && !hitList.isEmpty() ) {
            move = charList.get(column - 1) + "" + row;
            if (!movesList.contains(move)) {
                movesList.add(move);
                return move;
            }
        }
        if (column < 9 && !hitList.isEmpty()) {
            move = charList.get(column + 1) + "" + row;
            if (!movesList.contains(move)) {
                movesList.add(move);
                return move;
            }
        }
        if (row > 1 && !hitList.isEmpty()) {
            move = charList.get(column) + "" + (row - 1);
            if (!movesList.contains(move)) {
                movesList.add(move);
                return move;
            }
        }
        if (row < 10 && !hitList.isEmpty()) {
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
                hitList.add(movesList.get(movesList.size()-1));
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
