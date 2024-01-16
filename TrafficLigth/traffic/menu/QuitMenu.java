package traffic.menu;

import traffic.threads.ITrafficThread;
import traffic.utils.CircularQueue;

public class QuitMenu implements IMenu {
    @Override
    public void showText() {
        System.out.println("Bye!");
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
        return null;
    }
}
