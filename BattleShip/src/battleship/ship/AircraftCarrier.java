package battleship.ship;

public class AircraftCarrier extends Ship {

    final int length = 5;
    public AircraftCarrier(ShipCoordinates coordinates) {
        super(coordinates);
    }

    @Override
    public int getLength() {
        return length;
    }


}
