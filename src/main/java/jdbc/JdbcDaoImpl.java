package main.java.jdbc;

import main.java.food.Eatable;
import main.java.food.Meal;
import main.java.food.Product;
import main.java.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDaoImpl implements JdbcDao
{
    private Connection conn;

    @Override
    public List<Eatable> listAllProducts()
    {
        ArrayList<Eatable> list = new ArrayList<>();
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM PRODUCT;");

            while (rs.next())
            {
                list.add(new Product(rs.getString("NAME"), rs.getDouble("PROTEIN"), rs.getDouble("FAT"), rs.getDouble("CARB"), rs.getDouble("KCAL")));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
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
            }
        }
        return list;
    }

    @Override
    public Eatable getProductById(int id)
    {
        Product product = null;
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM PRODUCT WHERE ID =" + id + ";");

            while (rs.next())
            {
                product = new Product(rs.getString("NAME"), rs.getDouble("PROTEIN"), rs.getDouble("FAT"), rs.getDouble("CARB"), rs.getDouble("KCAL"));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
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
            }
        }
        return product;
    }

    @Override
    public int getProductIdByName(Eatable product)
    {
        int id = 0;

        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM PRODUCT WHERE NAME = " + "\"" + ((Product) product).getName() + "\"" + ";");

            while (rs.next())
            {
                id = Integer.parseInt(rs.getString("ID"));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
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
            }
        }
        return id;
    }

    @Override
    public List listCurrentDayMeals()
    {
        ArrayList<Meal> list = new ArrayList<>();

        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();

            System.out.println("Executing query " + "SELECT * FROM MEAL WHERE DATE = " + "\"" + Utils.getCurrentDay() + "\"" + ";)");

            ResultSet rs = s.executeQuery("SELECT * FROM MEAL WHERE DATE = " + "\"" + Utils.getCurrentDay() + "\"" + ";");

            while (rs.next())
            {
                int id = Integer.parseInt(rs.getString("ID"));

                System.out.println(id);

                list.add(new Meal(rs.getString("DATE"), (Product) getProductById(id), rs.getDouble("WEIGHT")));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
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
            }
        }
        return list;
    }

    @Override
    public boolean insertProduct(Product product)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO PRODUCT VALUES (NULL, ");
            sb.append("'").append(product.getName()).append("'").append(", ");
            sb.append(product.getProtein()).append(", ");
            sb.append(product.getFat()).append(", ");
            sb.append(product.getCarb()).append(", ");
            sb.append(product.getKcal()).append(");");

            System.out.println(sb.toString());

            System.out.println("Executing query " + sb.toString());

            s.executeUpdate(sb.toString());

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
            }
        }
        return true;
    }

    @Override
    public boolean insertMeal(Product product, double weight)
    {
        try
        {
            int id = getProductIdByName(product);

            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO MEAL VALUES (");
            sb.append("\"").append(Utils.getCurrentDay()).append("\", ");
            sb.append(id).append(", ");
            sb.append(weight).append(");");

            System.out.println("Executing query " + sb.toString());

            s.executeUpdate(sb.toString());
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
            }
        }
        return true;
    }

    @Override
    public boolean update(Eatable e)
    {
        return false;
    }

    @Override
    public boolean delete(Eatable e)
    {
        return false;
    }

}
