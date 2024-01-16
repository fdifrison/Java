package traffic;

import traffic.menu.MainMenu;
import traffic.menu.Menu;
import traffic.threads.ITrafficThread;
import traffic.threads.ThreadState;
import traffic.utils.CircularQueue;
import traffic.utils.ScannerUtils;

import java.util.NoSuchElementException;


public class UI {

    int roads;

    int intervals;

    static ITrafficThread systemThread;

    public void run() {
        MainMenu.intro();
        System.out.print("Input the number of roads:");
        roads = ScannerUtils.getUserInput("Error! Incorrect Input. Try again:");
        System.out.print("Input the interval:");
        intervals = ScannerUtils.getUserInput("Error! Incorrect Input. Try again:");
        systemThread = Menu.OPEN_SYSTEM.getMenu().getThread();
        systemThread.startThread(roads, intervals);
        var queue = Menu.ADD_ROAD.getMenu().initQueue(roads);
        systemThread.setQueue(queue);
        ScannerUtils.clearScreen();
        execute(queue);
    }

    public static void execute(CircularQueue queue) {
        while (true) {
            MainMenu.show();
            var action = Menu.getSelection();
            switch (action) {
                case ADD_ROAD:
                    action.getMenu().getUserInput();
                    ScannerUtils.readEmptyLine();
                    break;
                case DELETE_ROAD:
                    try {
                        var road = queue.pop();
                        System.out.printf("%s deleted%n", road);
                    } catch (NoSuchElementException e) {
                        System.out.println("queue is empty");
                    }
                    ScannerUtils.readEmptyLine();
                    break;
                case OPEN_SYSTEM:
                    systemThread.setThreadState(ThreadState.ON_SYSTEM);
                    ScannerUtils.readEmptyLine();
                    systemThread.setThreadState(ThreadState.ON_MENU);
                    break;
                case QUIT:
                    systemThread.setThreadState(ThreadState.KILL);
                    action.getMenu().showText();
                    return;
                case ERROR:
                default:
                    System.out.println("incorrect option");
                    ScannerUtils.readEmptyLine();
                    break;
            }
            ScannerUtils.clearScreen();
        }
    }


}
