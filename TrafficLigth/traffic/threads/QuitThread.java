package traffic.threads;

public class QuitThread implements ITrafficThread {

    String description = "Bye!";

    @Override
    public void run() {
        System.out.println("Bye!");
    }

    public void getDescription() {
        System.out.println(description);
    }
}
