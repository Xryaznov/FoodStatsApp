package main.java;

import main.java.food.Product;
import main.java.jdbc.JdbcDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreationClass
{
    public static void main(String[] args)
    {
        boolean successfully = false;

        try
        {
            successfully = createDatabase();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

        if (successfully)
        {
            System.out.println("Successfully.");
        }
        else
        {
            System.err.println("Unsuccessfully.");
        }
    }

    public static boolean createDatabase()
    {
        Connection conn = null;
        try
        {
            // Class.forName("jdbc:sqlite:foodStats.db");
            // classpath?

            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();
            s.setQueryTimeout(30);


            try
            {
                System.out.println("Dropping tables...");

                s.executeUpdate("DROP TABLE IF EXISTS FOOD");
                s.executeUpdate("DROP TABLE IF EXISTS PRODUCT");
                s.executeUpdate("DROP TABLE IF EXISTS MEAL");

                System.out.println("Creating tables...");

                s.executeUpdate("CREATE TABLE PRODUCT (ID INTEGER PRIMARY KEY, NAME TEXT, PROTEIN REAL, FAT REAL, CARB REAL, KCAL REAL);");
                s.executeUpdate("CREATE TABLE MEAL (DATE TEXT, ID INTEGER, WEIGHT REAL, FOREIGN KEY(ID) REFERENCES PRODUCT (ID));");

                Product product1 = new Product("ЙогуртВолошПоле", 2.5, 2.8, 19.6, 113.6);
                Product product2 = new Product("ФитнессБатNestle", 87, 23.5);

                System.out.println("Inserting products...");

                JdbcDaoImpl jdbc = new JdbcDaoImpl();

                jdbc.insertProduct(product1);
                jdbc.insertProduct(product2);

                System.out.println("Inserting meals...");


                jdbc.insertMeal(product1, 350);
                jdbc.insertMeal(product2, 23.5);
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
                return false;
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            return false;
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
}

