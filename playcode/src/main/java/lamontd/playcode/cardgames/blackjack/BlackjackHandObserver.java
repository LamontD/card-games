/**
 * 
 */
package lamontd.playcode.cardgames.blackjack;

import java.util.ArrayList;
import java.util.List;

import lamontd.playcode.cardgames.blackjack.BlackjackHandStrategy.Move;
import lamontd.playcode.cardgames.core.AbstractHand;
import lamontd.playcode.cardgames.core.HandObserver;
import lamontd.playcode.cardgames.core.PlayingCard;
import lamontd.playcode.cardgames.frenchdeck.FrenchCard;
import lamontd.playcode.cardgames.frenchdeck.Rank;

/**
 * A {@link HandObserver} implementation for Blackjack. Not only will this watch
 * the cards, but it will help determine the state of the hand and what advice
 * should be taken.
 * 
 * @author Lamont
 * 
 */
public class BlackjackHandObserver implements HandObserver {

	public enum HandState {
		OKAY, BLACKJACK, BUSTED
	}

	// The Up Card for the dealer
	private Rank dealerUpCard;
	private HandState currentState;
	private BlackjackHandStrategy.Move nextMove = null;
	private String playerName;

	public BlackjackHandObserver(String playerName) {
		this.playerName = playerName;
		this.currentState = HandState.OKAY;
		this.nextMove = Move.SHOULD_HIT;

	}

	public BlackjackHandObserver(String playerName, FrenchCard dealerCard) {
		this.playerName = playerName;
		this.dealerUpCard = dealerCard.getRank();
		this.currentState = HandState.OKAY;
		this.nextMove = Move.SHOULD_HIT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * lamontd.playcode.cardgames.core.HandObserver#handChanged(lamontd.playcode
	 * .cardgames.core.AbstractHand)
	 */
	public void handChanged(AbstractHand newHand) {
		long currentHandValue = newHand.evaluateHand();
		if (currentHandValue == 21) {
			currentState = HandState.BLACKJACK;
			nextMove = Move.SHOULD_STAND;
		} else if (currentHandValue > 21) {
			currentState = HandState.BUSTED;
			nextMove = Move.SHOULD_STAND;
		} else {
			// Setup the call to determine what the next move should be
			List<Rank> handRanks = new ArrayList<Rank>();
			for (PlayingCard pc : newHand.getAllCards()) {
				handRanks.add(((FrenchCard) pc).getRank());
			}
			nextMove = BlackjackHandStrategy.decideStrategy(handRanks,
					dealerUpCard, currentHandValue);
			// System.out.println("For " + playerName
			// + " I think my next move should be " + nextMove);
		}
	}

	/**
	 * Retrieves the current state of the hand.
	 * 
	 * @return the current state of the hand
	 */
	public HandState getHandState() {
		return currentState;
	}

	/**
	 * Retrieves what the next move should be, based on the strategy engine.
	 * 
	 * @return the next strategic move
	 */
	public BlackjackHandStrategy.Move getNextMove() {
		return nextMove;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lamontd.playcode.cardgames.core.HandObserver#getObserverId()
	 */
	public String getObserverId() {
		return playerName;
	}

}
