package battleship;

import battleship.utils.CoordinatesUtils;
import battleship.utils.FieldUtils;
import battleship.utils.ScannerUtils;

import java.util.Objects;

import static battleship.utils.FieldUtils.numberToLetter;

public class Coordinate implements Comparable<Coordinate> {

    private final int x;
    private final int y;

    public Coordinate(String input) {
        x = ScannerUtils.getIfNumber(input.substring(1)) -1;
        y = FieldUtils.letterToNumber(input.charAt(0)) -1;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public int compareTo(Coordinate c2) {
        if (CoordinatesUtils.isShipHorizontal(this, c2)) {
            return Integer.compare(this.getX(), c2.getX());
        } else {
            return Integer.compare(this.getY(), c2.getY());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return numberToLetter(y) + x;
    }
}


