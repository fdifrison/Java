package traffic.threads;

public class ErrorThread implements ITrafficThread{

    @Override
    public void getDescription() {
        System.out.println("Incorrect option");
    }

    @Override
    public void run() {

    }
}
