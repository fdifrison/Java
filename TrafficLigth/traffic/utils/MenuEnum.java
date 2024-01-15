package traffic.utils;

public enum MenuEnum {
    ADD_ROAD(1, "Add road", "Road added"),
    DELETE_ROAD(2, "Delete road", "Road deleted"),
    OPEN_SYSTEM(3, "Open system", "System opened"),
    QUIT(0, "Quit", "Bye!"),

    ERROR(-1, "Error","Incorrect option");

    final int value;

    final String description;

    final String action;

    MenuEnum(int value, String description, String action) {
        this.value = value;
        this.description = description;
        this.action = action;
    }

    public int getValue() {
        return value;
    }



    public String getDescription() {
        return description;
    }



    public void getAction() {
        System.out.println(action);
    }

    public static MenuEnum getSelection(int i) {
        for (MenuEnum m : MenuEnum.values()) {
            if (m.getValue() == i) return m;
        }
        return ERROR;
    }




}

