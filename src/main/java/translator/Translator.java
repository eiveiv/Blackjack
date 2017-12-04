package translator;

import model.Card;
import model.CardSuit;
import model.CardValue;

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
}
