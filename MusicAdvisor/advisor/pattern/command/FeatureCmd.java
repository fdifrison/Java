package advisor.pattern.command;

import advisor.pattern.invoker.Button;

public class FeatureCmd implements ICommand {

    private final Button button;

    public FeatureCmd(Button button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.print();
    }
}
