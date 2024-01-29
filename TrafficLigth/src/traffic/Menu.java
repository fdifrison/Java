package traffic;

import traffic.model.UserConfig;
import traffic.utils.ScannerUtils;

public class Menu {

    private final static String MENUITEMS = """
            Menu:
            1. Add road
            2. Delete road
            3. Open system
            0. Quit
            """;

    static UserConfig userConfig;

    protected Menu() {
    }

    public static void intro() {
        System.out.println("Welcome to the traffic management system!");
    }

    public static void show() {
        System.out.print(MENUITEMS);
    }

    public static UserConfig setUserConfig() {
        System.out.print("Input the number of roads:");
        var roads = ScannerUtils.getUserInput("Error! Incorrect Input. Try again:");
        System.out.print("Input the interval:");
        var intervals = ScannerUtils.getUserInput("Error! Incorrect Input. Try again:");
        userConfig = new UserConfig(roads, intervals);
        return userConfig;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

}
