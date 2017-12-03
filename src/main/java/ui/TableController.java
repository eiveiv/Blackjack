package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import model.Card;
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
        if (samsHand.isBlackjack() && dealer.isBlackjack()) {
            //Sam vinner
        }
        if (samsHand.isDoubleAces() && dealer.isDoubleAces()) {
            //Dealer vinner
        }

        drawCard.addEventFilter(MouseEvent.MOUSE_CLICKED, (event -> {
            samsHand.addCard(newDeck.drawCard());
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
