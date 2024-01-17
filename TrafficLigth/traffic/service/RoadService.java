package traffic.service;

import traffic.model.Road;
import traffic.model.RoadState;

import java.util.NoSuchElementException;

public record RoadService(QueueService service) {

    public void addRoad(Road road) {
        try {
            setInitialRoadConfig(road);
            service.getQueue().addLast(road);
            System.out.printf("%s added!%n", road.getName());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Queue is full!");
        }
    }

    private void setInitialRoadConfig(Road r) {
        setInitialRoadState(r);
        setInitialRoadTimer(r);
    }

    private void setInitialRoadTimer(Road r) {
        r.setTimer(service.maxTimeInQueue());
    }

    private void setInitialRoadState(Road road) {
        if (service.getQueue().isEmpty()) {
            road.setState(RoadState.open);
        } else {
            road.setState(RoadState.closed);
        }
    }

    public void removeRoad() {
        try {
            Road road = service.getQueue().removeFirst();
            System.out.printf("%s deleted!%n", road.getName());
            service.alignTimersIfOpenRoadIsDeleted();
        } catch (NoSuchElementException e) {
            System.out.println("Queue is empty!");
        }
    }



    public static void anotherSecond(Road r) {
        r.setTimer(r.getTimer() - 1);
    }

    public static void openRoad(Road r) {
        r.setState(RoadState.open);
    }

    public static void closeRoad(Road r) {
        r.setState(RoadState.closed);
    }

    public static boolean isOpen(Road r) {
        return r.getState().equals(RoadState.open);
    }

    public static boolean isClosed(Road r) {
        return r.getState().equals(RoadState.closed);
    }

    public static boolean isTimeZero(Road r) {
        return r.getTimer() == 0;
    }

}
