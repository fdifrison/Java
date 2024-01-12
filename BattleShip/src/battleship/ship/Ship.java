package battleship.ship;

import battleship.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship {

    ShipCoordinates coordinates;
    int length;
    List<String> shipParts = new ArrayList<>();

    int hits = 0;

    protected Ship(ShipCoordinates coordinates) {
        this.coordinates = coordinates;
        shipParts.addAll(coordinates.getAll().stream().map(Coordinate::toString).toList());
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
        return this.coordinates.getSpaceTaken().stream().anyMatch(c -> that.coordinates.getAll().contains(c));
    }

    public int getLength() {
        return length;
    };

    public String getShipName() {
        var name = this.getClass().getSimpleName().split("(?=[A-Z])");
        return String.join(" ", name);
    }

    public Ship isValid() {
        if (this.shipParts.size() == length) {
            return this;
        } else {
            System.out.printf("Error! Wrong length of the %s! Try again:%n", getShipName());
            return null;
        }
    }

    public ShipCoordinates getCoordinates() {
        return coordinates;
    }

    public void setHits() {
        this.hits ++;
    }

    public int getHits() {
        return hits;
    }

    public boolean isDead() {
        return getLength() <= getHits();
    }
}
