package service;

import model.Deck;
import model.Hand;
import org.apache.commons.io.FileUtils;
import translator.Translator;

import java.io.File;

public class GameService {

    public boolean playerWon(Hand playerHand, Hand dealerHand) {
        if (playerHand.isBlackjack() || (dealerHand.isBusted() && !dealerHand.isDoubleAces() && !playerHand.isDoubleAces())
                || (playerHand.getTotalValue() > dealerHand.getTotalValue() && !playerHand.isBusted())
                || !playerHand.isBusted() && dealerHand.isBusted()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean winnerFromStart(Hand playerHand, Hand dealerHand) {
        if (playerHand.isBlackjack() || dealerHand.isBlackjack() || playerHand.isDoubleAces() || dealerHand.isDoubleAces()) {
            return true;
        } else {
            return false;
        }
    }

    public Deck createDeckFromFile(String fileName) throws Exception{
        System.out.println("Creating new deck from" + fileName);
        File file = new File(fileName);
        String stringDeck = FileUtils.readFileToString(file, "UTF-8");
        return Translator.toDeck(stringDeck);
    }
}
