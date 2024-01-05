package cinema;

public class ScreenRoom {
    int rows;
    int premiumRows;
    final int premiumTicket = 10;
    int economyRows;
    final int economyTicket = 8;
    int seatsPerRow;

    int reservedSeats = 0;
    int totalSeats;

    float percentageOfReservedSeats = 0.00f;

    int currentIncome = 0;

    String[][] room;
    Integer maxProfit;

    public ScreenRoom(int rows, int seatsPerRow) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.totalSeats = seatsPerRow * rows;
        this.premiumRows = this.totalSeats <= 60 ? rows : rows / 2;
        this.economyRows = rows - premiumRows;
        createRoom(seatsPerRow, rows);
    }

    void createRoom(int seatsPerRow, int rows) {
        room = new String[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                room[i][j] = "S";
            }
        }
    }

    Integer getMaxProfit() {
        if (this.maxProfit == null) {
            this.maxProfit = this.premiumRows * this.seatsPerRow * this.premiumTicket +
                    this.economyRows * this.seatsPerRow * this.economyTicket;
        }
        return this.maxProfit;
    }

    boolean buyTicket(int rowNum, int seatNum) {
        int price = getTicketPrice(rowNum, seatNum);
        if (reserveSeat(rowNum, seatNum)) {
            printRoom();
            currentIncome += price;
            return true;
        } else return false;
    }

    int getTicketPrice(int rowNum, int seatNum) {
        int ticketPrice = 0;
        if (rowNum <= this.premiumRows) {
            ticketPrice = premiumTicket;
        } else {
            ticketPrice = economyTicket;
        }
        System.out.println("Ticket price: $" + ticketPrice);
        return  ticketPrice;
    }

    boolean reserveSeat(int rowNum, int seatNum) {
        if (rowNum > this.rows || seatNum > this.seatsPerRow) {
            System.out.println("Wrong input!");
            return false;
        } else if ("B".equals(room[rowNum - 1][seatNum - 1])) {
            System.out.println("That ticket has already been purchased!");
            return false;
        } else {
            room[rowNum - 1][seatNum - 1] = "B";
            this.reservedSeats++;
            return true;
        }
    }

    void printRoom() {
        System.out.println("\nCinema:");
        System.out.print("  ");
        // seat numbers
        for (int i = 1; i <= this.seatsPerRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        // row numbers
        for (int i = 0; i < this.rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < this.seatsPerRow; j++) {
                System.out.print(" " + room[i][j]);
            }
            System.out.println();
        }
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public float getPercentageOfReservedSeats() {
        percentageOfReservedSeats = (float) reservedSeats / totalSeats * 100f;
        return percentageOfReservedSeats;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }
}



