package mealplanner.menu;

import mealplanner.menu.buttons.AddBtn;
import mealplanner.menu.buttons.ExitBtn;
import mealplanner.menu.buttons.PlanBtn;
import mealplanner.menu.buttons.SaveBtn;
import mealplanner.menu.buttons.ShowBtn;
import mealplanner.menu.commands.AbstractCommand;
import mealplanner.menu.commands.Command;

public class Menu {

    private final Command add;
    private final Command show;
    private final Command plan;
    private final Command save;
    private final Command exit;

    public Menu() {
        this.add = new AbstractCommand(new AddBtn());
        this.show = new AbstractCommand(new ShowBtn());
        this.plan = new AbstractCommand(new PlanBtn());
        this.save = new AbstractCommand(new SaveBtn());
        this.exit = new AbstractCommand(new ExitBtn());
    }

    public void doAdd() {
        add.execute();
    }

    public void doShow() {
        show.execute();
    }

    public void doPlan() {
        plan.execute();
    }

    public void doSave() {
        save.execute();
    }

    public void doExit() {
        exit.execute();
    }


}
