package traffic.model;

public class Road {

    private RoadState state;

    private final String name;

    private int timer;

    public Road(String name, int interval) {
        this.name = name;
        this.timer = interval;
    }

    public RoadState getState() {
        return state;
    }

    public void setState(RoadState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }


}
