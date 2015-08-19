import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController
{
    @FXML
    private Label label;

    @FXML
    protected void handleSubmitButtonAction()
    {
        int val = Integer.parseInt(label.getText());
        label.setText(String.valueOf(++val));
    }
}
