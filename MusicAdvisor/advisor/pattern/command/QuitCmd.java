package advisor.pattern.command;

import advisor.pattern.invoker.Button;

public class QuitCmd implements ICommand {

    private final Button button;

    public QuitCmd(Button button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.print();
    }
}
