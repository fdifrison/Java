package mealplanner.dao.entity;

public class Meal {

    int mealId;
    String category;
    String meal;

    public Meal(String category, String meal) {
        this.category = category;
        this.meal = meal;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }
}
