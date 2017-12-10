package model;

import org.junit.Assert;
import org.junit.Test;

public class CardValueTest {

    @Test
    public void checkNumericValueKing() {
        Integer numericValue = CardValue.KING.getNumericValue();
        Assert.assertTrue(10 == numericValue);
    }

    @Test
    public void checkNumericValueAce() {
        Integer numericValue = CardValue.ACE.getNumericValue();
        Assert.assertTrue(11 == numericValue);
    }

    @Test
    public void checkNumericValueFive() {
        Integer numericValue = CardValue.FIVE.getNumericValue();
        Assert.assertTrue(5 == numericValue);
    }
}
