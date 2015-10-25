package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = null;

        try {

            root = FXMLLoader.load(getClass().getResource("/main/java/views/mainWindow.fxml"));

            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add("/main/java/views/stylesheet.css");

            primaryStage.setTitle("FoodStatsApp");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
