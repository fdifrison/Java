package mealplanner.dao;

import mealplanner.dao.entity.DayEnum;
import mealplanner.dao.entity.DayOfWeek;
import mealplanner.dao.entity.Ingredient;
import mealplanner.meal.MealFactory;
import mealplanner.dao.entity.MealEnum;

import java.util.List;

public interface mealDao {

    List<String> finAllNamesByCategory(MealEnum category);

    DayOfWeek getPlanForDay(DayEnum day);
    void clearPlan();

    void addDayToPlan(DayOfWeek day);

    List<Ingredient> getIngredientForDay(DayOfWeek day);

    List<MealFactory> findAllByCategory(MealEnum category);
    void add(MealFactory meal);

}
