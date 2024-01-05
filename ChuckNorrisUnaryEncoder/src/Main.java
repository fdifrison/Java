
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        boolean isActive = true;
        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            System.out.println("Please input operation (encode/decode/exit):");
            String input = scanner.nextLine();
            switch (input) {
                case "encode":
                    System.out.println("Input string:");
                    String msgToEncode = scanner.nextLine();
                    if (!msgToEncode.isEmpty()) encode(msgToEncode);
                    break;
                case "decode":
                    System.out.println("Input encoded string:");
                    String msgToDecode = scanner.nextLine();
                    if (!msgToDecode.isEmpty()) decode(msgToDecode);
                    break;
                case "exit":
                    System.out.println("Bye!");
                    isActive = false;
                    break;
                default:
                    System.out.println("There is no '" + input + "' operation");
            }
        }
    }

    private static void decode(String input) {
        if (!input.matches("^[0\\s]*$")) {
            System.out.println("Encoded string is not valid.");
            return;
        }
        List<String> parsedZerosList = List.of(input.split(" "));
        if ((parsedZerosList.size() % 2 != 0)) {
            System.out.println("Encoded string is not valid.");
            return;
        }
        var parsedZerosListGroupedByTwo = partitionList(parsedZerosList, 2);
        if (parsedZerosListGroupedByTwo.stream().anyMatch(l -> l.get(0).length() > 2)) {
            System.out.println("Encoded string is not valid.");
            return;
        }
        var binary = parsedZerosListGroupedByTwo.stream().map(Main::toBinaryEncoder).collect(Collectors.joining());
        var binaryGroupedBySeven = splitString(binary, 7);
        if (binaryGroupedBySeven.stream().anyMatch(s -> s.length() != 7)) {
            System.out.println("Encoded string is not valid.");
            return;
        }
        var output = binaryGroupedBySeven.stream().map(s -> (char) Integer.parseInt(s, 2)).toList();
        StringBuilder finale = new StringBuilder();
        output.forEach(finale::append);
        System.out.println("Decoded string:");
        System.out.println(finale);
    }


    private static void encode(String input) {
        StringBuilder binaryInput = binaryEncoder(input);
        SplitBinaryInput splitBinary = getSplitBinaryInput(binaryInput);
        StringBuilder end = binaryToChuckNorrisEncoder(splitBinary.all(), splitBinary.ones(), splitBinary.zeros());
        System.out.println("Encoded string:");
        System.out.println(end);
    }

    private static SplitBinaryInput getSplitBinaryInput(StringBuilder binaryInput) {
        List<String> all = List.of(binaryInput.toString().split(""));
        List<String> ones = new java.util.ArrayList<>(Stream.of(binaryInput.toString().split("0")).filter(s -> !s.isEmpty()).toList());
        List<String> zeros = new java.util.ArrayList<>(Stream.of(binaryInput.toString().split("1")).filter(s -> !s.isEmpty()).toList());
        return new SplitBinaryInput(all, ones, zeros);
    }

    private record SplitBinaryInput(List<String> all, List<String> ones, List<String> zeros) {
    }

    private static StringBuilder binaryToChuckNorrisEncoder(List<String> all, List<String> ones, List<String> zeros) {
        StringBuilder end = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < all.size(); i++) {
            if (i < counter) continue;
            int len;
            if (all.get(i).equals("1")) {
                len = getLenAndPop(ones);
                end.append(0).append(" ");
            } else {
                len = getLenAndPop(zeros);
                end.append("00").append(" ");
            }
            end.append("0".repeat(len));
            end.append(" ");
            counter += len;
        }
        return end;
    }

    private static StringBuilder binaryEncoder(String input) {
        StringBuilder output = new StringBuilder();
        for (char ch : input.toCharArray()) {
            output.append(String.format("%7s", Integer.toBinaryString(ch)).replace(' ', '0'));
        }
        return output;
    }

    private static int getLenAndPop(List<String> list) {
        return list.remove(0).length();
    }

    public static List<List<String>> partitionList(List<String> inputList, int partitionSize) {
        List<List<String>> partitions = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i += partitionSize) {
            int end = Math.min(i + partitionSize, inputList.size());
            partitions.add(inputList.subList(i, end));
        }

        return partitions;
    }

    public static List<String> splitString(String inputString, int groupSize) {
        List<String> splitStrings = new ArrayList<>();

        for (int i = 0; i < inputString.length(); i += groupSize) {
            int end = Math.min(i + groupSize, inputString.length());
            splitStrings.add(inputString.substring(i, end));
        }

        return splitStrings;
    }

    private static String toBinaryEncoder(List<String> input) {
        var base = input.get(0).equals("0") ? "1" : "0";
        return base.repeat(input.get(1).length());
    }
}