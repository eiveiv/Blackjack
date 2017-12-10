package translator;

import model.Card;
import model.CardSuit;
import model.CardValue;
import model.Deck;
import org.junit.Assert;
import org.junit.Test;

public class TranslatorTest {

    @Test
    public void mapToCard() throws Exception {
        Card card = Translator.toCard("CA");
        Card clubsOfAce = new Card(CardSuit.CLUBS, CardValue.ACE);
        Assert.assertTrue(clubsOfAce.getValue() == card.getValue());
        Assert.assertTrue(clubsOfAce.getSuit() == card.getSuit());
    }

    @Test(expected = Exception.class)
    public void mapToCardIvalid() throws Exception {
        Translator.toCard("543");
    }

    @Test
    public void readFile() throws Exception {
        String deckString = "CA, D5, H9, HQ, S8";
        Deck deck = Translator.toDeck(deckString);
        Assert.assertFalse(deck.getCards().isEmpty());
        Assert.assertTrue(deck.getCards().get(1).getSuit() == CardSuit.DIAMONDS);
    }

}
