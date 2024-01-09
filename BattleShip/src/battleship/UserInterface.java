package battleship;

import battleship.ship.Ship;
import battleship.ship.ShipCoordinates;
import battleship.ship.ShipFactory;
import battleship.utils.ScannerUtils;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);
    Field field = new Field();

    ShipFactory factory = new ShipFactory();

    public void run() {
        field.printField();
        enterCoordinates();
    }


    private void enterCoordinates() {
        System.out.println("Enter the coordinates of the ship:");
        var input = ScannerUtils.validateInputCoordinates(scanner.nextLine());
        var coordinates = input.stream().map(Coordinate::new).toList();
        Ship ship = createShip("Aircraft Carrier", coordinates);
        field.addShip(ship);
    }

    private Ship createShip(String shipType, List<Coordinate> coordinates) {
        ShipCoordinates shipCoordinates = new ShipCoordinates(coordinates);
        if (shipCoordinates.isValid()) {
            Ship ship = factory.create(shipType, shipCoordinates);
            ship.printShipDetails();
            return ship;
        }
        return null;
    }

}



