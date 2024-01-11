package tictactoe;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] table = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "},
        };
        printTable(table);

        List<Integer> input;
        var counter = 1;

        while (true) {
            try {
                input = Stream.of(scanner.nextLine().split(" ")).map(Integer::parseInt).toList();
                var x = input.get(0);
                var y = input.get(1);
                if (table[x - 1][y - 1].equals(" ")) {
                    table[x - 1][y - 1] = counter % 2 == 0 ? "O" : "X";
                    counter++;
                    printTable(table);
                    if (isWinMove(table)) {
                        return;
                    }
                    if (counter == 10) {
                        System.out.println("Draw");
                        return;
                    }
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");

            }
        }


    }

    private static void printTable(String[][] table) {
        System.out.println("---------");
        for (String[] strings : table) {
            System.out.print("| ");
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    static boolean isWinMove(String[][] table) {
        var s1 = table[0][0] + table[0][1] + table[0][2];
        var s2 = table[1][0] + table[1][1] + table[1][2];
        var s3 = table[2][0] + table[2][1] + table[2][2];
        var s4 = table[0][0] + table[1][0] + table[2][0];
        var s5 = table[0][1] + table[1][1] + table[2][1];
        var s6 = table[0][2] + table[1][2] + table[2][2];
        var s7 = table[0][0] + table[1][1] + table[2][2];
        var s8 = table[0][2] + table[1][1] + table[2][0];
        var sList = List.of(s1, s2, s3, s4, s5, s6, s7, s8);
        if (sList.contains("XXX")) {
            System.out.println("X wins");
            return true;
        } else if (sList.contains("OOO")) {
            System.out.println("O wins");
            return true;
        } else {
            return false;
        }
    }
}
