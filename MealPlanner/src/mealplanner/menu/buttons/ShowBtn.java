package mealplanner.menu.buttons;

import mealplanner.meal.MealService;
import mealplanner.dao.entity.MealEnum;

import java.util.Scanner;

public class ShowBtn implements Button {

    private final MealService service = new MealService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void pressBtn() {
        System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
        while (true) {
            try {
                MealEnum userInput = MealEnum.valueOf(scanner.nextLine());
                switch (userInput) {
                    case breakfast, lunch, dinner -> {
                        var meals = service.getMealsByCategory(userInput);
                        if (meals.isEmpty()) {
                            System.out.println("No meals found.");
                            return;
                        }
                        System.out.printf("Category: %s%n", userInput);
                        meals.forEach(service::printMeal);
                        return;
                    }
                }

            } catch (IllegalArgumentException ignored) {
                System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            }
        }
    }
}
