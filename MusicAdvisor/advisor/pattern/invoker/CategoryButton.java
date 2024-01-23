package advisor.pattern.invoker;

import advisor.Menu;

public class CategoryButton implements Button {

    @Override
    public void print() {
        Menu.categories();
    }


}
