package mealplanner.dao.entity;

import java.util.HashMap;
import java.util.Map;

public class DayOfWeek {

    String day;

    public Map<String, String> mealForCategory = new HashMap<>();

    {
        mealForCategory.put(MealEnum.breakfast.name(), "");
        mealForCategory.put(MealEnum.lunch.name(), "");
        mealForCategory.put(MealEnum.dinner.name(), "");
    }

    public DayOfWeek(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
