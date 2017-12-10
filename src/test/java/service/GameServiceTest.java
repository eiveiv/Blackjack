package service;

import model.*;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

public class GameServiceTest {

    private GameService gameService = new GameService();

    @Test
    public void playerWinsWithBothBlackjack() {
        Hand playerHand = new Hand();
        playerHand.addCard( new Card(CardSuit.CLUBS, CardValue.ACE));
        playerHand.addCard( new Card(CardSuit.SPADES, CardValue.KING));
        Hand dealerHand = new Hand();
        dealerHand.addCard( new Card(CardSuit.HEARTS, CardValue.ACE));
        dealerHand.addCard( new Card(CardSuit.DIAMONDS, CardValue.QUEEN));
        Assert.assertTrue(gameService.playerWon(playerHand, dealerHand));
    }

    @Test
    public void dealerWinsWithBothDoubleAces() {
        Hand playerHand = new Hand();
        playerHand.addCard( new Card(CardSuit.CLUBS, CardValue.ACE));
        playerHand.addCard( new Card(CardSuit.SPADES, CardValue.ACE));
        Hand dealerHand = new Hand();
        dealerHand.addCard( new Card(CardSuit.HEARTS, CardValue.ACE));
        dealerHand.addCard( new Card(CardSuit.DIAMONDS, CardValue.ACE));
        Assert.assertFalse(gameService.playerWon(playerHand, dealerHand));
    }

    @Test
    public void dealerLosesDoubleAces() {
        Hand playerHand = new Hand();
        playerHand.addCard( new Card(CardSuit.CLUBS, CardValue.FIVE));
        playerHand.addCard( new Card(CardSuit.SPADES, CardValue.TEN));
        Hand dealerHand = new Hand();
        dealerHand.addCard( new Card(CardSuit.HEARTS, CardValue.ACE));
        dealerHand.addCard( new Card(CardSuit.DIAMONDS, CardValue.ACE));
        Assert.assertTrue(gameService.playerWon(playerHand, dealerHand));
    }

    @Test
    public void winnerFromStartWithBlackjack() {
        Hand playerHand = new Hand();
        playerHand.addCard( new Card(CardSuit.CLUBS, CardValue.ACE));
        playerHand.addCard( new Card(CardSuit.SPADES, CardValue.TEN));
        Hand dealerHand = new Hand();
        dealerHand.addCard( new Card(CardSuit.HEARTS, CardValue.ACE));
        dealerHand.addCard( new Card(CardSuit.DIAMONDS, CardValue.FIVE));
        Assert.assertTrue(gameService.winnerFromStart(playerHand, dealerHand));
    }

    @Test
    public void winnerFromStartDealerDoubleAces() {
        Hand playerHand = new Hand();
        playerHand.addCard( new Card(CardSuit.CLUBS, CardValue.EIGHT));
        playerHand.addCard( new Card(CardSuit.SPADES, CardValue.QUEEN));
        Hand dealerHand = new Hand();
        dealerHand.addCard( new Card(CardSuit.HEARTS, CardValue.ACE));
        dealerHand.addCard( new Card(CardSuit.DIAMONDS, CardValue.ACE));
        Assert.assertTrue(gameService.winnerFromStart(playerHand, dealerHand));
    }

    @Test
    public void winnerFromStartBothDoubleAces() {
        Hand playerHand = new Hand();
        playerHand.addCard( new Card(CardSuit.CLUBS, CardValue.ACE));
        playerHand.addCard( new Card(CardSuit.SPADES, CardValue.ACE));
        Hand dealerHand = new Hand();
        dealerHand.addCard( new Card(CardSuit.HEARTS, CardValue.ACE));
        dealerHand.addCard( new Card(CardSuit.DIAMONDS, CardValue.ACE));
        Assert.assertTrue(gameService.winnerFromStart(playerHand, dealerHand));
    }

    @Test
    public void noWinnerFromStart() {
        Hand playerHand = new Hand();
        playerHand.addCard( new Card(CardSuit.CLUBS, CardValue.SEVEN));
        playerHand.addCard( new Card(CardSuit.SPADES, CardValue.TWO));
        Hand dealerHand = new Hand();
        dealerHand.addCard( new Card(CardSuit.HEARTS, CardValue.KING));
        dealerHand.addCard( new Card(CardSuit.DIAMONDS, CardValue.EIGHT));
        Assert.assertFalse(gameService.winnerFromStart(playerHand, dealerHand));
    }

    @Test
    public void createDeckFromFile() throws Exception {
        URL resource = getClass().getClassLoader().getResource("cardDeck.csv");
        Deck deckFromFile = gameService.createDeckFromFile(resource.getPath());
        Assert.assertTrue(deckFromFile.getCards().size() == 5);
        Assert.assertTrue(deckFromFile.getCards().get(0).getSuit() == CardSuit.CLUBS);
        Assert.assertTrue(deckFromFile.getCards().get(0).getValue() == CardValue.ACE);
    }

    @Test(expected = Exception.class)
    public void createDeckFromFileFail() throws Exception {
        gameService.createDeckFromFile("path/does/not/exist");
    }

    @Test(expected = Exception.class)
    public void createDeckFromFileInvalid() throws Exception {
        URL resource = getClass().getClassLoader().getResource("invalidCardDeck.csv");
        gameService.createDeckFromFile(resource.getPath());
    }

    @Test
    public void isValidDeck() throws Exception {
        Deck deck = new Deck();
        deck.addCard( new Card(CardSuit.CLUBS, CardValue.ACE));
        deck.addCard( new Card(CardSuit.CLUBS, CardValue.TWO));
        deck.addCard( new Card(CardSuit.CLUBS, CardValue.ACE));
        deck.addCard( new Card(CardSuit.CLUBS, CardValue.TWO));
        Assert.assertTrue(gameService.isValidDeck(deck));
    }

    @Test
    public void isInvalidDeck() throws Exception {
        Deck deck = new Deck();
        deck.addCard( new Card(CardSuit.SPADES, CardValue.FIVE));
        deck.addCard( new Card(CardSuit.DIAMONDS, CardValue.TWO));
        deck.addCard( new Card(CardSuit.CLUBS, CardValue.ACE));
        deck.addCard( new Card(CardSuit.HEARTS, CardValue.SEVEN));
        Assert.assertFalse(gameService.isValidDeck(deck));
    }

    @Test
    public void isValidDeck2() throws Exception {
        Deck deck = new Deck();
        deck.addCard( new Card(CardSuit.SPADES, CardValue.TEN));
        deck.addCard( new Card(CardSuit.DIAMONDS, CardValue.TWO));
        deck.addCard( new Card(CardSuit.CLUBS, CardValue.FIVE));
        deck.addCard( new Card(CardSuit.HEARTS, CardValue.SEVEN));
        deck.addCard( new Card(CardSuit.HEARTS, CardValue.THREE));
        deck.addCard( new Card(CardSuit.DIAMONDS, CardValue.SEVEN));
        deck.addCard( new Card(CardSuit.HEARTS, CardValue.EIGHT));
        deck.addCard( new Card(CardSuit.SPADES, CardValue.SEVEN));
        deck.addCard( new Card(CardSuit.HEARTS, CardValue.TEN));
        Assert.assertTrue(gameService.isValidDeck(deck));
    }

}
