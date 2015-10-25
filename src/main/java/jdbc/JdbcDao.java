package main.java.jdbc;

import main.java.food.Eatable;
import main.java.food.Product;

import java.sql.SQLException;
import java.util.List;

public interface JdbcDao
{
    List<Product> listAllProducts();

    Eatable getProductById(int id);

    int getProductIdByName(String productName);

    List listCurrentDayMeals();

    boolean insertProduct(Product product);

    boolean insertMeal(String date, String productName, double weight);

    boolean update(Product product, Product newData);

    boolean delete(Eatable e);
}
