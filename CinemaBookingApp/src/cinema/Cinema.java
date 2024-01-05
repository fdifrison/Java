package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        menu.init();

        while (true) {
            int selection = menu.mainMenu();
            switch (selection) {
                case 1:
                    menu.getScreenRoom().printRoom();
                    break;
                case 2:
                    menu.selectSeat();
                    break;
                case 3:
                    menu.getStatistics();
                    break;
                case 0:
                    return;
            }
        }
    }

}