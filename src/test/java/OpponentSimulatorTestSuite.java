import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OpponentSimulatorTestSuite {

    @Test
    void getRandomCoordinateTest() {

        OpponentSimulator simulator = new OpponentSimulator();
        for (int i = 0; i < 50; i++) {
            System.out.println(simulator.shoot());
        }
    }
    @Test
    void computerBoardTest() {
        OpponentSimulator simulator = new OpponentSimulator();
        Player player = simulator.getBoardForComputer();
        BoardPrinter.printBoard(player);
    }
    @Test
    void movesSimulatorColumnMinusTest() {
        //Given
        OpponentSimulator simulator = new OpponentSimulator();
        simulator.addMove("B2");
        //When
        simulator.setHit(true);
        String move = simulator.movesSimulator();

        //Then
        Assertions.assertEquals("A2", move);

    }

    @Test
    void movesSimulatorColumnPlusTest() {
        //Given
        OpponentSimulator simulator = new OpponentSimulator();
        simulator.addMove("A2");
        simulator.addMove("B2");
        //When
        simulator.setHit(true);
        String move = simulator.movesSimulator();

        //Then
        Assertions.assertEquals("C2", move);
    }
}
