package traffic.menu;

import traffic.UI;
import traffic.utils.ScannerUtils;

public enum Menu {
    ADD_ROAD(1, "Add road", new AddRoadMenu()),
    DELETE_ROAD(2, "Delete road", new DeleteRoadMenu()),
    OPEN_SYSTEM(3, "Open system", new OpenSystemMenu()),
    QUIT(0, "Quit", new QuitMenu()),
    ERROR(-1, null, null);

    final int value;

    final String description;

    final IMenu menu;

    Menu(int value, String description, IMenu menu) {
        this.value = value;
        this.description = description;
        this.menu = menu;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public IMenu getMenu() {
        return menu;
    }


    public static Menu getSelection() {
        var i = ScannerUtils.getUserSelection();
        for (Menu m : Menu.values()) {
            if (m.getValue() == i) return m;
        }
        return ERROR;
    }


}

