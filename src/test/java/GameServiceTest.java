import model.Card;
import model.CardSuit;
import model.CardValue;
import model.Hand;
import org.junit.Assert;
import org.junit.Test;
import service.GameService;

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


}
