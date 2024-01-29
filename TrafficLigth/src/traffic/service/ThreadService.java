package traffic.service;

import traffic.threads.QueueThread;
import traffic.threads.ThreadState;
import traffic.utils.ScannerUtils;

public class ThreadService {

    QueueThread thread;

    public ThreadService(QueueThread thread) {
        this.thread = thread;
    }

    private void setThreadState(ThreadState state) {
        this.thread.setState(state);
    }

    public void startThread() {
        setThreadState(ThreadState.STARTED);
        this.thread.start();
    }

    public void openThread() {
        setThreadState(ThreadState.ON_SYSTEM);
        ScannerUtils.readEmptyLine();
        setThreadState(ThreadState.STARTED);
    }

    public void killThread() {
        setThreadState(ThreadState.KILL);
    }

}
