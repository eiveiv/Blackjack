package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Deck;
import model.Hand;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import service.GameService;
import translator.Translator;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private Button drawCard;

    @FXML
    private Button newGame;

    @FXML
    private HBox playerCards;

    @FXML
    private HBox dealerCards;

    @FXML
    private Label resultText;

    @FXML
    private Label fileName;

    @FXML
    private Button removeFile;

    private static final String PLAYER = "Sam";
    private static final String DEALER = "Dealer";

    private GameService gameService = new GameService();


    public void setParameter(String parameter) {
        if (parameter != null) {
            fileName.setText(parameter);
            removeFile.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("startup");
        drawCard.setDisable(true);
        newGame.setOnAction(event -> {
            game();
        });
        removeFile.setOnAction(event -> {
            removeFile.setVisible(false);
            fileName.setText("");
        });
    }

    private void game() {
        init();

        String filePath = "";
        if (fileName != null) {
            filePath = fileName.getText();
        }

        Deck deck = createDeck(filePath);
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        playerCards.getChildren().add(drawCard(playerHand, deck));
        dealerCards.getChildren().add(drawCard(dealerHand, deck));
        playerCards.getChildren().add(drawCard(playerHand, deck));
        dealerCards.getChildren().add(drawCard(dealerHand, deck));

        if (gameService.winnerFromStart(playerHand, dealerHand)) {
            gameOver(playerHand, dealerHand);
        }

        if (playerHand.stopDrawingCards()) {
            drawCard.setDisable(true);
            dealerDraw(dealerHand, playerHand, deck);
        }

        drawCard.setOnAction(event -> {
            playerCards.getChildren().add(drawCard(playerHand, deck));
            System.out.println("sam trakk trakk kort");
            if (playerHand.isBusted()) {
                drawCard.setDisable(true);
                gameOver(playerHand, dealerHand);
            } else if(playerHand.stopDrawingCards()) {
                drawCard.setDisable(true);
                dealerDraw(dealerHand, playerHand, deck);
            }
        });

    }

    private void init() {
        System.out.println("Starting new game-------------------------------------");
        playerCards.getChildren().clear();
        dealerCards.getChildren().clear();
        drawCard.setDisable(false);
        resultText.setText("");
    }

    private void dealerDraw(Hand dealerHand, Hand samsHand, Deck deck) {
        while(dealerHand.getTotalValue() <= samsHand.getTotalValue()) {
            dealerCards.getChildren().add(drawCard(dealerHand, deck));
            System.out.println("dealer trakk kort");
        }
        gameOver(samsHand, dealerHand);
    }

    private CardView drawCard(Hand hand, Deck deck) {
        return new CardView(hand.addCard(deck.drawCard()));
    }

    private Deck createDeck(String path) {
        Deck newDeck = new Deck();
        if (!StringUtils.isBlank(path)) {
            try {
                newDeck = gameService.createDeckFromFile(path);
            } catch (Exception e) {
                System.out.println("Failed creating deck from inputfile");
                fileName.setVisible(false);
                fileName.setText("");
                removeFile.setVisible(false);
                newDeck.createShuffleNewDeck();
            }
        } else {
            newDeck.createShuffleNewDeck();
        }

        return newDeck;
    }

    private void gameOver(Hand playerHand, Hand dealerHand) {
        boolean playerWon = gameService.playerWon(playerHand, dealerHand);
        String result;

        if (playerWon) {
            result = PLAYER + "\n" + PLAYER + ":" + playerHand.toString() + "\n" + DEALER + dealerHand.toString();
        } else {
            result = DEALER + "\n" + DEALER + ":" + dealerHand.toString() + "\n" + PLAYER + ":" + playerHand.toString();
        }
        resultText.setText(result);

    }
}
