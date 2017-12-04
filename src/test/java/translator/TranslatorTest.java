package translator;

import model.Card;
import model.CardSuit;
import model.CardValue;
import org.junit.Assert;
import org.junit.Test;

public class TranslatorTest {

    @Test
    public void testMapToCard() {
        Card card = Translator.doMap("CA");
        Card clubsOfAce = new Card(CardSuit.CLUBS, CardValue.ACE);
        Assert.assertTrue(clubsOfAce.getValue() == card.getValue());
        Assert.assertTrue(clubsOfAce.getSuit() == card.getSuit());
    }
}
