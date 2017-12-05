package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public Deck shuffleNewDeck() {
        Collections.shuffle(this.cards);
        return this;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void createDeck() {
        Stream.of(CardSuit.values()).forEach(cs -> {
            Stream.of(CardValue.values()).forEach(cv -> {
                this.cards.add( new Card(cs, cv));
            });
        });
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card drawCard() {
        if (!this.cards.isEmpty()) {
            Card card = this.cards.get(0);
            this.cards.remove(0);
            return card;
        } else {
            return null;
        }
    }
}
