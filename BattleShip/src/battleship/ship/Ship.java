package battleship.ship;

import battleship.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    ShipCoordinates coordinates;
    int length;
    List<String> shipParts = new ArrayList<>();

    public Ship(ShipCoordinates coordinates) {
        this.coordinates = coordinates;
        shipParts.addAll(coordinates.getAll().stream().map(Coordinate::toString).toList());
        length = coordinates.getAll().size();
    }

    public void printShipDetails() {
        System.out.printf("Length: %d%n", length);
        System.out.print("Parts:");
        for (String part : shipParts) {
            System.out.printf(" %s", part);
        }
        System.out.println();
    }

    public boolean overlaps(Ship that) {
        return this.coordinates.getAll().stream().anyMatch(c -> that.coordinates.getAll().contains(c));
    }

}
