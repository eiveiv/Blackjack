package ui;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Card;

import java.net.URL;

//Måtte extende stackpane for å kunne bruke getchildren
public class CardView extends StackPane{

    public CardView(Card card) {

        getChildren().addAll(createCard(card.getShortName()));

    }

    private Rectangle createCard(String shortName) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(60);
        rectangle.setHeight(60);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        URL url = getClass().getResource("/images/" + shortName + ".png");
        Image image = new Image(url.toString());
        rectangle.setFill(new ImagePattern(image));

        return rectangle;
    }



}
