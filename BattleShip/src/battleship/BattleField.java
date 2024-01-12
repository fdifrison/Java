package battleship;

import battleship.ship.Ship;
import battleship.utils.FieldUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleField {

    private final String FOG = "~";
    private final String SHIP = "O";
    static final String HIT = "X";
    private final String MISS = "M";

    private String lastShot;
    private boolean lastShotSankAShip = false;

    List<Ship> ships = new ArrayList<>();
    private final String[][] field = {
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
            {FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG, FOG},
    };

    void printField() {
        System.out.print(" ");
        Arrays.stream(FieldUtils.COLS).sequential().forEach(c -> System.out.print(" " + c));
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            System.out.printf("%s", FieldUtils.ROWS.get(i));
            for (int j = 0; j < field[i].length; j++) {
                System.out.printf(" %s", field[i][j]);
            }
            System.out.println();
        }
    }

    void setShipPartOnField(int i, int j) {
        field[i][j] = SHIP;
    }

    void setHitOrMiss(int i, int j) {
        if (field[i][j].equals(SHIP) || field[i][j].equals(HIT)) {
            field[i][j] = HIT;
            setHitOnShip(new Coordinate(j, i));
            setLastShot(HIT);
        } else if (field[i][j].equals(FOG) || field[i][j].equals(MISS)) {
            field[i][j] = MISS;
            setLastShot(MISS);
        }
    }


    void setHitOrMiss(Coordinate c, String hitOrMiss) {
        try {
            field[c.getY()][c.getX()] = hitOrMiss;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void setHitOnShip(Coordinate c) {
        var ship = ships.stream().filter(s -> s.getCoordinates().getAll().contains(c)).findAny().orElseThrow();
        ship.setHits();
        if (ship.isDead()) this.lastShotSankAShip = true;
    }

    public List<Ship> getShips() {
        return ships;
    }

    private void addShip(Ship ship) {
        this.ships.add(ship);
    }

    private void setShipOnField(Ship ship) {
        ship.getCoordinates().getAll().forEach(c -> field[c.getY()][c.getX()] = SHIP);
    }

    public void tryAddShipToFleet(Ship that) {
        if (ships.stream().noneMatch(ship -> ship.overlaps(that))) {
            addShip(that);
            setShipOnField(that);
        } else {
            System.out.println("Error! You placed it too close to another one. Try again:");
        }
    }

    public boolean shootTo(Coordinate c) {
        lastShotSankAShip = false;
        try {
            setHitOrMiss(c.getY(), c.getX());
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }
    }

    private void setLastShot(String lastShot) {
        this.lastShot = lastShot;
    }

    public String getLastShot() {
        return lastShot;
    }

    public boolean isEndGame() {
        return ships.stream().allMatch(Ship::isDead);
    }

    public boolean hasLastShotSankAShip() {
        return lastShotSankAShip;
    }

}
