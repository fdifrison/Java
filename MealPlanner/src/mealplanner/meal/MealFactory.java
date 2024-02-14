package mealplanner.meal;

import mealplanner.dao.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MealFactory {

    private String category;
    private String name;
    private final List<Ingredient> ingredients = new ArrayList<>();

    private MealFactory() {
    }

    public static class MealBuilder {

        private final MealFactory meal = new MealFactory();

        public MealBuilder setCategory(String category) {
            meal.category = category;
            return this;
        }

        public MealBuilder setName(String name) {
            meal.name = name;
            return this;
        }

        public MealBuilder setIngredients(List<Ingredient> ingredients) {
            meal.ingredients.addAll(ingredients);
            return this;
        }

        public MealBuilder setIngredient(Ingredient ingredient) {
            meal.ingredients.add(ingredient);
            return this;
        }

        public MealFactory createMeal() {
            return meal;
        }
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealFactory meal = (MealFactory) o;
        return Objects.equals(getCategory(), meal.getCategory()) && Objects.equals(getName(), meal.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getName(), getIngredients());
    }
}
