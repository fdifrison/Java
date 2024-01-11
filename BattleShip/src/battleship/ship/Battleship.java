package battleship.ship;

public class Battleship extends Ship{

    final int length = 4;
    public Battleship(ShipCoordinates coordinates) {
        super(coordinates);
    }

    @Override
    public int getLength() {
        return length;
    }


}
