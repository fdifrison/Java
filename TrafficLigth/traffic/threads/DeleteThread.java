package traffic.threads;

public class DeleteThread implements  ITrafficThread{

    String description = "Road deleted";
    @Override
    public void run() {
        System.out.println("Road deleted");
    }

    public void getDescription() {
        System.out.println(description);
    }
}
