package advisor.pattern.invoker;

import advisor.Menu;

public class PlaylistButton implements Button {

    @Override
    public void print() {
        Menu.playlist();
    }


}
