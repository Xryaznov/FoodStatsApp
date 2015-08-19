import java.sql.*;

public class Connector
{
    public static Connection conn;

    public static void main(String[] args)
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


    public void insertValue(int id, String date, String name, int kcal)
    {
        try
        {

            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");

            Statement s = conn.createStatement();
            s.setQueryTimeout(30);

            s.executeUpdate("INSERT INTO FOOD VALUES (" + id + ", '" + date + "'" + ", " + "'" + name + "'" + ", " + kcal + ");");

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

    public int getSum()
    {
        int sum = 0;
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM FOOD");

            while (rs.next())
            {
                sum += Integer.parseInt(rs.getString("KCAL"));
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
        System.out.println(sum);
        return sum;
    }
}

