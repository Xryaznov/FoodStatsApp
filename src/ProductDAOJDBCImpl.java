import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOJDBCImpl implements ProductDAO
{
    private static Connection conn;

    public static void main(String[] args)
    {
        try
        {
            // Class.forName("jdbc:sqlite:foodStats.db");
            // classpath?

            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();
            s.setQueryTimeout(30);

            s.executeUpdate("DROP TABLE IF EXISTS FOOD");
            s.executeUpdate("CREATE TABLE FOOD (ID INTEGER PRIMARY KEY, NAME TEXT, PROTEIN REAL, FAT REAL, CARB REAL, KCAL REAL);");
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

            ResultSet rs = s.executeQuery("SELECT * FROM FOOD;");

            while (rs.next())
            {
                System.out.println(rs.getString("NAME"));
                list.add(new Product(
                        rs.getString("NAME"),
                        rs.getDouble("PROTEIN"),
                        rs.getDouble("FAT"),
                        rs.getDouble("CARB"),
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
    public List<Product> findByName(String productName)
    {
        ArrayList<Product> list = new ArrayList<>();
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM FOOD WHERE NAME = ';" + productName + "';");

            while (rs.next())
            {
                System.out.println(rs.getString("NAME"));
                list.add(new Product(
                        rs.getString("NAME"),
                        rs.getDouble("PROTEIN"),
                        rs.getDouble("FAT"),
                        rs.getDouble("CARB"),
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
    public boolean insertProduct(Product product)
    {
        System.out.println(conn == null);
        try
        {
            if (conn.isClosed())
            {
                conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");

            }
            System.out.println(conn);
            Statement s = conn.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO FOOD VALUES (NULL, ");
            sb.append("'").append(new String(product.getName().getBytes("UTF-8"))).append("'").append(", ");
            sb.append(product.getProtein()).append(", ");
            sb.append(product.getFat()).append(", ");
            sb.append(product.getCarb()).append(", ");
            sb.append(product.getKcal()).append(");");

            System.out.println(sb.toString());
            s.executeQuery(sb.toString());

        }
        catch (SQLException e)
        {
            System.err.print(e.getMessage());
            return false;
        }
        catch (UnsupportedEncodingException e)
        {
            System.err.print(e.getMessage());
            return false;
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
        return true;
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
