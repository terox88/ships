import java.io.*;
import java.util.Scanner;

public class TheGame {
    private Player player1;
    private Player player2;
    private GameSetter setter;
    private GameHelper helper;
    private OpponentSimulator simulator;
    private ConsoleHandler handler;
    private ObjectInputStream input;

    public TheGame() {
        setter = new GameSetter();
        helper = new GameHelper();
        simulator = new OpponentSimulator();
        handler = new ConsoleHandler();

    }

    public int shootingVerification(String shoot, Player activePlayer) {
        int outcome ;
        boolean hit = false;
        boolean drawn = false;

        if (activePlayer.equals(player1)) {

            for (Ship ship : player2.getShipList()) {
                int index = ship.getCoordinates().indexOf(shoot);
                if (index >= 0) {
                    hit = true;
                    player1.changeBoard(shoot, ship.getName());//changing board for player nr 1
                    ship.removeCoordinate(index);
                    drawn = ship.getCoordinates().isEmpty();
                    if (drawn) player2.removeShip(ship);
                    break;
                } else {
                    if(!player1.getMoveList().contains(shoot))player1.changeBoard(shoot, "<MISS>");
                    if(player1.getMoveList().contains(shoot)) return 4;
                }
            }
        } else {
            for (Ship ship : player1.getShipList()) {
                int index = ship.getCoordinates().indexOf(shoot);
                if (index >= 0) {
                    hit = true;
                    player2.changeBoard(shoot, ship.getName());//changing board for player nr 2
                    ship.removeCoordinate(index);
                    drawn = ship.getCoordinates().isEmpty();
                    if (drawn) player1.removeShip(ship);
                    break;
                } else {
                    if(!player2.getMoveList().contains(shoot)) player2.changeBoard(shoot, "<MISS>");
                    if(player2.getMoveList().contains(shoot)) return 4;
                }
            }
        }
        if (hit && !drawn) {
            outcome = 1;
        } else if (hit && drawn) {
            outcome = 2;
        } else {
            outcome = 3;
        }


        return outcome;
    }
    public boolean humanMove (String activePlayer){
        int shootCounter = 3;
        String shoot;
        boolean win =false;
        if (activePlayer.equals("P1")){
            while (shootCounter > 0) {
                ConsoleHandler.printBoard(player1);
                System.out.println("Make a shoot. You have " + shootCounter + " left");
                shoot = handler.getCoordinate();
                ConsoleHandler.printOutcome(shootingVerification(shoot, player1), shoot);
                win = player2.getShipList().isEmpty();
                if (win) return win;
                player1.addMove(shoot);
                shootCounter--;
            }
        }else {
            while (shootCounter > 0) {
                ConsoleHandler.printBoard(player2);
                System.out.println("Make a shoot. You have " + shootCounter + " left");
                shoot = handler.getCoordinate();
                ConsoleHandler.printOutcome(shootingVerification(shoot, player2), shoot);
                win = player1.getShipList().isEmpty();
                if (win) return win;
                player2.addMove(shoot);
                shootCounter--;
            }
        }
        return win;
    }



    public boolean gameRunner() {
        boolean win = false;
        System.out.println("Ahoy sailor!");
        boolean load = handler.loadGame();
        int version =1;
        if(load){
            try {
                input = new ObjectInputStream(new FileInputStream("SAVE_GAME.data"));
                version = (Integer)input.readObject();

            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            version = handler.getNumberOfPlayers();
        }
        ConsoleHandler.gameInformation();
        String activePlayer = "P1";
        if(load) {
            activePlayer = loadGame();
            if(activePlayer.equals("")) load = false;
        }
        switch (version) {
            case 1:
                String shoot;
                if (!load){
                    player1 = setter.setGameForPerson();
                    player2 = simulator.getBoardForComputer();
                    activePlayer = "P1";
                }


                while (!win) {
                    int shootCounter = 3;
                    switch (activePlayer) {
                        case "P1":
                            win = humanMove(activePlayer);
                            activePlayer = "P2";
                            boolean save = handler.saveGame();
                            if (save) return saveGame(activePlayer, version);
                            break;
                        case "P2":
                            while (shootCounter > 0) {
                                ConsoleHandler.printBoard(player2);
                                System.out.println("Computer make a move when you press enter. It has " + shootCounter + " left");
                                String pause = new Scanner(System.in).nextLine();
                                shoot = simulator.movesSimulator();
                                int outcome = shootingVerification(shoot, player2);
                                simulator.receiveOutcome(outcome);
                                ConsoleHandler.printOutcome(outcome, shoot);
                                win = player1.getShipList().isEmpty();
                                if (win) shootCounter = 0;
                                shootCounter--;
                            }
                            activePlayer = "P1";
                            break;
                    }
                }
                switch (activePlayer) {
                    case "P2":
                        System.out.println(player1.getName() + " wins!");
                        System.out.println("CONGRATULATIONS!");
                        break;
                    case "P1":
                        System.out.println(player2.getName() + " wins!");
                        System.out.println("Maybe next time :)");
                        break;

                }
                break;
            case 2:
                if (!load) {
                    player1 = setter.setGameForPerson();
                    player2 = setter.setGameForPerson();
                    activePlayer = "P1";
                }
                while (!win) {
                    switch (activePlayer) {
                        case "P1":
                            win = humanMove(activePlayer);
                            activePlayer = "P2";
                            boolean save = handler.saveGame();
                            if (save) return saveGame(activePlayer, version);
                            break;
                        case "P2":
                            win = humanMove(activePlayer);
                            activePlayer = "P1";
                            save = handler.saveGame();
                            if (save) return saveGame(activePlayer, version);
                            break;
                    }
                }
                switch (activePlayer) {
                    case "P2":
                        System.out.println(player1.getName() + " wins!");
                        System.out.println("CONGRATULATIONS!");
                        break;
                    case "P1":
                        System.out.println(player2.getName() + " wins!");
                        System.out.println("CONGRATULATIONS!");
                        break;

                }
        }
        return false;
    }

    public  boolean saveGame(String activePlayer, int version)  {
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("SAVE_GAME.data"))) {
            output.writeObject(version);
            output.writeObject(player1);
            output.writeObject(player2);
            output.writeObject(simulator);
            output.writeObject(activePlayer);


        }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Saving error");
                return false;
            }
        return true;
    }
    public String loadGame() {
        String activePlayer ="";
        try {
            player1 = (Player)input.readObject();
            player2 = (Player)input.readObject();
            simulator = (OpponentSimulator) input.readObject();
            activePlayer = (String)input.readObject();
            input.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
           e.printStackTrace();
        }
        return activePlayer;
    }

}

