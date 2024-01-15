package traffic.threads;

public class AddThread implements ITrafficThread {

    String description = "Road added";

    @Override
    public void run() {
        System.out.println("Road added");
    }

    public void getDescription() {
        System.out.println(description);
    }

}
