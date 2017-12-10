package service;

import model.Card;
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

    public boolean isValidDeck(Deck deck) {
        int count = 0;
        boolean playerCard = true;
        boolean validDeck = false;
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        if (deck.getCards().size() < 4) {
            return false;
        }

        for(Card card: deck.getCards()) {
            if (playerCard && playerHand.getTotalValue() < 17) {
                playerHand.addCard(card);
                if (count < 4) {
                    playerCard = false;
                }
            } else {
                dealerHand.addCard(card);
                playerCard = true;
            }
            if (count == 4) {
                if (winnerFromStart(playerHand, dealerHand)) {
                    return true;
                }
            }
            if (playerHand.getTotalValue() > 21 || dealerHand.getTotalValue() > 21 || dealerHand.getTotalValue() > playerHand.getTotalValue()) {
                return true;
            }
            count ++;
        }
        return validDeck;
    }
}
