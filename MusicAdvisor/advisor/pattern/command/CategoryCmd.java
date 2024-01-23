package advisor.pattern.command;

import advisor.pattern.invoker.Button;

public class CategoryCmd implements ICommand {

    private final Button button;

    public CategoryCmd(Button button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.print();
    }
}
