package mealplanner.menu.buttons;

import mealplanner.meal.MealService;
import mealplanner.dao.entity.MealEnum;

import java.util.Scanner;

public class AddBtn implements Button {

    Scanner scanner = new Scanner(System.in);
    MealService service = new MealService();

    @Override
    public void pressBtn() {
        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        while (true) {
            try {
                MealEnum userInput = MealEnum.valueOf(scanner.nextLine());
                switch (userInput) {
                    case breakfast, lunch, dinner -> {
                        service.createMeal(userInput);
                        System.out.println("The meal has been added!");
                        return;
                    }
                }
            } catch (IllegalArgumentException ignored) {
                System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            }
        }
    }


}
