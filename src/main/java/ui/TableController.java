package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Deck;
import model.Hand;

import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {
//
//    private final ObservableList<CardView> samsHandView = FXCollections.observableArrayList();
//    private final ObservableList<CardView> dealerHandView = FXCollections.observableArrayList();

    @FXML
    private Button drawCard;

    @FXML
    private HBox samCards;

    @FXML
    private HBox dealerCards;

    @FXML
    private TextArea resultText;


    Deck newDeck = new Deck();
    Hand samsHand = new Hand();
    Hand dealerHand = new Hand();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("startup");

        //Programlogikk

        startNewGame();

        if (winnerFromStart(samsHand, dealerHand)) {
            gameOver();
        }

        if (samsHand.stopDrawingCards()) {
            drawCard.setDisable(true);
            dealerDraw();
        }

        drawCard.addEventFilter(MouseEvent.MOUSE_CLICKED, (event -> {
            samCards.getChildren().add(new CardView(samsHand.addCard(newDeck.drawCard())));
            if (samsHand.isBusted()) {
                gameOver();
            } else if(samsHand.stopDrawingCards()) {
                drawCard.setDisable(true);
                dealerDraw();
            }

        }));
    }

    public void dealerDraw() {
        while(dealerHand.getTotalValue() < 17) {
            dealerCards.getChildren().add(new CardView(dealerHand.addCard(newDeck.drawCard())));
        }
        gameOver();
    }

    public boolean winnerFromStart(Hand samsHand, Hand dealerHand) {
        if (samsHand.isBlackjack() && dealerHand.isBlackjack() || samsHand.isDoubleAces() && dealerHand.isDoubleAces()) {
            return true;
        } else {
            return false;
        }
    }

    private void startNewGame() {
        newDeck = new Deck();
        newDeck.createDeck();
        newDeck.shuffleNewDeck();

        samCards.getChildren().add(new CardView(samsHand.addCard(newDeck.drawCard())));
        dealerCards.getChildren().add(new CardView(dealerHand.addCard(newDeck.drawCard())));
        samCards.getChildren().add(new CardView(samsHand.addCard(newDeck.drawCard())));
        dealerCards.getChildren().add(new CardView(dealerHand.addCard(newDeck.drawCard())));
    }

    private void gameOver() {
        boolean samWins = false;
        String result;
        if (samsHand.isBlackjack() || (dealerHand.isBusted() && !dealerHand.isDoubleAces())
                || (samsHand.getTotalValue() > dealerHand.getTotalValue() && !samsHand.isBusted())) {
            samWins = true;
        }
        if (samWins) {
            result = "sam \n" + "sam:" + samsHand.toString() + "\ndealer:" + dealerHand.toString();
        } else {
            result = "dealer \n" + "dealer:" + dealerHand.toString() + "\nsam:" + samsHand.toString();
        }
        resultText.setText(result);

    }
}
