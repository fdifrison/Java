package traffic;

import traffic.threads.QueueThread;
import traffic.threads.ThreadState;
import traffic.utils.MenuEnum;
import traffic.utils.ScannerUtils;

import java.io.IOException;

public class UI {

    int roads;

    int intervals;

    static QueueThread systemThread;

    public void run() throws IOException {
        Menu.intro();
        System.out.print("Input the number of roads:");
        roads = ScannerUtils.getUserInput("Error! Incorrect Input. Try again:");
        System.out.print("Input the interval:");
        intervals = ScannerUtils.getUserInput("Error! Incorrect Input. Try again:");
        systemThread = new QueueThread(roads, intervals);
        systemThread.setState(ThreadState.ON_MENU);
        systemThread.start();
        ScannerUtils.clearScreen();
        while (true) {
            Menu.show();
            var selection = ScannerUtils.getUserSelection();
            var action = MenuEnum.getSelection(selection);
            if (isQUIT(action)) {
                systemThread.interrupt();
                return;
            }
            if (isSYSTEM(action)) {
                continue;
            } else {
                action.getAction();
                ScannerUtils.readAndClearScreen();
            }
        }
    }

    private static boolean isQUIT(MenuEnum action) {
        if (action.equals(MenuEnum.QUIT)) {
            action.getAction();
            return true;
        }
        return false;
    }

    private static boolean isSYSTEM(MenuEnum action) throws IOException {
        if (action.equals(MenuEnum.OPEN_SYSTEM)) {
            systemThread.setState(ThreadState.ON_SYSTEM);
            System.in.read();
            systemThread.setState(ThreadState.ON_MENU);
            return true;
        }
        return false;
    }


}
