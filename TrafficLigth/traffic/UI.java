package traffic;

import traffic.utils.MenuEnum;
import traffic.utils.ScannerUtils;

public class UI {


    public void run() {
        Menu.intro();
        System.out.print("Input the number of roads:");
        var roads = ScannerUtils.getUserInput("Error! Incorrect Input. Try again:");
        System.out.print("Input the interval:");
        var intervals = ScannerUtils.getUserInput("Error! Incorrect Input. Try again:");
        ScannerUtils.clearScreen();
        while (true) {
            Menu.show();
            var selection = ScannerUtils.getUserSelection();
            var action = MenuEnum.getSelection(selection);
            if (isQUIT(action)) return;
            action.getAction();
            ScannerUtils.readAndClearScreen();

        }

    }

    private static boolean isQUIT(MenuEnum action) {
        if (action.equals(MenuEnum.QUIT)) {
            action.getAction();
            return true;
        }
        return false;
    }


}
