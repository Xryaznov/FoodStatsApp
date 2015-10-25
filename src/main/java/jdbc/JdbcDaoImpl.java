package main.java.jdbc;

import main.java.food.Eatable;
import main.java.food.Meal;
import main.java.food.Product;
import main.java.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDaoImpl implements JdbcDao {
    private Connection conn;

    @Override
    public List<Product> listAllProducts() {
        ArrayList<Product> list = new ArrayList<>();

        try {
            Statement s = createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM PRODUCT;");

            while (rs.next()) {
                list.add(new Product(rs.getString("NAME"),
                        rs.getDouble("PROTEIN"),
                        rs.getDouble("FAT"),
                        rs.getDouble("CARB"),
                        rs.getDouble("KCAL")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Eatable getProductById(int id) {
        Product product = null;

        try {
            Statement s = createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT * FROM PRODUCT WHERE ID =" + id + ";");

            while (rs.next()) {
                product = new Product(rs.getString("NAME"),
                        rs.getDouble("PROTEIN"),
                        rs.getDouble("FAT"),
                        rs.getDouble("CARB"),
                        rs.getDouble("KCAL"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // TODO Name field should be unique
    @Override
    public int getProductIdByName(String productName) {
        int id = 0;

        try {
            Statement s = createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT * FROM PRODUCT WHERE NAME = "
                            + "\""
                            + (productName)
                            + "\""
                            + ";");

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("ID"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List listCurrentDayMeals() {
        ArrayList<Meal> list = new ArrayList<>();

        try {
            Statement s = createStatement();

            ResultSet rs = s.executeQuery(
                    "SELECT * FROM MEAL WHERE DATE = "
                            + "\"" + Utils.getCurrentDay()
                            + "\""
                            + ";");

            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("ID"));

                list.add(new Meal(rs.getString("DATE"),
                        (Product) getProductById(id),
                        rs.getDouble("WEIGHT")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insertProduct(Product product) {
        try {
            Statement s = createStatement();

            StringBuilder sb = new StringBuilder("INSERT INTO PRODUCT VALUES (NULL, ");
            sb.append("'").append(product.getName()).append("'").append(", ");
            sb.append(product.getProtein()).append(", ");
            sb.append(product.getFat()).append(", ");
            sb.append(product.getCarb()).append(", ");
            sb.append(product.getKcal()).append(");");

            System.out.println("Executing query " + sb.toString());

            s.executeUpdate(sb.toString());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean insertMeal(String date, String productName, double weight) {
        try {
            int id = getProductIdByName(productName);
            Statement s = createStatement();

            StringBuilder sb = new StringBuilder("INSERT INTO MEAL VALUES (");
            sb.append("\"").append(date).append("\", ");
            sb.append(id).append(", ");
            sb.append(weight).append(");");

            System.out.println("Executing query " + sb.toString());

            s.executeUpdate(sb.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return true;
    }

    // TODO Use id in WHERE condition
    // TODO logging
    @Override
    public boolean update(Product product, Product newData) {
        StringBuilder sql = new StringBuilder("UPDATE ");

        sql.append("PRODUCT ")
                .append("SET PROTEIN = '")
                .append(newData.getProtein()).append("', ")
                .append("FAT ='")
                .append(newData.getFat()).append("', ")
                .append("CARB ='")
                .append(newData.getCarb()).append("', ")
                .append("KCAL = '")
                .append(newData.getKcal()).append("' ")
                .append("WHERE NAME = '").append(product.getName())
                .append("';");

        System.out.println(sql.toString());

        try {
            Statement s = createStatement();
            s.executeUpdate(sql.toString());

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // TODO Meal needs id to be deleted (you CAN eat same products in one day)
    @Override
    public boolean delete(Eatable eatable) {
        StringBuilder sql = new StringBuilder();

        if (eatable instanceof Product) {
            sql.append("DELETE FROM PRODUCT WHERE NAME = '")
                    .append(((Product) eatable).getName())
                    .append("';");
        } else if (eatable instanceof Meal) {
            throw (new UnsupportedOperationException("Not yet implemented"));
        }

        try {
            Statement s = createStatement();
            s.executeUpdate(sql.toString());

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private Statement createStatement()
            throws SQLException {
        conn = DriverManager
                .getConnection("jdbc:sqlite:foodStats.db");

        return conn.createStatement();
    }

}
