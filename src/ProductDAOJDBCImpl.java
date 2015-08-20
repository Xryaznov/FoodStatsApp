import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOJDBCImpl implements ProductDAO
{
    private static Connection conn;

    public static void init()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();
            s.setQueryTimeout(30);

            s.executeUpdate("DROP TABLE IF EXISTS FOOD");
            s.executeUpdate("CREATE TABLE FOOD (ID INTEGER, DATE STRING, NAME STRING, KCAL INTEGER)");
        }
        catch (SQLException e)
        {
            System.err.print(e.getMessage());
        }
        finally
        {
            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Product> findAll()
    {
        ArrayList<Product> list = new ArrayList<>();
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM FOOD);");

            while (rs.next())
            {
                list.add(new Product(rs.getString("NAME"),
                        rs.getDouble("PROTEINS"),
                        rs.getDouble("LIPIDS"),
                        rs.getDouble("CARBS"),
                        rs.getDouble("KCAL")));
            }
        }
        catch (SQLException e)
        {
            System.err.print(e.getMessage());
        }
        finally
        {
            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
        return list;
    }

    @Override
    public List<Product> findByDate()
    {
        return null;
    }

    @Override
    public List<Product> findByName()
    {
        return null;
    }

    @Override
    public boolean insertProduct(Product product)
    {
        return false;
    }

    @Override
    public boolean updateProduct(Product product)
    {
        return false;
    }

    @Override
    public boolean deleteProduct(Product product)
    {
        return false;
    }
}
