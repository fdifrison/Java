package battleship.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ScannerUtils {

    public static List<String> validateInputCoordinates(String input) {
        List<String> stringList = new ArrayList<>(2);
        var splitInput = input.trim().split(" ");
        if (isLength2(splitInput))  stringList.addAll(Arrays.asList(splitInput));
        return stringList;
    }

    static boolean isLength2(String[] splitInput) {
        return splitInput.length == 2 && splitInput[0].length() <= 3 && splitInput[1].length() <= 3;
    }

//    static boolean hasValidNumber(String[] splitInput) {
//        return isValidNumber(splitInput[0].charAt(1)) && isValidNumber(splitInput[1].charAt(1));
//    }


    public static int getIfNumber(String c) {
        try {
            return Integer.parseInt(c);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

//    static boolean hasValidLetter(String[] splitInput) {
//        try {
//            var firstNum = Integer.parseInt(splitInput[0]);
//            var secondNum = Integer.parseInt(splitInput[4]);
//            return isValidRange(firstNum) && isValidRange(secondNum) && firstNum != secondNum;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }

    static boolean isValidLetter(String letter) {
        return FieldUtils.ROWS.contains(letter);
    }


}
