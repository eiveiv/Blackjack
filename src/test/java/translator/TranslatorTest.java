package translator;

import model.Card;
import model.CardSuit;
import model.CardValue;
import model.Deck;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void readFromFile() {
        File file = new File("/home/eivind/Music/cards.csv");
        Deck deck = new Deck();
        String stringDeck = null;
        try {
            stringDeck = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            //Bruker default
        }
        List<String> cards = new ArrayList<>(Arrays.asList(stringDeck.split(",")));
        cards.forEach(c -> {
            deck.addCard( Translator.doMap(c.trim()));
        });
        System.out.println(cards);
    }


}
