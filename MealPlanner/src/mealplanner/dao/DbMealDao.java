package mealplanner.dao;

import mealplanner.dao.entity.DayEnum;
import mealplanner.dao.entity.DayOfWeek;
import mealplanner.dao.entity.Ingredient;
import mealplanner.dao.entity.MealEnum;
import mealplanner.meal.MealFactory;
import mealplanner.persistance.PsqlLocalDbClient;

import java.util.List;

public class DbMealDao implements mealDao {

    private final PsqlLocalDbClient client;

    public DbMealDao() {
        this.client = new PsqlLocalDbClient();
    }

    @Override
    public List<String> finAllNamesByCategory(MealEnum category) {
        return client.getMealsNamesByCategory(category);
    }

    @Override
    public DayOfWeek getPlanForDay(DayEnum day) {
        return client.getPlanForDay(day);
    }

    @Override
    public void clearPlan() {
        client.clearPlanTable();
    }

    @Override
    public void addDayToPlan(DayOfWeek day) {
        client.addDayToPlan(day);
    }

    @Override
    public List<Ingredient> getIngredientForDay(DayOfWeek day) {
        return client.getIngredientForDay(day);
    }

    @Override
    public List<MealFactory> findAllByCategory(MealEnum category) {
        return client.getMealsByCategory(category);
    }

    @Override
    public void add(MealFactory meal) {
        client.insert(meal);
    }
}
