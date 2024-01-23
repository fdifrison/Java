package advisor.pattern.command;

import advisor.pattern.invoker.Button;

public class AuthCmd implements ICommand {

    private final Button button;

    public AuthCmd(Button button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.print();
    }
}
