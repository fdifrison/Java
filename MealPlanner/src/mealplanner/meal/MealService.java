package mealplanner.meal;

import mealplanner.dao.DbMealDao;
import mealplanner.dao.entity.DayEnum;
import mealplanner.dao.entity.DayOfWeek;
import mealplanner.dao.entity.Ingredient;
import mealplanner.dao.entity.Meal;
import mealplanner.dao.entity.MealEnum;
import mealplanner.meal.MealFactory.MealBuilder;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MealService {

    Scanner scanner = new Scanner(System.in);

    DbMealDao dao = new DbMealDao();

    public void createMeal(MealEnum category) {
        Meal mealType = new Meal(category.name(), setMealName());
        List<Ingredient> ingredients = setIngredients();
        var meal = new MealBuilder()
                .setCategory(mealType.getCategory())
                .setName(mealType.getMeal())
                .setIngredients(ingredients)
                .createMeal();
        dao.add(meal);
    }

    public List<MealFactory> getMealsByCategory(MealEnum category) {
        return dao.findAllByCategory(category);
    }
    public List<String> getMealsNamesByCategory(MealEnum category) {
        return dao.finAllNamesByCategory(category);
    }

    public void addDayPlan(DayOfWeek day) {
        dao.addDayToPlan(day);
    }

    public DayOfWeek getDailyPlan(DayEnum day) {
        return dao.getPlanForDay(day);
    }

    public void clearPlan() {
        dao.clearPlan();
    }

    private String setMealName() {
        System.out.println("Input the meal's name:");
        while (true) {
            var input = scanner.nextLine();
            //regex works for only letters and spaces
            if (input.strip().matches("[a-zA-Z ]+")) return input;
            System.out.println("Wrong format. Use letters only!");
        }
    }

    private List<Ingredient> setIngredients() {
        System.out.println("Input the ingredients:");
        while (true) {
            var input = Stream.of(scanner.nextLine().split(",")).map(String::strip).collect(Collectors.toList());
            //regex works for only letters and spaces
            boolean isValid = input.stream().allMatch(s -> s.matches("[a-zA-Z ]+"));
            if (isValid) return input.stream().map(Ingredient::new).collect(Collectors.toList());
            System.out.println("Wrong format. Use letters only!");
        }
    }

    public List<Ingredient> getIngredientsForDay(DayOfWeek day) {
        return dao.getIngredientForDay(day);
    }

    public void printMeal(MealFactory meal) {
        System.out.printf("Name: %s%n", meal.getName());
        System.out.println("Ingredients:");
        meal.getIngredients().stream().map(Ingredient::getIngredient).forEach(System.out::println);
    }

    public void printPlan() {
        for (DayEnum day : DayEnum.values()) {
            var plan = getDailyPlan(day);
            System.out.println(plan.getDay());
            System.out.println("Breakfast: " + plan.mealForCategory.get(MealEnum.breakfast.name()));
            System.out.println("Lunch: " + plan.mealForCategory.get(MealEnum.lunch.name()));
            System.out.println("Dinner: " + plan.mealForCategory.get(MealEnum.dinner.name()));
        }
    }

}
