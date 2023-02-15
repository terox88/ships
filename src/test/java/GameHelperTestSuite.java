import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameHelperTestSuite {


    @Test
    public void neighbourCheckTest() {

        //Given
        GameHelper hepler = new GameHelper();
        List<String> helpList = new ArrayList<>();
        helpList.add("A2");
        helpList.add("B5");
        helpList.add("B6");
        String coordinate = "A3";
        String coordinateFalse = "C8";

        //When
        boolean result = hepler.neighborCheck(coordinate, helpList);
        boolean resultFalse = hepler.neighborCheck(coordinateFalse, helpList);

        //Then
        Assertions.assertTrue(result);
        Assertions.assertFalse(resultFalse);

    }

    @Test
    void shipSetterCheckRowTest() {
        //Given
        GameHelper helper = new GameHelper();
        List<String> temporaryListTrue = new ArrayList<>();
        temporaryListTrue.add("B2");
        temporaryListTrue.add("B3");
        temporaryListTrue.add("B4");

        List<String> temporaryListFalse = new ArrayList<>();
        temporaryListFalse.add("C1");
        temporaryListFalse.add("C3");
        temporaryListFalse.add("C4");
        temporaryListFalse.add("C5");

        // When
        boolean resultTrue = helper.shipSettingCheck(temporaryListTrue);
        boolean resultFalse = helper.shipSettingCheck(temporaryListFalse);

        //Then
        Assertions.assertTrue(resultTrue);
        Assertions.assertFalse(resultFalse);

    }

    @Test
    void shipSetterCheckColumnTest() {
        //Given
        GameHelper helper = new GameHelper();
        List<String> temporaryListTrue = new ArrayList<>();
        temporaryListTrue.add("A2");
        temporaryListTrue.add("B2");
        temporaryListTrue.add("C2");

        List<String> temporaryListFalse = new ArrayList<>();
        temporaryListFalse.add("C3");
        temporaryListFalse.add("D3");
        temporaryListFalse.add("F3");
        temporaryListFalse.add("G3");

        // When
        boolean resultTrue = helper.shipSettingCheck(temporaryListTrue);
        boolean resultFalse = helper.shipSettingCheck(temporaryListFalse);

        //Then
        Assertions.assertTrue(resultTrue);
        Assertions.assertFalse(resultFalse);

    }
}




