package traffic.utils;

import java.io.IOException;
import java.util.Scanner;

public class ScannerUtils {

    static Scanner scanner = new Scanner(System.in);

    private ScannerUtils() {
    }

    public static void readEmptyLine() {
        scanner.nextLine();
    }
    public static String readLine() {
        return scanner.nextLine();
    }

    public static int getUserInput(String exceptionMsg) {
        try {
            int input;
            if ((input = Integer.parseInt(scanner.nextLine())) <= 0) {
                System.out.print(exceptionMsg);
                getUserInput(exceptionMsg);
            }
            return input;
        } catch (NumberFormatException e) {
            System.out.print(exceptionMsg);
            return getUserInput(exceptionMsg);
        }
    }

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static int getUserSelection() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void clearScreen(boolean pressEnterToContinue) {
        try {
            if (pressEnterToContinue) System.in.read();
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
