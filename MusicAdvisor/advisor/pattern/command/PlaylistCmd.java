package advisor.pattern.command;

import advisor.pattern.invoker.Button;

public class PlaylistCmd implements ICommand {
    private final Button button;

    public PlaylistCmd(Button button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.print();
    }
}
