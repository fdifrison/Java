package traffic.threads;

import traffic.model.UserConfig;
import traffic.service.QueueService;
import traffic.utils.ScannerUtils;

import java.util.Timer;
import java.util.TimerTask;

public class QueueThread extends Thread {

    private ThreadState state;
    private final QueueService queueService;
    private final Timer timer = new Timer();

    int executionTime = 0;

    public QueueThread( QueueService queueService) {
        super("QueueThread");
        this.state = ThreadState.NOT_STARTED;
        this.queueService = queueService;
    }


    @Override
    public synchronized void run() {
        while (!currentThread().isInterrupted()) {
                if (this.state == ThreadState.ON_SYSTEM) {
                    ScannerUtils.clearScreen(false);
                    printSystemInfo();
                    try {
                        this.wait(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (this.state == ThreadState.KILL) {
                    timer.cancel();
                    return;
                }
        }
    }

    @Override
    public synchronized void start() {
        timer();
        super.start();
    }

    private void timer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                executionTime++;
            }
        }, 0, 1000);
    }


    private synchronized void printSystemInfo() {
        queueService.printSystemHeader(executionTime);
        queueService.printSystemBody();
        queueService.printSystemFooter();
    }



    

    public void setState(ThreadState state) {
        this.state = state;
    }
}
