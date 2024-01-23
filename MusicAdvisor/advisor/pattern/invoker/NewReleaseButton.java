package advisor.pattern.invoker;

import advisor.Menu;

public class NewReleaseButton implements Button {

    @Override
    public void print() {
        Menu.release();
    }


}
