package traffic.menu;

import traffic.threads.ITrafficThread;
import traffic.threads.QueueThread;
import traffic.utils.CircularQueue;

public class OpenSystemMenu implements IMenu {

    QueueThread thread = new QueueThread();

    @Override
    public void showText() {

    }

    @Override
    public void getUserInput() {

    }

    @Override
    public CircularQueue initQueue(int i) {
        return null;
    }

    @Override
    public ITrafficThread getThread() {
        return this.thread;
    }


}


