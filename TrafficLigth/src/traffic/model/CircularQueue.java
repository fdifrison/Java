package traffic.model;

import java.util.ArrayDeque;
import java.util.Comparator;

public class CircularQueue extends ArrayDeque<Road> {

    int numElements;

    int intervals;



    public CircularQueue(UserConfig config) {
        super(config.roads());
        this.numElements = config.roads();
        this.intervals = config.intervals();
    }


    @Override
    public void addLast(Road road) {
        if (this.isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        super.addLast(road);
    }


    public boolean isFull() {
        return this.size() == numElements;
    }

    public void tempPrint() {
        for (Road road : this) {
            System.out.println(road.getName());
        }
    }


}
