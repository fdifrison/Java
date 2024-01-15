package traffic;

import traffic.utils.MenuEnum;
import traffic.utils.ScannerUtils;

public class UI {


    public void run() {
        Menu.intro();
        System.out.print("Input the number of roads:");
        var roads = ScannerUtils.getUserInput();
        System.out.print("Input the interval:");
        var intervals = ScannerUtils.getUserInput();
        while (true) {
            Menu.show();
            var input = ScannerUtils.getUserSelection();
            MenuEnum selectedOption = MenuEnum.getSelection(input);
            if (input == 0) {
                selectedOption.getAction();
                return;
            } else if (selectedOption != null) {
                selectedOption.getAction();
            }

        }

    }


}
