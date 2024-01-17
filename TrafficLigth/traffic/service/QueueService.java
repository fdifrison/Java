package traffic.service;

import traffic.model.CircularQueue;
import traffic.model.Road;
import traffic.model.RoadState;
import traffic.model.UserConfig;
import traffic.utils.PrintUtils;

import java.util.Comparator;

public class QueueService {

    private final CircularQueue queue;
    private final UserConfig config;


    public QueueService(UserConfig config) {
        this.config = config;
        this.queue = new CircularQueue(config);
    }

    private void printRoad(Road r) {
        var text = String.format(
                "Road \"%s\" will be %s for %ds.",
                r.getName(), r.getState(), r.getTimer());
        if (r.getState().equals(RoadState.open)) {
            PrintUtils.inGreen(text);
        } else {
            PrintUtils.inRead(text);
        }
    }

    public synchronized void printQueueState() {
        queue.forEach(this::printRoad);
        updateTimer();
        changeState();
        resetClosingRoadTimer();
        resetOpeningRoadTimer();
    }

    private void updateTimer() {
        queue.stream().
                filter(road -> road.getTimer() > 0)
                .forEach(RoadService::anotherSecond);
    }

    private void resetClosingRoadTimer() {
        int queueSize = queue.size() == 1 ? 2 : queue.size();
        queue.stream()
                .filter(RoadService::isTimeZero)
                .filter(RoadService::isClosed)
                .forEach(road -> road.setTimer(config.intervals() * (queueSize - 1)));
    }

    private void resetOpeningRoadTimer() {
        queue.stream()
                .filter(RoadService::isTimeZero)
                .filter(RoadService::isOpen)
                .forEach(road -> road.setTimer(config.intervals()));
    }

    private void changeState() {
        if (queue.size() <= 1) return;
        queue.forEach(road -> {
            if (RoadService.isTimeZero(road)) {
                if (RoadService.isOpen(road)) {
                    RoadService.closeRoad(road);
                } else RoadService.openRoad(road);
            }
        });
    }


    public CircularQueue getQueue() {
        return queue;
    }

    public void printSystemHeader(int executionTime) {
        System.out.printf("! %ds. have passed since system startup !%n", executionTime);
        System.out.printf("! Number of roads: %s !%n", config.roads());
        System.out.printf("! Interval: %s !%n", config.intervals());
    }

    public void printSystemBody() {
        printQueueState();
    }

    public void printSystemFooter() {
        System.out.println("! Press \"Enter\" to open menu !");
    }

    public int maxTimeInQueue() {
        if (queue.isEmpty()) return config.intervals();
        if (queue.size() == 1) return queue.getFirst().getTimer();

        Integer maxTime = queue.stream()
                .filter(RoadService::isClosed)
                .max(Comparator.comparingInt(Road::getTimer))
                .map(Road::getTimer).orElse(config.intervals());

        return maxTime + config.intervals();
    }

    public void alignTimersIfOpenRoadIsDeleted() {
        if (queue.size() > 2) {
            queue.stream()
                    .filter(RoadService::isClosed)
                    .filter(road -> road.getTimer() > 2 * config.intervals())
                    .forEach(road -> road.setTimer(road.getTimer() + config.intervals()));
        }
    }


}
