package cinema;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private ScreenRoom screenRoom;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void init() {
        this.screenRoom = buildScreenRoom();
    }

    public int mainMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }


    protected ScreenRoom buildScreenRoom() {
        System.out.println("Enter the number of rows:");
        var rows = this.scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        var seats = this.scanner.nextInt();
        return new ScreenRoom(rows, seats);
    }

    protected void selectSeat() {
        boolean available = true;
        while (available) {
            System.out.println("\nEnter a row number:");
            var row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            var seat = scanner.nextInt();
            if (screenRoom.buyTicket(row, seat)) {
                available = false;
            }
        }
    }

    protected void getStatistics() {
        System.out.println("Number of purchased tickets: " + screenRoom.getReservedSeats());
        System.out.printf("Percentage: %.2f%%\n", screenRoom.getPercentageOfReservedSeats());
        System.out.println("Current income: $" + screenRoom.getCurrentIncome());
        System.out.println("Total income: $" + screenRoom.getMaxProfit());
    }

    public ScreenRoom getScreenRoom() {
        return screenRoom;
    }


}
