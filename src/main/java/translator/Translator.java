package translator;

import model.Card;
import model.CardSuit;
import model.CardValue;
import model.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Translator {

    public static Card doMap(String shortName) {
        String suit = shortName.substring(0,1);
        String value = shortName.substring(1, shortName.length());
        Card card = new Card( toCardSuit(suit), toCardValue(value));
        return card;
    }

    public static CardSuit toCardSuit(String shortName) {
        for(CardSuit c: CardSuit.values()) {
            if (shortName.equals(c.getShortLetter())){
                return c;
            }
        }
        return null;
    }

    public static CardValue toCardValue(String shortName) {
        for(CardValue c: CardValue.values()) {
            if (shortName.equals(c.getShortLetter())){
                return c;
            }
        }
        return null;
    }

    public static Deck toDeck(List<String> cards) {
        Deck deck = new Deck();
        cards.forEach(c -> {
            deck.addCard( Translator.doMap(c.trim()));
        });
        return deck;
    }
}
