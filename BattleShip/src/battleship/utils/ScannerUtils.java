package battleship.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScannerUtils {

    public  static String validateInputShoot(String input) {
        if (input.trim().length() <= 3) {
            return input.trim();
        } else {
            return getMockShoot();
        }
    }

    private static String getMockShoot() {
        return "Z1";
    }

    public static List<String> validateInputCoordinates(String input) {
        List<String> stringList = new ArrayList<>(2);
        var splitInput = input.trim().split(" ");
        if (isLength2(splitInput)) {
            stringList.addAll(Arrays.asList(splitInput));
            return stringList;
        } else {
            return getMockCoordinates(stringList);
        }
    }

    private static List<String> getMockCoordinates(List<String> stringList) {
        stringList.add("Z1");
        stringList.add("Z10");
        return stringList;
    }

    static boolean isLength2(String[] splitInput) {
        return splitInput.length == 2 && splitInput[0].length() <= 3 && splitInput[1].length() <= 3;
    }



    public static int getIfNumber(String c) {
        try {
            return Integer.parseInt(c);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    static boolean isValidLetter(String letter) {
        return FieldUtils.ROWS.contains(letter);
    }


}
