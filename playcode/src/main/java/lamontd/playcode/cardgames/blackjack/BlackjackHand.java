/**
 * 
 */
package lamontd.playcode.cardgames.blackjack;

import lamontd.playcode.cardgames.core.AbstractHand;
import lamontd.playcode.cardgames.core.PlayingCard;
import lamontd.playcode.cardgames.frenchdeck.FrenchCard;
import lamontd.playcode.cardgames.frenchdeck.Rank;

/**
 * A {@link AbstractHand} implementation that operates for the game of
 * Blackjack.
 * 
 * @author Lamont
 * 
 */
public class BlackjackHand extends AbstractHand {

	// The Blackjack value
	public static final Integer TWENTY_ONE = 21;

	/*
	 * (non-Javadoc)
	 * 
	 * @see lamontd.playcode.cardgames.core.AbstractHand#evaluateHand()
	 */
	@Override
	public long evaluateHand() {
		long handValue = 0;

		// Count the card values. Assume that all Aces are worth 1 and all face
		// cards are worth 10.
		int numAces = 0;
		for (PlayingCard pc : getAllCards()) {
			FrenchCard fc = (FrenchCard) pc;
			if (fc.getRank().isFaceCard()) {
				if (Rank.ACE == fc.getRank()) {
					numAces++;
					handValue += 1;
				} else {
					handValue += 10;
				}
			} else {
				handValue += fc.getRank().getOrdinalValue();
			}
		}

		// Now, if we have aces...we might want to count them as ten as long as
		// we don't end up busted!
		while (numAces > 0) {
			if (handValue + 10 <= TWENTY_ONE) {
				handValue += 10;
			}
			numAces--;
		}

		return handValue;
	}

	/**
	 * Determines whether this hand has any Aces
	 * 
	 * @return true if this hand has an Ace and false otherwise
	 */
	public boolean hasAces() {
		for (PlayingCard pc : this.getAllCards()) {
			FrenchCard fc = (FrenchCard) pc;
			if (fc.getRank() == Rank.ACE) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasBlackjack() {
		return evaluateHand() == TWENTY_ONE;
	}
	
	public boolean isBusted() {
		return evaluateHand() > TWENTY_ONE;
	}
	
	/**
	 * Update the given hand. Notifies all observers of what has happened.
	 */
	public void update() {
		this.notifyObservers();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Value: ").append(evaluateHand()).append(" - ").append(super.toString());
		return sb.toString();
	}

}
