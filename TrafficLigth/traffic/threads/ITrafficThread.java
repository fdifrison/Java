package traffic.threads;

import traffic.utils.CircularQueue;

public interface ITrafficThread {

    void startThread(int... args);
    void setThreadState(ThreadState state);

    void setQueue(CircularQueue queue);


}
