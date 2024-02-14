package mealplanner.menu.buttons;

public class ExitBtn implements Button{
    @Override
    public void pressBtn() {
        System.out.println("Bye!");
        System.exit(0);
    }
}
