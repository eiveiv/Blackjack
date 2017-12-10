package model;

import org.junit.Assert;
import org.junit.Test;

import static model.CardSuit.CLUBS;
import static model.CardSuit.HEARTS;
import static model.CardSuit.SPADES;
import static model.CardValue.FIVE;
import static model.CardValue.JACK;
import static model.CardValue.TEN;

public class CardTest {

    @Test
    public void getCardShortName() {
        Card card1 = new Card(CLUBS, JACK);
        Assert.assertTrue("CJ".equals(card1.getShortName()));

        Card card2 = new Card(HEARTS, FIVE);
        Assert.assertTrue("H5".equals(card2.getShortName()));

        Card card3 = new Card(SPADES, TEN);
        Assert.assertTrue("S10".equals(card3.getShortName()));
    }
}
