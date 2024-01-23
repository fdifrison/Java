package advisor.pattern.command;

import advisor.pattern.invoker.Button;

public class NewReleaseCmd implements ICommand {

    private final Button newReleaseButton;

    public NewReleaseCmd(Button newReleaseButton) {
        this.newReleaseButton = newReleaseButton;
    }

    @Override
    public void execute() {
        this.newReleaseButton.print();
    }


}
