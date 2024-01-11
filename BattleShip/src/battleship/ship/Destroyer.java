package battleship.ship;

public class Destroyer extends Ship{

    final int length = 2;
    public Destroyer(ShipCoordinates coordinates) {
        super(coordinates);
    }

    @Override
    public int getLength() {
        return length;
    }


}
