package mealplanner;

import mealplanner.menu.Menu;
import mealplanner.menu.commands.CmdEnum;
import mealplanner.persistance.PsqlLocalDbClient;

import java.util.Scanner;

public class UI {

    private final Menu menu = new Menu();
    private final Scanner scanner = new Scanner(System.in);

    public UI() {
        PsqlLocalDbClient db = new PsqlLocalDbClient();
    }

    public void run() {
        while (true) {
            try {
                System.out.println("What would you like to do (add, show, plan, save, exit)?");
                CmdEnum userInput = CmdEnum.valueOf(scanner.nextLine());
                switch (userInput) {
                    case add -> menu.doAdd();
                    case show -> menu.doShow();
                    case plan -> menu.doPlan();
                    case save -> menu.doSave();
                    case exit -> menu.doExit();
                }
            } catch (IllegalArgumentException ignored) {
            }
        }

    }

}
