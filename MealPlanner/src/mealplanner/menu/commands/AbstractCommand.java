package mealplanner.menu.commands;

import mealplanner.menu.buttons.Button;

public class AbstractCommand implements Command {

    private final Button button;

    public AbstractCommand(Button button) {
        this.button = button;
    }

    @Override
    public void execute() {
        button.pressBtn();
    }
}
