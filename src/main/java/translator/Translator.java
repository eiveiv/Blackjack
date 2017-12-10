package translator;

import model.Card;
import model.CardSuit;
import model.CardValue;
import model.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Translator {

    public static Card toCard(String shortName) throws Exception {
        String suit = shortName.substring(0,1);
        String value = shortName.substring(1, shortName.length());
        return new Card( toCardSuit(suit), toCardValue(value));
    }

    public static CardSuit toCardSuit(String shortName) throws Exception {
        for(CardSuit c: CardSuit.values()) {
            if (shortName.equals(c.getShortName())){
                return c;
            }
        }
        throw new Exception("Invalid cardsuit");
    }

    public static CardValue toCardValue(String shortName) throws Exception {
        for(CardValue c: CardValue.values()) {
            if (shortName.equals(c.getShortName())){
                return c;
            }
        }
        throw new Exception("Invalid cardsuit");
    }

    public static Deck toDeck(String deckString) throws Exception {
        Deck deck = new Deck();
        List<String> cards = new ArrayList<>(Arrays.asList(deckString.split(",")));
        for (String card : cards) {
            deck.addCard( Translator.toCard(card.trim()));
        }
        return deck;
    }
}
