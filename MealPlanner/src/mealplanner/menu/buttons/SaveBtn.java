package mealplanner.menu.buttons;

import mealplanner.dao.entity.DayEnum;
import mealplanner.dao.entity.Ingredient;
import mealplanner.meal.MealService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class SaveBtn implements Button {

    private final MealService service = new MealService();
    Scanner scanner = new Scanner(System.in);
    List<Ingredient> ingredientList = new ArrayList<>();

    @Override
    public void pressBtn() {
        var test = service.getDailyPlan(DayEnum.Sunday); // test if plan is empty
        if (test == null) {
            System.out.println("Unable to save. Plan your meals first.");
            return;
        }
        System.out.println("Input a filename:");
        var fileName = scanner.nextLine();
        File file = new File(fileName);
        try (PrintWriter writer = new PrintWriter(file)) {
            for (DayEnum day : DayEnum.values()) {
                var dayPlan = service.getDailyPlan(day);
                ingredientList.addAll(service.getIngredientsForDay(dayPlan));
            }
            writeIngredientsToFile(writer);
            writer.flush();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!", e);
        }
    }

    private void writeIngredientsToFile(PrintWriter writer) {
        Set<Ingredient> processedIngredients = new HashSet<>();
        ingredientList.forEach(ingredient -> {
            int count = ingredientList.stream()
                    .filter(i -> !processedIngredients.contains(ingredient))
                    .filter(i -> i.equals(ingredient))
                    .toList()
                    .size();
            if (count > 1) {
                writer.printf("%s x%d%n", ingredient.getIngredient(), count);
            } else if (count == 1){
                writer.printf("%s%n", ingredient.getIngredient());
            }
            processedIngredients.add(ingredient);
        });
    }
}
