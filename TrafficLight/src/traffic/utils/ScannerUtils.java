package traffic.utils;

import java.io.IOException;
import java.util.Scanner;

public class ScannerUtils {

    static Scanner scanner = new Scanner(System.in);

    public static int getUserSelection() {
        var input = ScannerUtils.parseInputAsInt(scanner.nextLine());
        if (!intInRange03(input)) {
            getUserSelection();
            System.out.println("Incorrect option");
            clearConsole();
        }
        return input;
    }

    private static void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException ignored) {}
    }

    public static int getUserInput() {
        var input = ScannerUtils.parseInputAsInt(scanner.nextLine());
        if (input <=0) {
            getUserInput();
        }
        return input;
    }

    private static int parseInputAsInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Input must be and integer");
            return -1;
        }
    }

    private static boolean intInRange03(int i) {
        return i >= 0 && i <= 3;
    }

}
