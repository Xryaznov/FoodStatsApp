import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    private TextField proteins;
    @FXML
    private TextField lipids;
    @FXML
    private TextField carbs;
    @FXML
    private TextField kcal;
    @FXML
    private TextField weight;
    @FXML
    private Label statusBar;

    @FXML
    protected void handleSubmitButtonAction()
    {
        String productName = product.getText();
        Double productWeight = Double.valueOf(weight.getText());

        ProductDAOJDBCImpl db = new ProductDAOJDBCImpl();

//        boolean insert = db.insertProduct(new Product("?????????????????", 2.9, 1.5, 14.8, 84));
//        System.out.println(insert);


        List products = db.findByName(productName);

        System.out.println(products.size());

        // if productName is not in db - addNewProduct; else - addNewMeal;
        // new Connector().insertValue(1, date.getText(), product.getText(), Integer.parseInt(kcal.getText()));


        addNewProduct();

        ProductDAOJDBCImpl jdbc = new ProductDAOJDBCImpl();

        System.out.println(jdbc.findAll());
    }

    @FXML
    protected void showList()
    {
        ProductDAOJDBCImpl jdbc = new ProductDAOJDBCImpl();
        label.setText(jdbc.findAll().toString());
    }

    private void addNewProduct()
    {
        proteins.visibleProperty().set(true);
        lipids.visibleProperty().set(true);
        carbs.visibleProperty().set(true);
        kcal.visibleProperty().set(true);

        statusBar.setText("No product found in database, add data then press +");



    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String today = f.format(new Date().getTime());
        date.setText(today);

        label.setText("2000");

        proteins.managedProperty().bind(proteins.visibleProperty());
        proteins.visibleProperty().set(false);

        lipids.managedProperty().bind(lipids.visibleProperty());
        lipids.visibleProperty().set(false);

        carbs.managedProperty().bind(carbs.visibleProperty());
        carbs.visibleProperty().set(false);

        kcal.managedProperty().bind(kcal.visibleProperty());
        kcal.visibleProperty().set(false);
    }
}
