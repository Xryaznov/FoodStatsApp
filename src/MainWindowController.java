import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private TextField name;

    @FXML
    private TextField kcal;

    @FXML
    protected void handleSubmitButtonAction()
    {
        new Connector().insertValue(1, date.getText(), name.getText(), Integer.parseInt(kcal.getText()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String today = f.format(new Date().getTime());
        date.setText(today);


        label.setText(String.valueOf(new Connector().getSum()));



        name.setPromptText("Name");
        kcal.setPromptText("kcal");
    }
}
