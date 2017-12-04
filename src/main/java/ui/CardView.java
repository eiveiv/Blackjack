package ui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Card;

public class CardView extends StackPane{

    public CardView(Card card) {
        Text text = new Text();
        text.setText(String.valueOf(card.getShortName()));

        getChildren().addAll(createCard(), text);

    }

    private Rectangle createCard() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(60);
        rectangle.setHeight(60);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        return rectangle;
    }



}
