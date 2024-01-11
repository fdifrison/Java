package battleship.ship;

public class Submarine extends Ship {

    final int length = 3;

    public Submarine(ShipCoordinates coordinates) {
        super(coordinates);
    }

    @Override
    public int getLength() {
        return length;
    }
}
