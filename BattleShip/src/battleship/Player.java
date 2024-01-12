package battleship;

public class Player {

    private String name;

    private BattleField field = new BattleField();

    private BattleField fogField = new BattleField();

    public Player(String name) {
        this.name = name;
    }

    public boolean hasLost() {
        return field.isEndGame();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BattleField getField() {
        return field;
    }

    public void setField(BattleField field) {
        this.field = field;
    }

    public BattleField getFogField() {
        return fogField;
    }

    public void setFogField(BattleField fogField) {
        this.fogField = fogField;
    }
}
