package main.java.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.food.Meal;
import main.java.food.Product;
import main.java.jdbc.JdbcDaoImpl;
import main.java.utils.Utils;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    private TextField date;
    @FXML
    private ComboBox comboBox;
    @FXML
    private TextField weightField;
    @FXML
    private Label statusBar;
    @FXML
    private Label label;

    @FXML
    // TODO Need to reposition statusBar, move it to the very bottom
    protected void addMeal() {
        Product product = (Product) comboBox.getSelectionModel().getSelectedItem();
        Double weight;

        if (!weightField.getText().isEmpty()) {
            weight = Double.valueOf(weightField.getText());
        } else {
            weight = 0d;
        }

        JdbcDaoImpl jdbc = new JdbcDaoImpl();

        Meal meal = new Meal(Utils.getCurrentDay(), product, weight);

        jdbc.insertMeal(Utils.getCurrentDay(), meal.getProduct().getName(), weight);

        statusBar.setText(meal.getProduct().getName()
                + " added: " + new DecimalFormat("#0.0").format(meal.calculateKcal())
                + " kcal");
    }

    @FXML
    // TODO What if decimal number length > 10?
    protected void showCurrentDayStats() {
        if (label.getText().length() < 10) {
            showCurrentDayProducts();
        } else {
            showCurrentDayKcal();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setText(Utils.getCurrentDay());
        showCurrentDayKcal();
        setComboBoxProducts();
    }

    private void setComboBoxProducts() {
        JdbcDaoImpl jdbc = new JdbcDaoImpl();
        ArrayList<Product> products = (ArrayList) jdbc.listAllProducts();
        ObservableList<Product> observableList = FXCollections.observableArrayList(products);
        comboBox.setVisibleRowCount(10);
        comboBox.setItems(observableList);
    }

    // TODO How to implement DecimalFormat("#0.0") everywhere?
    // TODO How to align list and values in a label?
    // TODO How to limit product names in length?
    // TODO What if there more products then label height? Need scroll - or smaller font?
    private void showCurrentDayProducts() {
        JdbcDaoImpl jdbc = new JdbcDaoImpl();
        ArrayList<Meal> products = (ArrayList) jdbc.listCurrentDayMeals();

        if (products.isEmpty()) {
            label.setStyle("-fx-font-size: 18px;");
            label.setText("Didn't eat anything today?");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (Meal m : products) {
            sb.append(m.getProduct().getName())
                    .append(": ")
                    .append(new DecimalFormat("#0.0").format(m.calculateKcal()));
            sb.append("\n");
        }

        label.setStyle("-fx-font-size: 18px;");
        label.setText(sb.toString());
    }

    private void showCurrentDayKcal() {
        JdbcDaoImpl jdbc = new JdbcDaoImpl();
        ArrayList<Meal> list = (ArrayList) jdbc.listCurrentDayMeals();

        double totalKcal = 0;

        for (Meal m : list) {
            totalKcal += m.calculateKcal();
        }

        NumberFormat f = new DecimalFormat("#0.0");

        label.setStyle("-fx-font-size: 180px;");
        label.setText(f.format(totalKcal));
    }
}
