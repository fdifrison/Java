package traffic.threads;

import traffic.utils.CircularQueue;
import traffic.utils.ScannerUtils;

import java.util.ArrayDeque;

public class QueueThread extends Thread implements ITrafficThread {

    private ThreadState state;
    private CircularQueue queue;
    int executionTime = 0;
    int roads;
    int intervals;

    public QueueThread() {
        super("QueueThread");
        this.state = ThreadState.NOT_STARTED;
    }

    public QueueThread(int roads, int intervals) {
        super("QueueThread");
        this.state = ThreadState.NOT_STARTED;
        this.roads = roads;
        this.intervals = intervals;
    }


    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            try {
                if (this.state == ThreadState.ON_MENU) {
                    this.sleep(1000);
                    executionTime++;
                }
                if (this.state == ThreadState.ON_SYSTEM) {
                    ScannerUtils.clearScreen();
                    System.out.printf("! %ds. have passed since system startup !%n", getExecutionTime());
                    System.out.printf("! Number of roads: %s !%n", roads);
                    System.out.printf("! Interval: %s !%n", intervals);
                    queue.printQueue();
                    System.out.println("! Press \"Enter\" to open menu !");
                    this.sleep(1000);
                    executionTime++;
                } else if (this.state == ThreadState.KILL) {
                    return;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public void setRoads(int roads) {
        this.roads = roads;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    @Override
    public void startThread(int... args) {
        setRoads(args[0]);
        setIntervals(args[1]);
        setThreadState(ThreadState.ON_MENU);
        this.start();
    }

    @Override
    public void setThreadState(ThreadState state) {
        this.state = state;
    }

    @Override
    public void setQueue(CircularQueue queue) {
        this.queue = queue;
    }
}
