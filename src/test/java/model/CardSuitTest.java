package model;

import org.junit.Assert;
import org.junit.Test;

public class CardSuitTest {

    @Test
    public void getCardSuitShortName() {
        Assert.assertTrue("C".equals(CardSuit.CLUBS.getShortName()));
        Assert.assertTrue("D".equals(CardSuit.DIAMONDS.getShortName()));
        Assert.assertTrue("S".equals(CardSuit.SPADES.getShortName()));
        Assert.assertTrue("H".equals(CardSuit.HEARTS.getShortName()));
    }
}
