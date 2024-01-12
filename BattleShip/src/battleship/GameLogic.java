package battleship;

import battleship.ship.Ship;
import battleship.ship.ShipCoordinates;
import battleship.ship.ShipFactory;
import battleship.utils.ScannerUtils;

import java.util.List;
import java.util.Scanner;

public class GameLogic {

    Scanner scanner = new Scanner(System.in);

    Player player1 = new Player("Player 1");
    Player player2 = new Player("Player 2");

    ShipFactory factory = new ShipFactory();

    public void run() {
        positioningPhase(player1);
        passTheMove();
        positioningPhase(player2);
        passTheMove();
        while (true) {
            shootingPhase(player1, player2);
            if (player2.hasLost()) break;
            shootingPhase(player2, player1);
            if (player1.hasLost()) break;
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private void passTheMove() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    private void shootingPhase(Player player, Player enemy) {
        enemy.getFogField().printField();
        System.out.println("---------------------");
        player.getField().printField();
        System.out.printf("%s, it's your turn:%n", player.getName());
        shoot(enemy);
        if (enemy.hasLost()) return;
        passTheMove();
    }

    private void shoot(Player enemy) {
        var input = ScannerUtils.validateInputShoot(scanner.nextLine());
        var coordinate = new Coordinate(input);
        boolean isValidShoot = enemy.getField().shootTo(coordinate);
        if (!isValidShoot) {
            shoot(enemy);
        }
        enemy.getFogField().setHitOrMiss(coordinate, enemy.getField().getLastShot());
        if (BattleField.HIT.equals(enemy.getField().getLastShot())) {
            if (enemy.getField().hasLastShotSankAShip()) {
                if (enemy.hasLost()) return;
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.println("You hit a ship!");
            }
        } else {
            System.out.println("You missed!");
        }
    }

    private void positioningPhase(Player p) {
        var field = p.getField();
        field.printField();
        enterCoordinates(field,"Aircraft Carrier", 5);
        field.printField();
        enterCoordinates(field, "Battleship", 4);
        field.printField();
        enterCoordinates(field, "Submarine", 3);
        field.printField();
        enterCoordinates(field, "Cruiser", 3);
        field.printField();
        enterCoordinates(field, "Destroyer", 2);
        field.printField();
    }


    private void enterCoordinates(BattleField field, String currentShip, int length) {
        System.out.printf("Enter the coordinates of the %s (%d cells):%n", currentShip, length);
        inputShip(field, currentShip);
    }

    private void inputShip(BattleField field, String currentShip) {
        var input = ScannerUtils.validateInputCoordinates(scanner.nextLine());
        var coordinates = input.stream().map(Coordinate::new).toList();
        Ship ship = createShip(currentShip, coordinates);
        if (ship != null) {
            field.tryAddShipToFleet(ship);
            if (!field.getShips().contains(ship)) {
                inputShip(field, currentShip);
            }
        } else {
            inputShip(field, currentShip);
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



