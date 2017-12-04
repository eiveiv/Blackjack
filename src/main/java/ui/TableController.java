package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Card;
import model.CardView;
import model.Deck;
import model.Hand;

import java.net.URL;
import java.util.ResourceBundle;

import static model.CardSuit.HEARTS;
import static model.CardSuit.SPADES;
import static model.CardValue.*;

public class TableController implements Initializable {

    @FXML
    private Button drawCard;

    @FXML
    private TextArea samHand;

    @FXML
    private HBox samCards;

    @FXML
    private HBox dealerCards;

    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 140;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("startup");

        //Programlogikk
        Deck newDeck = startNewGame();
        Hand samsHand = new Hand();
        Hand dealer = new Hand();
        samsHand.addCard(newDeck.drawCard());
        dealer.addCard(newDeck.drawCard());
        samsHand.addCard(newDeck.drawCard());
        dealer.addCard(newDeck.drawCard());

        CardView cardView = new CardView(dealer.getCards().get(0));
        CardView cardView1 = new CardView(dealer.getCards().get(1));

        dealerCards.getChildren().add(cardView);
        dealerCards.getChildren().add(cardView1);

        CardView cardView3 = new CardView(samsHand.getCards().get(0));
        CardView cardView4 = new CardView(samsHand.getCards().get(1));

        samCards.getChildren().add(cardView);
        samCards.getChildren().add(cardView1);

        if (samsHand.isBlackjack() && dealer.isBlackjack()) {
            //Sam vinner
        }
        if (samsHand.isDoubleAces() && dealer.isDoubleAces()) {
            //Dealer vinner
        }

        drawCard.addEventFilter(MouseEvent.MOUSE_CLICKED, (event -> {
            samsHand.addCard(newDeck.drawCard());
            samCards.getChildren().add( new CardView(samsHand.getCards().get(2)));
            if (samsHand.isBusted()) {

            }

            drawCard.setDisable(samsHand.getTotalValue() >= 17);

            System.out.println(samsHand.getTotalValue());
        }));
    }

    private Deck startNewGame() {
        Deck deck = new Deck();
        deck.createDeck();
        deck.shuffleNewDeck();
//        deck.getCards().add(new Card(SPADES, FIVE));
//        deck.getCards().add(new Card(SPADES, FOUR));
//        deck.getCards().add(new Card(SPADES, THREE));
//        deck.getCards().add(new Card(SPADES, TWO));
//
//        deck.getCards().add(new Card(HEARTS, FIVE));
//        deck.getCards().add(new Card(HEARTS, FOUR));
//        deck.getCards().add(new Card(HEARTS, THREE));
//        deck.getCards().add(new Card(HEARTS, TWO));

        return deck;
    }

    private void gameOver(Hand samsHand, Hand dealerHand) {
        if (samsHand.isBusted()) {

        }

    }
}
