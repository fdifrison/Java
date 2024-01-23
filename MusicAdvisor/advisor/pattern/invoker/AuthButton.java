package advisor.pattern.invoker;

import advisor.Menu;

public class AuthButton  implements  Button{

    @Override
    public void print() {
        Menu.auth();
    }
}
