import java.sql.*;

public class Main
{
    public static void main(String[] args)
    {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:foodStats.db");
            Statement s = conn.createStatement();
            s.setQueryTimeout(30);

            s.executeUpdate("DROP TABLE IF EXISTS PERSON");
            s.executeUpdate("CREATE TABLE PERSON (ID INTEGER, NAME STRING)");
            s.executeUpdate("INSERT INTO PERSON VALUES (1, 'LEO')");
            s.executeUpdate("INSERT INTO PERSON VALUES (2, 'YUI')");

            ResultSet rs = s.executeQuery("SELECT * FROM PERSON");

            while (rs.next())
            {
                System.out.println("NAME = " + rs.getString("NAME"));
                System.out.println("ID = " + rs.getString("ID"));
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
    }
}
