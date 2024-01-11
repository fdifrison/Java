package battleship.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldUtils {

    public static final List<String> ROWS = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
    public static final String[] COLS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static Map<Character, Integer> letterToNumberMap = new HashMap<>();
    static Map<Integer, Character> numberToLetterMap = new HashMap<>();

    static {
        letterToNumberMap.put('A', 1);
        letterToNumberMap.put('B', 2);
        letterToNumberMap.put('C', 3);
        letterToNumberMap.put('D', 4);
        letterToNumberMap.put('E', 5);
        letterToNumberMap.put('F', 6);
        letterToNumberMap.put('G', 7);
        letterToNumberMap.put('H', 8);
        letterToNumberMap.put('I', 9);
        letterToNumberMap.put('J', 10);

        for (Map.Entry<Character, Integer> entry : letterToNumberMap.entrySet()) {
            numberToLetterMap.put(entry.getValue(), entry.getKey());
        }
    }

    public static int letterToNumber(char c) {
        return letterToNumberMap.getOrDefault(c, -1);
    }

    public static String numberToLetter(int i) {
        return String.valueOf(numberToLetterMap.getOrDefault(i, '!'));
    }


}
