package model;


import org.junit.Assert;
import org.junit.Test;

public class DeckTest {

    @Test
    public void createNewDeck() {
        Deck deck = new Deck();
        deck.createShuffleNewDeck();
        Assert.assertTrue(52 == deck.getCards().size());
    }

    @Test
    public void drawCard() throws Exception {
        Deck deck = new Deck();
        deck.createShuffleNewDeck();
        Card card = deck.drawCard();
        Assert.assertFalse(deck.getCards().contains(card));
    }
}
