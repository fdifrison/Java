package advisor.pattern.invoker;

import advisor.Menu;

public class QuitButton implements Button{
    @Override
    public void print() {
        Menu.goodbye();
    }
}
