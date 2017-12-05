package translator;

import model.Card;
import model.CardSuit;
import model.CardValue;
import model.Deck;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TranslatorTest {

    @Test
    public void testMapToCard() {
        Card card = Translator.doMap("CA");
        Card clubsOfAce = new Card(CardSuit.CLUBS, CardValue.ACE);
        Assert.assertTrue(clubsOfAce.getValue() == card.getValue());
        Assert.assertTrue(clubsOfAce.getSuit() == card.getSuit());
    }

    @Test
    public void readFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cardDeck.csv");
        String output = IOUtils.toString(inputStream, "UTF-8");
        List<String> stringCards = new ArrayList<>(Arrays.asList(output.split(",")));
        Deck deck = Translator.toDeck(stringCards);
        Assert.assertFalse(deck.getCards().isEmpty());
        Assert.assertTrue(deck.getCards().get(1).getSuit() == CardSuit.DIAMONDS);
    }


}
