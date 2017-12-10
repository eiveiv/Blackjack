package model;

public enum CardSuit { CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");

    private String shortName;

    CardSuit(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }
}
