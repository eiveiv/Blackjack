package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("table.fxml"));
        VBox box = loader.load();  //Kaller initialize
        List<String> unnamed = getParameters().getUnnamed();
        TableController tableController = loader.getController();
        if (!unnamed.isEmpty()) {
            tableController.setParameter(unnamed.get(0));
        }
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
