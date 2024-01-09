package battleship.ship;

public class ShipFactory {

    public Ship create(String shipType, ShipCoordinates coordinates) {
        return switch (shipType) {
            case "Aircraft Carrier" -> new AircraftCarrier(coordinates);
            case "Battleship" -> new Battleship(coordinates);
            case "Submarine" -> new Submarine(coordinates);
            case "Cruiser" -> new Cruiser(coordinates);
            case "Destroyer" -> new Destroyer(coordinates);
            default -> null;
        };
    }

}
