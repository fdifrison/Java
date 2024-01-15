package traffic.utils;

import traffic.threads.*;

public enum MenuEnum {
    ADD_ROAD(1, "Add road", new AddThread()),
    DELETE_ROAD(2, "Delete road", new DeleteThread()),
    OPEN_SYSTEM(3, "Open system", new SystemThread()),
    QUIT(0, "Quit", new QuitThread()),
    ERROR(-1, "Error", new ErrorThread());

    final int value;

    final String description;

    final ITrafficThread action;

    MenuEnum(int value, String description, ITrafficThread action) {
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

    public Thread getThread() {
        Thread thread = new Thread(action);
        thread.setName(action.getClass().getSimpleName());
        return thread;
    }



    public void getAction() {
        action.getDescription();
    }



    public static MenuEnum getSelection(int i) {
        for (MenuEnum m : MenuEnum.values()) {
            if (m.getValue() == i) return m;
        }
        return ERROR;
    }


}

