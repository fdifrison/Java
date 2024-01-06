package lastpencil;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int numOfPencils = -1;
        numOfPencils = getNumOfPencils(numOfPencils, scanner);
        var player1 = "John";
        var player2 = "Jack";

        System.out.printf("Who will be the first (%s, %s):%n", player1, player2);
        String name = getFirstPlayerName(scanner, player1, player2);
        var playFirst = player1.equals(name) ? player1 : player2;
        var playSecond = player2.equals(name) ? player1 : player2;
        System.out.println("|".repeat(numOfPencils));
        int count = 1;
        while (numOfPencils > 0) {
            System.out.println(name + "'s turn");
            var maxValue = Math.min(numOfPencils, 3);
            int playerInput = 0;
            if (name.equals(player1)) {
                playerInput = getPlayerInput(maxValue, scanner, playerInput, numOfPencils);
            } else if (name.equals(player2)) {
                playerInput = takeXPencil(numOfPencils);
                System.out.printf("%d%n", playerInput);
            }
            numOfPencils -= playerInput;
            count++;
            name = count % 2 == 0 ? playSecond : playFirst;
            if (numOfPencils > 0) {
                System.out.println("|".repeat(numOfPencils));
            } else {
                System.out.printf("%s won!", name);
            }


        }
        System.out.println();

    }

    private static int takeXPencil(int numOfPencil) {
        if (numOfPencil % 4 == 0) {
            return 3;
        } else if (numOfPencil % 4 == 3) {
            return 2;
        } else if (numOfPencil % 4 == 2) {
            return 1;
        } else if (numOfPencil % 4 == 1) {
            if (numOfPencil >= 3) {
                return new Random().nextInt(1, 4);
            } else {
                return 1;
            }
        }
        return 0;
    }

    private static int getPlayerInput(int possibleValues, Scanner scanner, int playerInput, int numOfPencil) {
        while (playerInput > 3 || playerInput <= 0) {
            try {
                playerInput = Integer.parseInt(scanner.nextLine());
                if (playerInput > 3 || playerInput <= 0) {
                    System.out.println("Possible values: '1', '2' or '3'");
                } else if (playerInput > numOfPencil) {
                    System.out.println("Too many pencils were taken");
                    playerInput = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
        return playerInput;
    }

    private static String getFirstPlayerName(Scanner scanner, String player1, String player2) {
        String name = scanner.nextLine();
        while (!List.of(player1, player2).contains(name)) {
            System.out.println("Choose between 'John' and 'Jack'");
            name = scanner.nextLine();
        }
        return name;
    }

    private static int getNumOfPencils(int numOfPencils, Scanner scanner) {
        while (numOfPencils <= 0) {
            try {
                numOfPencils = Integer.parseInt(scanner.nextLine());
                if (numOfPencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
        return numOfPencils;
    }

}
