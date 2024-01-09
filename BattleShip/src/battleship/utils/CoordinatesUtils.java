package battleship.utils;

import battleship.Coordinate;

import java.util.List;

public class CoordinatesUtils {


    public static boolean isValidCoordinates(Coordinate c1, Coordinate c2) {
        return (isShipVertical(c1, c2) || isShipHorizontal(c1, c2)); // same number or same letter
    }

    public static boolean isShipHorizontal(Coordinate c1, Coordinate c2) {
        // es A1 A4
        return c1.getY() == c2.getY();
    }

    public static boolean isShipVertical(Coordinate c1, Coordinate c2) {
        // es. A1 D1
        return c1.getX() == c2.getX();
    }

    public static boolean isValidRange(List<Coordinate> coordinates) {
        return coordinates.stream().allMatch(c ->
                isValidRange(c.getX()) && isValidRange(c.getY())
        );
    }

    private static boolean isValidRange(int num) {
        return num > 0 && num <= 10;
    }

}
