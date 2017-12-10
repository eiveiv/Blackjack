package model;

import org.apache.commons.lang3.StringUtils;

public enum CardValue {TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private String shortName;

    CardValue(String shortName) {
        this.shortName = shortName;
    }

    public int getNumericValue() {
        if (StringUtils.isNumeric(this.shortName)) {
            return Integer.valueOf(this.shortName);
        } else {
            if (this == ACE) {
                return 11;
            } else {
                return 10;
            }
        }
    }

    public String getShortName() {
        return shortName;
    }

}
