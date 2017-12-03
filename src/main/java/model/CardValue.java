package model;

import org.apache.commons.lang3.math.NumberUtils;

public enum CardValue {TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private String shortLetter;

    CardValue(String shortLetter) {
        this.shortLetter = shortLetter;
    }

    public Integer getNumericValue() {
        if (NumberUtils.isNumber(this.shortLetter)) {
            return Integer.valueOf(this.shortLetter);
        } else {
            if (this == ACE) {
                return 11;
            } else {
                return 10;
            }
        }
    }

    public String getShortLetter() {
        return shortLetter;
    }
}
