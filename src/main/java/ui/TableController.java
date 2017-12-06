package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Deck;
import model.Hand;
import org.apache.commons.io.FileUtils;
import translator.Translator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private Button drawCard;

    @FXML
    private Button newGame;

    @FXML
    private HBox samCards;

    @FXML
    private HBox dealerCards;

    @FXML
    private TextArea resultText;

    private String path = "";



    public void setParameter(String parameter) {
        if (parameter != null) {
            path = parameter;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("startup");

        newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            game();
        }));

    }

    private void game() {

        init();

        Deck deck = createDeck();

        Hand samsHand = new Hand();
        Hand dealerHand = new Hand();

        samCards.getChildren().add(drawCard(samsHand, deck));
        dealerCards.getChildren().add(drawCard(dealerHand, deck));
        samCards.getChildren().add(drawCard(samsHand, deck));
        dealerCards.getChildren().add(drawCard(dealerHand, deck));

        if (winnerFromStart(samsHand, dealerHand)) {
            gameOver(samsHand, dealerHand);
        }

        if (samsHand.stopDrawingCards()) {
            drawCard.setDisable(true);
            dealerDraw(dealerHand, samsHand, deck);
        }

        drawCard.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            samCards.getChildren().add(drawCard(samsHand, deck));
            System.out.println("sam trakk trakk kort");
            if (samsHand.isBusted()) {
                drawCard.setDisable(true);
                gameOver(samsHand, dealerHand);
            } else if(samsHand.stopDrawingCards()) {
                drawCard.setDisable(true);
                dealerDraw(dealerHand, samsHand, deck);
            }
        }));

    }

    private void init() {
        System.out.println("Starting new game-------------------------------------");
        samCards.getChildren().clear();
        dealerCards.getChildren().clear();
        drawCard.setDisable(false);
        resultText.clear();
    }

    private Deck createDeckFromFile() throws IOException{
        System.out.println("Creating new deck from" + path);
        File file = new File(path);
        Deck deck = new Deck();
        String stringDeck = null;
        stringDeck = FileUtils.readFileToString(file, "UTF-8");
        List<String> cards = new ArrayList<>(Arrays.asList(stringDeck.split(",")));
        cards.forEach(c -> {
            deck.addCard( Translator.doMap(c.trim()));
        });

        return deck;
    }

    private void dealerDraw(Hand dealerHand, Hand samsHand, Deck deck) {
        while(dealerHand.getTotalValue() <= samsHand.getTotalValue()) {
            dealerCards.getChildren().add(drawCard(dealerHand, deck));
            System.out.println("dealer trakk kort");
        }
        gameOver(samsHand, dealerHand);
    }

    public CardView drawCard(Hand hand, Deck deck) {
        return new CardView(hand.addCard(deck.drawCard()));
    }

    public boolean winnerFromStart(Hand samsHand, Hand dealerHand) {
        if (samsHand.isBlackjack() && dealerHand.isBlackjack() || samsHand.isDoubleAces() && dealerHand.isDoubleAces()) {
            return true;
        } else {
            return false;
        }
    }

    private Deck createDeck() {
        System.out.println("Creating new deck");
        Deck newDeck = new Deck();
        try {
            newDeck = createDeckFromFile();
        } catch (IOException e) {
            newDeck.createDeck();
            newDeck.shuffleNewDeck();
        }
        return newDeck;
    }

    private void gameOver(Hand samsHand, Hand dealerHand) {
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
