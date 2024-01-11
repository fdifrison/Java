package battleship.utils;

import battleship.Coordinate;

import java.util.ArrayList;
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

    public static List<Coordinate> shiftNSEW(Coordinate c) {
        List<Coordinate> list = new ArrayList<>();
        var c1 = new Coordinate(c.getX() + 1, c.getY());
        var c2 = new Coordinate(c.getX() + -1, c.getY());
        var c3 = new Coordinate(c.getX() , c.getY() +1);
        var c4 = new Coordinate(c.getX() + 1, c.getY() -1);
        var c5 = new Coordinate(c.getX() - 1, c.getY() -1);
        var c6 = new Coordinate(c.getX() + 1, c.getY() +1);
        var c7 = new Coordinate(c.getX() + 1, c.getY() -1);
        var c8 = new Coordinate(c.getX() - 1, c.getY() +1);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        list.add(c7);
        list.add(c8);
        return list;
    }

}
