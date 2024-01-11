package battleship.ship;

public class Cruiser extends Ship{

    final int length = 3;
    public Cruiser(ShipCoordinates coordinates) {
        super(coordinates);
    }

    @Override
    public int getLength() {
        return length;
    }


}
