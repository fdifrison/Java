package battleship.ship;

import battleship.Coordinate;
import battleship.utils.CoordinatesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static battleship.utils.CoordinatesUtils.isShipHorizontal;
import static battleship.utils.CoordinatesUtils.isValidCoordinates;

public class ShipCoordinates {

    Coordinate start;
    Coordinate end;

    List<Coordinate> shipCoordinates = new ArrayList<>();

    public ShipCoordinates(List<Coordinate> coordinates) {
        this.start = coordinates.get(0);
        this.end = coordinates.get(1);
        setShipCoordinates();
    }

    public boolean isValid() {
        return !start.equals(end) &&
                isValidCoordinates(start, end) &&
                Stream.of(shipCoordinates).allMatch(CoordinatesUtils::isValidRange);
    }


    private void setShipCoordinates() {
        shipCoordinates.add(start);
        if (isShipHorizontal(start, end)) {
            addCoordinatesInRangeX(start.getX(), end.getX(), start.getY());
        } else {
            addCoordinatesInRangeY(start.getY(), end.getY(), start.getX());
        }
        shipCoordinates.add(end);
    }

    private boolean isShipForward() {
        return start.compareTo(end) < 0;
    }

    private void addCoordinatesInRange(int from, int to, int fixed,
                                       BiFunction<Integer, Integer, Coordinate> coordinateFunction) {
        if (isShipForward()) {
            for (int i = from + 1; i < to; i++) {
                shipCoordinates.add(coordinateFunction.apply(i, fixed));
            }
        } else {
            for (int i = from - 1; i > to; i--) {
                shipCoordinates.add(coordinateFunction.apply(i, fixed));
            }
        }
    }

    private void addCoordinatesInRangeX(int fromX, int toX, int fixedY) {
        addCoordinatesInRange(fromX, toX, fixedY, Coordinate::new);
    }

    private void addCoordinatesInRangeY(int fromY, int toY, int fixedX) {
        addCoordinatesInRange(fromY, toY, fixedX, (a, b) -> new Coordinate(b, a));
    }


    public List<Coordinate> getAll() {
        return shipCoordinates;
    }


}
