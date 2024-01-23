package advisor.pattern.invoker;

import advisor.Menu;

public class FeatureButton implements Button {

    @Override
    public void print() {
        Menu.featured();
    }


}
