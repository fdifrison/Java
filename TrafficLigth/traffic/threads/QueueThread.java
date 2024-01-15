package traffic.threads;

import traffic.utils.ScannerUtils;

public class QueueThread extends Thread {

    private ThreadState state;
    int executionTime = 0;

    int roads;
    int intervals;


    public int getExecutionTime() {
        return executionTime;
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
            if (this.state != ThreadState.NOT_STARTED) {
                try {
                    this.sleep(1000);
                    executionTime++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.state == ThreadState.ON_SYSTEM) {
                System.out.printf("! %ds. have passed since system startup !%n", getExecutionTime());
                System.out.printf("! Number of roads: %s !%n", roads);
                System.out.printf("! Interval: %s !%n", intervals);
                System.out.println("! Press \"Enter\" to open menu !");
                ScannerUtils.clearScreen();
                continue;
            }
        }
    }


    public void setState(ThreadState state) {
        this.state = state;
    }
}
