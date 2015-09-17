package main.java.jdbc;

import main.java.food.Eatable;
import main.java.food.Product;

import java.util.List;

public interface JdbcDao
{
    List<Eatable> listAllProducts();

    Eatable getProductById(int id);

    int getProductIdByName(Eatable product);

    List listCurrentDayMeals();

    boolean insertProduct(Product product);

    boolean insertMeal(Product product, double weight);

    boolean update(Eatable e);

    boolean delete(Eatable e);
}
