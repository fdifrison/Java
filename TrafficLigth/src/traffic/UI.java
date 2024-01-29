package traffic;

import traffic.model.CircularQueue;
import traffic.model.Road;
import traffic.model.UserConfig;
import traffic.service.QueueService;
import traffic.service.RoadService;
import traffic.service.ThreadService;
import traffic.threads.QueueThread;
import traffic.utils.ScannerUtils;


public class UI {

    private UserConfig config;
    private RoadService roadService;
    private ThreadService threadService;
    private QueueService queueService;

    public void run() {
        Menu.intro();
        configPhase();
        menuSelection();
    }

    private void configPhase() {
        config = Menu.setUserConfig();
        queueService = new QueueService(config);
        roadService = new RoadService(queueService);
        threadService = new ThreadService(new QueueThread(queueService));
        threadService.startThread();
    }

    private void menuSelection() {
        while (true) {
            Menu.show();
            var selection = ScannerUtils.getUserSelection();
            switch (selection) {
                case 1:
                    addRoad();
                    break;
                case 2:
                    removeRoad();
                    break;
                case 3:
                    openSystemMenu();
                    break;
                case 0:
                    quitApp();
                    return;
                default:
                    exception();
                    break;
            }
            ScannerUtils.clearScreen(false);
        }
    }

    private static void exception() {
        System.out.println("Incorrect option!");
        ScannerUtils.readEmptyLine();
    }

    private void quitApp() {
        threadService.killThread();
        System.out.println("Bye!");
    }

    private void openSystemMenu() {
        threadService.openThread();
    }

    private void removeRoad() {
        roadService.removeRoad();
        ScannerUtils.readEmptyLine();
    }

    private void addRoad() {
        System.out.print("Input road name: ");
        var roadName = ScannerUtils.getUserInput();
        roadService.addRoad(new Road(roadName, config.intervals()));
        ScannerUtils.readEmptyLine();
    }


}
