package traffic.utils;

import java.util.ArrayDeque;

public class CircularQueue extends ArrayDeque<String> {

    int numElements;

    public CircularQueue(int numElements) {
        super(numElements);
        this.numElements = numElements;
    }

    @Override
    public void addFirst(String s) {
        if (this.size() < numElements) {
            super.addFirst(s);
        } else throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void addLast(String s) {
        if (this.size() < numElements) {
            super.addLast(s);
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public boolean isFull() {
        return this.size() == numElements;
    }

    public void printQueue() {
        for (String s : this) {
            System.out.printf("%s%n", s);
        }
    }
}
