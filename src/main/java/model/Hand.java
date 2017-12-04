package model;

import java.util.ArrayList;
import java.util.List;

import static model.CardValue.ACE;

public class Hand {

    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    //TODO denne viser 1 for mye
    public int getTotalValue() {
        int value = 0;
        for (Card c : this.cards) {
            value += c.getNumericValue();
        }
        return value;
    }

    public boolean isBlackjack() {
        if (2 == this.cards.size() && 21 == this.getTotalValue()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDoubleAces() {
        if (this.cards.size() == 2) {
            return 2 == this.cards.stream().filter(c -> c.getValue() == ACE).count();
        } else {
            return false;
        }
    }

    public boolean isBusted() {
        return this.getTotalValue() > 21;
    }
}
