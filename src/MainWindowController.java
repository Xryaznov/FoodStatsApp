import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable
{
    @FXML
    private TextField date;
    @FXML
    private TextField product;
    @FXML
    private TextField protein;
    @FXML
    private TextField fat;
    @FXML
    private TextField carb;
    @FXML
    private TextField kcal;
    @FXML
    private TextField weight;
    @FXML
    private Label statusBar;
    @FXML
    private Label label;

    @FXML
    protected void handleSubmitButtonAction()
    {

        if (!kcal.getText().isEmpty())
        {
            Product p = new Product(product.getText(),
                    Double.valueOf(protein.getText()),
                    Double.valueOf(fat.getText()),
                    Double.valueOf(carb.getText()),
                    Double.valueOf(kcal.getText()));
            addNewProduct(p);
            System.out.println(p.getName() + " added");
            return;
        }

        String productName = product.getText();
        System.out.println("productName = " + productName);

        Double productWeight = Double.valueOf(weight.getText());
        System.out.println("productWeight = " + productWeight);

        ProductDAOJDBCImpl jdbc = new ProductDAOJDBCImpl();
        ArrayList<Product> products = (ArrayList) jdbc.findAll();

        System.out.println(products);

        for (Product p : products)
        {
            if (p.getName().equals(productName))
            {
                System.out.println(productName + " is in the base");
                System.out.println(p);
                return;
            }
        }

        statusBar.setText("No product found in database, add data then press +");
        showAddPanel();
    }


    private void showAddPanel()
    {
        protein.visibleProperty().set(true);
        fat.visibleProperty().set(true);
        carb.visibleProperty().set(true);
        kcal.visibleProperty().set(true);
    }

    @FXML
    protected void showProductsEatenToday()
    {
        ProductDAOJDBCImpl jdbc = new ProductDAOJDBCImpl();

        ArrayList<Product> products = (ArrayList) jdbc.findAll();

        StringBuilder sb = new StringBuilder();

        for (Product p : products)
        {
            sb.append(p.getName()).append(": ").append(p.getKcal());
            sb.append("\n");
        }

        label.setStyle("-fx-font-size: 18px;");
        label.setText(sb.toString());
    }

    private void addNewProduct(Product product)
    {
        ProductDAOJDBCImpl jdbc = new ProductDAOJDBCImpl();
        jdbc.insertProduct(product);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String today = f.format(new Date().getTime());
        date.setText(today);


        label.setText("2000");


        protein.managedProperty().bind(protein.visibleProperty());
        protein.visibleProperty().set(false);

        fat.managedProperty().bind(fat.visibleProperty());
        fat.visibleProperty().set(false);

        carb.managedProperty().bind(carb.visibleProperty());
        carb.visibleProperty().set(false);

        kcal.managedProperty().bind(kcal.visibleProperty());
        kcal.visibleProperty().set(false);
    }
}
