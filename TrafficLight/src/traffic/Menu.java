package traffic;

import traffic.utils.MenuEnum;

public class Menu {

    protected Menu() {
    }

    protected static void intro() {
        System.out.println("Welcome to the traffic management system!");
    }

    protected static void show() {
        System.out.println("Menu:");
        for (MenuEnum s : MenuEnum.values()) {
            System.out.printf("%d. %s%n", s.getValue(), s.getDescription());
        }

    }

}
