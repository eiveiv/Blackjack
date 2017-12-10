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

    public void createShuffleNewDeck() {
        System.out.println("Creating new random shuffled deck");
        createDeck();
        Collections.shuffle(this.cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    private void createDeck() {
        this.cards = new ArrayList<>();
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
