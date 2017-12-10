package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Deck;
import model.Hand;
import org.apache.commons.lang3.StringUtils;
import service.GameService;

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
    private Label dealerScore;

    @FXML
    private Label playerScore;

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

        String filePath = null;
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
        playerScore.setText("");
        dealerScore.setText("");
    }

    private void dealerDraw(Hand dealerHand, Hand samsHand, Deck deck) {
        while(dealerHand.getTotalValue() <= samsHand.getTotalValue()) {
            dealerCards.getChildren().add(drawCard(dealerHand, deck));
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
                if (!gameService.isValidDeck(newDeck)) {
                    System.out.println("Invalid deck, cant finish a game");
                    clearInputFile();
                    newDeck.createShuffleNewDeck();
                }
            } catch (Exception e) {
                System.out.println("Failed creating deck from inputfile");
                clearInputFile();
                newDeck.createShuffleNewDeck();
            }
        } else {
            newDeck.createShuffleNewDeck();
        }

        return newDeck;
    }

    private void clearInputFile() {
        fileName.setVisible(false);
        fileName.setText("");
        removeFile.setVisible(false);
    }

    private void gameOver(Hand playerHand, Hand dealerHand) {
        boolean playerWon = gameService.playerWon(playerHand, dealerHand);
        String result;

        if (playerWon) {
            result = PLAYER + "\n" + PLAYER + ":" + playerHand.toString() + "\n" + DEALER + dealerHand.toString();
        } else {
            result = DEALER + "\n" + DEALER + ":" + dealerHand.toString() + "\n" + PLAYER + ":" + playerHand.toString();
        }
        playerScore.setText(playerHand.getTotalValue().toString());
        dealerScore.setText(dealerHand.getTotalValue().toString());
        resultText.setText(result);
    }
}
