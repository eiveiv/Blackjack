package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private Button drawCard;

    @FXML
    private TextArea samHand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("startup");

        drawCard.addEventFilter(MouseEvent.MOUSE_CLICKED, (event -> {
            samHand.setText("Nå trakk du et kort");
        }));
    }
}
