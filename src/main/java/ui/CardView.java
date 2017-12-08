package ui;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Card;

import java.net.URL;


public class CardView extends Pane{

    public CardView(Card card) {
        getChildren().addAll(createCard(card.getShortName()));
    }

    private Rectangle createCard(String shortName) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(80);
        rectangle.setHeight(80);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setArcWidth(8);
        rectangle.setArcHeight(8);
        URL url = getClass().getResource("/images/" + shortName + ".png");
        Image image = new Image(url.toString());
        rectangle.setFill(new ImagePattern(image));

        return rectangle;
    }



}
