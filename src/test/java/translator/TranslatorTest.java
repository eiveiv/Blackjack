package translator;

import model.Card;
import model.CardSuit;
import model.CardValue;
import model.Deck;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TranslatorTest {

    @Test
    public void mapToCard() {
        Card card = Translator.doMap("CA");
        Card clubsOfAce = new Card(CardSuit.CLUBS, CardValue.ACE);
        Assert.assertTrue(clubsOfAce.getValue() == card.getValue());
        Assert.assertTrue(clubsOfAce.getSuit() == card.getSuit());
    }

    @Test
    public void readFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cardDeck.csv");
        Deck deck = Translator.toDeck(inputStream);
        Assert.assertFalse(deck.getCards().isEmpty());
        Assert.assertTrue(deck.getCards().get(1).getSuit() == CardSuit.DIAMONDS);
    }


}
