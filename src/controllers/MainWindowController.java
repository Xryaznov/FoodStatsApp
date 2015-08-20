package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable
{
    @FXML
    private Label label;
    @FXML
    private TextField date;
    @FXML
    private TextField product;
    @FXML
    private TextField weight;
    @FXML
    private Label statusBar;


    @FXML
    protected void handleSubmitButtonAction()
    {
        String productName = product.getText();



        // new Connector().insertValue(1, date.getText(), product.getText(), Integer.parseInt(kcal.getText()));

        addNewProduct();
        statusBar.setText("Adding new product");
    }

    private void addNewProduct()
    {
        Stage stage = new Stage();
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("../popUpWindow.fxml"));

            Scene scene = new Scene(root, 400, 400);

            scene.getStylesheets().add("stylesheet.css");

            stage.setTitle("Add product");

            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String today = f.format(new Date().getTime());
        date.setText(today);


        label.setText("TO DO");


        product.setPromptText("product");
        weight.setPromptText("weight");
    }
}
