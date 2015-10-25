package main.java.jdbc;

import main.java.food.Product;
import main.java.utils.Utils;
import org.junit.Assert;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        JdbcDaoImpl jdbc = new JdbcDaoImpl();

        System.out.println("Listing all products:");
        jdbc.listAllProducts().forEach(System.out::println);
        System.out.println("______________________________________");

        Product product = (Product) jdbc.getProductById(3);
        Assert.assertEquals("ДольчеДесВишня", product.getName());

        int id = jdbc.getProductIdByName("ДольчеДесВишня");
        Assert.assertEquals(3, id);

        System.out.println("Listing current day meals:");
        jdbc.listCurrentDayMeals().forEach(System.out::println);
        System.out.println("______________________________________");

        System.out.println("Inserting new product into db:");
        Product product1 = new Product("test1", 1, 1, 1, 1);
        Product product2 = new Product("test2", 2, 200);
        jdbc.insertProduct(product1);
        jdbc.insertProduct(product2);
        System.out.println("______________________________________");

        System.out.println("Inserting new meal into db:");
        jdbc.insertMeal(Utils.getCurrentDay(), "ДольчеДесВишня", 200);
        System.out.println("______________________________________");

        System.out.println("Updating product into db:");
        int id1 = jdbc.getProductIdByName("ДольчеДесВишня");
        Product product3 = (Product) jdbc.getProductById(id1);
        boolean success = jdbc.update(product3, new Product("ДольчеДесВишня", 222, 222, 222, 222));
        System.out.println("Success = " + success);
        System.out.println("______________________________________");

        System.out.println("Deleting product from db:");
        int id2 = jdbc.getProductIdByName("ДольчеДесВишня");
        Product product4 = (Product) jdbc.getProductById(id2);
        boolean success1 = jdbc.delete(product4);
        System.out.println("Success = " + success1);
        System.out.println("______________________________________");
    }
}
