package traffic.menu;

public class MainMenu {

    protected MainMenu() {
    }

    public static void intro() {
        System.out.println("Welcome to the traffic management system!");
    }

    public static void show() {
        System.out.println("MainMenu:");
        for (Menu s : Menu.values()) {
            if (s.equals(Menu.ERROR)) continue;
            System.out.printf("%d. %s%n", s.getValue(), s.getDescription());
        }

    }

}
