package battleship;

import battleship.ship.Ship;
import battleship.ship.ShipCoordinates;
import battleship.utils.ScannerUtils;

import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);
    Field field = new Field();

    public void run() {
        field.printField();
        enterCoordinates();
    }


    private void enterCoordinates() {
        System.out.println("Enter the coordinates of the ship:");
        var input = ScannerUtils.validateInputCoordinates(scanner.nextLine());
        var coordinates = input.stream().map(Coordinate::new).toList();
        ShipCoordinates shipCoordinates =  new ShipCoordinates(coordinates);
        if (shipCoordinates.isValid()) {
            Ship ship = new Ship(shipCoordinates);
            ship.printShipDetails();
            field.addShip(ship);
        } else {
            System.out.println("Error!");
        }

    }


}
