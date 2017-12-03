package model;

import org.junit.Assert;
import org.junit.Test;

import static model.CardSuit.HEARTS;
import static model.CardSuit.SPADES;
import static model.CardValue.*;

public class HandTest {

    @Test
    public void getHandValue() {
        Hand hand = new Hand();
        hand.addCard(new Card(HEARTS, TEN));
        hand.addCard(new Card(SPADES, QUEEN));

        Assert.assertTrue(20 == hand.getTotalValue());
    }

    @Test
    public void getBlackjack() {
        Hand hand = new Hand();
        hand.addCard(new Card(HEARTS, ACE));
        hand.addCard(new Card(SPADES, QUEEN));

        Assert.assertTrue(hand.isBlackjack());
    }

    @Test
    public void getDoubleAces() {
        Hand hand = new Hand();
        hand.addCard(new Card(HEARTS, ACE));
        hand.addCard(new Card(SPADES, ACE));

        Assert.assertTrue(hand.isDoubleAces());
    }

    @Test
    public void getNotDoubleAces() {
        Hand hand = new Hand();
        hand.addCard(new Card(HEARTS, FIVE));
        hand.addCard(new Card(SPADES, EIGHT));

        Assert.assertFalse(hand.isDoubleAces());
    }

    @Test
    public void isBusted() {
        Hand hand1 = new Hand();
        hand1.addCard(new Card(HEARTS, FIVE));
        hand1.addCard(new Card(SPADES, EIGHT));
        Assert.assertFalse(hand1.isBusted());

        Hand hand2 = new Hand();
        hand2.addCard(new Card(HEARTS, FIVE));
        hand2.addCard(new Card(SPADES, KING));
        hand2.addCard(new Card(SPADES, QUEEN));
        Assert.assertTrue(hand2.isBusted());
    }
}
