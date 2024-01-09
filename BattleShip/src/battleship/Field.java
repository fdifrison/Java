package battleship;

import battleship.ship.Ship;
import battleship.utils.FieldUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Field {

    private final String FOG = "~";
    private final String SHIP  = "O";
    private final String HIT  = "X";
    private final String MISS  = "M";
    List<Ship> ships = new ArrayList<>();
    private String[][] field = {
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

    void setShipOnField(int i, int j) {
        field[i][j] = SHIP;
    }

    void setHitOrMiss(int i, int j) {
        if (field[i][j].equals(SHIP)) {
            field[i][j] = HIT;
        } else {
            field[i][j] = MISS;
        }
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }
}
