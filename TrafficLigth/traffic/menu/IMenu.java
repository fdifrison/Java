package traffic.menu;

import traffic.threads.ITrafficThread;
import traffic.utils.CircularQueue;

public interface IMenu {

    void showText();

    void getUserInput();

    CircularQueue initQueue(int i);

    ITrafficThread getThread();


}
