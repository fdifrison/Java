package mealplanner.persistance;

import mealplanner.dao.entity.DayEnum;
import mealplanner.dao.entity.DayOfWeek;
import mealplanner.dao.entity.Ingredient;
import mealplanner.dao.entity.MealEnum;
import mealplanner.meal.MealFactory;
import mealplanner.meal.MealFactory.MealBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PsqlLocalDbClient {

    private static final String DB_URL = "jdbc:postgresql:meals_db";
    private static final String USER = "postgres";
    private static final String PASS = "1111";


    private Connection connection;

    public PsqlLocalDbClient() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
            createTables();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to obtain a connection.", e);
        }
    }

    private Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to obtain a connection.", e);
        }
        return connection;
    }


    private void createTables() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS meals (" +
                    "meal_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                    "category VARCHAR(20)," +
                    "meal VARCHAR(50)" +
                    ")"
            );
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ingredients (" +
                    "ingredient_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                    "meal_id INTEGER REFERENCES meals(meal_id)," +
                    "ingredient VARCHAR(50)" +
                    ")"
            );
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS plan (" +
                    "day_of_week VARCHAR(20)," +
                    "category VARCHAR(20)," +
                    "meal_id INTEGER REFERENCES meals(meal_id)" +
                    ")"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create tables.", e);
        }

    }


    public void insert(MealFactory meal) {
        String insertMeal = "INSERT INTO meals (category, meal) VALUES (?, ?)";
        String insertIngredient = "INSERT INTO ingredients (meal_id, ingredient) VALUES (?, ?)";
        try (PreparedStatement mealStatement = getConnection().prepareStatement(insertMeal, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement ingStatement = getConnection().prepareStatement(insertIngredient)) {
            mealStatement.setString(1, meal.getCategory());
            mealStatement.setString(2, meal.getName());
            int rows = mealStatement.executeUpdate();

            if (rows != 0) {
                ResultSet keys = mealStatement.getGeneratedKeys();
                if (keys.next()) {
                    meal.getIngredients().forEach(ingredient -> {
                        try {
                            ingStatement.setInt(1, keys.getInt(1));
                            ingStatement.setString(2, ingredient.getIngredient());
                            ingStatement.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while adding meal to database!");
        }
    }

    public List<MealFactory> getMealsByCategory(MealEnum category) {
        List<MealFactory> returnList = new ArrayList<>();
        String sqlMeal = String.format("SELECT * FROM meals WHERE meals.category = '%s'", category.name());
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlMeal)) {
            ResultSet meals = statement.executeQuery();

            if (!meals.next()) {
                return returnList;
            } else {
                do {
                    MealFactory meal = new MealBuilder()
                            .setCategory(meals.getString("category"))
                            .setName(meals.getString("meal"))
                            .createMeal();
                    var mealId = meals.getInt("meal_id");
                    String sqlIngredients = String.format("SELECT * FROM ingredients WHERE ingredients.meal_id = %s", mealId);
                    try (Statement innerStatement = getConnection().createStatement()) {
                        ResultSet ingredients = innerStatement.executeQuery(sqlIngredients);
                        if (!ingredients.next()) {
                            continue;
                        } else {
                            do {
                                String ingredient = ingredients.getString("ingredient");
                                meal.setIngredient(new Ingredient(ingredient));
                            } while (ingredients.next());
                        }
                        returnList.add(meal);
                    }
                } while (meals.next());
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving list of meals.");
        }
        return returnList;
    }

    public List<String> getMealsNamesByCategory(MealEnum category) {
        List<String> returnList = new ArrayList<>();
        String sqlMeal = String.format("SELECT * FROM meals WHERE meals.category = '%s' ORDER BY meals.meal ASC", category.name());
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlMeal)) {
            ResultSet meals = statement.executeQuery();

            if (!meals.next()) {
                return returnList;
            } else {
                do {
                    returnList.add(meals.getString("meal"));
                } while (meals.next());
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving list of meals names.");
        }
        return returnList;
    }

    public void clearPlanTable() {
        String sqlMeal = String.format("TRUNCATE TABLE plan");
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlMeal)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting rows form plan tables.");
        }
    }

    public void addDayToPlan(DayOfWeek day) {
        day.mealForCategory.forEach((key, value) -> {
            var meal_id = getMealId(value);
            String sql = "INSERT INTO plan (day_of_week, category, meal_id) VALUES (?, ?, ?)";
            try (Connection connection = getConnection();
                 PreparedStatement insert = connection.prepareStatement(sql)) {
                insert.setString(1, day.getDay());
                insert.setString(2, key);
                insert.setInt(3, meal_id);
                insert.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Cannot Insert into Plan", e);
            }
        });
    }

    public DayOfWeek getPlanForDay(DayEnum day) {
        DayOfWeek DoW = null;
        String sqlMeal = String.format("SELECT day_of_week, plan.category, meal\n" +
                "FROM plan\n" +
                "         inner join meals\n" +
                "                    on plan.meal_id = meals.meal_id\n" +
                "WHERE plan.day_of_week = '%s'", day.name());
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlMeal)) {
            ResultSet plan = statement.executeQuery();
            if (!plan.next()) {
                return DoW;
            } else {
                DoW = new DayOfWeek(day.name());
                do {
                    var category = plan.getString("category");
                    var meal = plan.getString("meal");
                    DoW.mealForCategory.replace(category, meal);
                } while (plan.next());
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving list of meals names.");
        }
        return DoW;
    }


    private int getMealId(String meal) {
        String sql = String.format("SELECT meals.meal_id FROM meals WHERE meals.meal = '%s'", meal);
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("meal_id");
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving list of meals names.");
        }
        return 0;
    }

    public List<Ingredient> getIngredientForDay(DayOfWeek day) {
        var ingredients = new ArrayList<Ingredient>();
        for (String entry : day.mealForCategory.values()) {
            var mealId = getMealId(entry);
            String sql = String.format("SELECT ingredients.ingredient FROM ingredients WHERE ingredients.meal_id = %d", mealId);
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ingredients.add(new Ingredient(resultSet.getString("ingredient")));
                }
            } catch (SQLException e) {
                System.out.println("Error while retrieving list of meals names.");
            }
        }
        return ingredients;
    }


}
