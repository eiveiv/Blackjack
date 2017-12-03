package model;

public enum CardSuit { CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");

    private String shortLetter;

    CardSuit(String shortLetter) {
        this.shortLetter = shortLetter;
    }

    public String getShortLetter() {
        return this.shortLetter;
    }
}
