package traffic.menu;

import traffic.threads.ITrafficThread;
import traffic.utils.CircularQueue;
import traffic.utils.ScannerUtils;

public class AddRoadMenu implements IMenu {

    CircularQueue queue;

    @Override
    public void showText() {
    }

    @Override
    public void getUserInput() {
        System.out.print("Input road name: ");
        var roadName = ScannerUtils.readLine();
        try {
            queue.addLast(roadName);
            System.out.printf("%s added!%n", roadName);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("queue is full");
        }
    }

    @Override
    public CircularQueue initQueue(int i) {
        queue = new CircularQueue(i);
        return queue;
    }

    @Override
    public ITrafficThread getThread() {
        return null;
    }
}
