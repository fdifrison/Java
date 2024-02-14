package mealplanner.menu.buttons;

import mealplanner.dao.entity.DayEnum;
import mealplanner.dao.entity.DayOfWeek;
import mealplanner.dao.entity.MealEnum;
import mealplanner.meal.MealService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlanBtn implements Button {

    private final MealService service = new MealService();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void pressBtn() {

        service.clearPlan();

        for (DayEnum day : DayEnum.values()) {
            System.out.println(day);
            DayOfWeek dayPlan = new DayOfWeek(day.name());
            for (MealEnum cat : MealEnum.values()) {
                var meals = service.getMealsNamesByCategory(cat);
                meals.forEach(System.out::println);
                System.out.printf("Choose the %s for %s from the list above:%n", cat, day);
                while (true) {
                    var chosenMeal = scanner.nextLine();
                    if (meals.contains(chosenMeal)) {
                        dayPlan.mealForCategory.put(cat.name(), chosenMeal);
                        break;
                    }
                    System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                }
            }
            service.addDayPlan(dayPlan);
            System.out.printf("Yeah! We planned the meals for %s.%n", day);
        }

        service.printPlan();

    }
}
