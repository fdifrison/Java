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
        enterCoordinates("Aircraft Carrier", 5);
        field.printField();
        enterCoordinates("Battleship", 4);
        field.printField();
        enterCoordinates("Submarine", 3);
        field.printField();
        enterCoordinates("Cruiser", 3);
        field.printField();
        enterCoordinates("Destroyer", 2);
        field.printField();
    }


    private void enterCoordinates(String currentShip, int length) {
        System.out.printf("Enter the coordinates of the %s (%d cells):%n", currentShip, length);
        inputShip(currentShip);
    }

    private void inputShip(String currentShip) {
        var input = ScannerUtils.validateInputCoordinates(scanner.nextLine());
        var coordinates = input.stream().map(Coordinate::new).toList();
        Ship ship = createShip(currentShip, coordinates);
        if (ship != null) {
            field.tryAddShipToFleet(ship);
            if (!field.getShips().contains(ship)) {
                inputShip(currentShip);
            }
        } else {
            inputShip(currentShip);
        }
    }

    private Ship createShip(String shipType, List<Coordinate> coordinates) {
        ShipCoordinates shipCoordinates = new ShipCoordinates(coordinates);
        if (shipCoordinates.isValid()) {
            return factory.create(shipType, shipCoordinates).isValid();
        }
        return null;
    }

}



