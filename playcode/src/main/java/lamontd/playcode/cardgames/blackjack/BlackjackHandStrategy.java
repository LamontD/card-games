package lamontd.playcode.cardgames.blackjack;

import java.util.Collections;
import java.util.List;

import lamontd.playcode.cardgames.frenchdeck.Rank;

/**
 * Class that manages the strategy for Blackjack. Basically, it's a state machine.
 * @author Lamont
 *
 */
public class BlackjackHandStrategy {

	public enum Move {
		SHOULD_HIT, SHOULD_DOUBLE, SHOULD_STAND, SHOULD_SPLIT
	};

	/**
	 * Two card strategies
	 * 
	 * @param first
	 *            The rank of the first card
	 * @param second
	 *            The rank of the second card
	 * @param dealer
	 *            The rank of the dealer's up card
	 * @return The next move we should make
	 */
	protected static Move aceStrategies(Rank first, Rank second, Rank dealer) {
		Move myMove = null;
		if (first == Rank.ACE) {
			switch (second) {
			case TWO:
			case THREE:
				if ((dealer == Rank.FIVE) || (dealer == Rank.SIX)) {
					myMove = Move.SHOULD_DOUBLE;
				} else {
					myMove = Move.SHOULD_HIT;
				}
				break;
			case FOUR:
			case FIVE:
				if ((dealer == Rank.FOUR) || (dealer == Rank.FIVE)
						|| (dealer == Rank.SIX)) {
					myMove = Move.SHOULD_DOUBLE;
				} else {
					myMove = Move.SHOULD_HIT;
				}
				break;
			case SIX:
				if ((dealer == Rank.THREE) || (dealer == Rank.FOUR)
						|| (dealer == Rank.FIVE) || (dealer == Rank.SIX)) {
					myMove = Move.SHOULD_DOUBLE;
				} else {
					myMove = Move.SHOULD_HIT;
				}
				break;
			case SEVEN:
				if (dealer.compareTo(Rank.NINE) >= 0) {
					myMove = Move.SHOULD_HIT;
				} else {
					myMove = Move.SHOULD_STAND;
				}

				break;
			default:
				myMove = Move.SHOULD_STAND;
				break;
			}
		}
		return myMove;
	}

	protected static Move pairStrategies(Rank first, Rank second, Rank dealer) {
		Move myMove = null;
		switch (first) {
		case TWO:
		case THREE:
		case SEVEN:
			myMove = dealer.compareTo(Rank.EIGHT) >= 0 ? Move.SHOULD_HIT
					: Move.SHOULD_SPLIT;
			break;

		case FOUR:
			if ((dealer == Rank.FIVE) || (dealer == Rank.FOUR)) {
				myMove = Move.SHOULD_SPLIT;
			} else {
				myMove = Move.SHOULD_HIT;
			}
			break;
		case SIX:
			myMove = dealer.compareTo(Rank.SEVEN) >= 0 ? Move.SHOULD_HIT
					: Move.SHOULD_SPLIT;
			break;
		case EIGHT:
			myMove = Move.SHOULD_SPLIT;
			break;
		case NINE:
			if ((dealer == Rank.SEVEN) || dealer.isFaceCard()) {
				myMove = Move.SHOULD_STAND;
			} else {
				myMove = Move.SHOULD_SPLIT;
			}
			break;
		default:
			myMove = Move.SHOULD_STAND;
			break;
		}
		return myMove;
	}

	protected static Move countStrategies(long handValue, Rank dealer) {
		Move myMove = null;
		if (handValue < 9) {
			myMove = Move.SHOULD_HIT;
		} else if (handValue == 9) {
			if ((dealer == Rank.THREE) || (dealer == Rank.FOUR)
					|| (dealer == Rank.FIVE) || (dealer == Rank.SIX)) {
				myMove = Move.SHOULD_DOUBLE;
			} else {
				myMove = Move.SHOULD_HIT;
			}
		} else if (handValue == 10) {
			myMove = dealer.isFaceCard() ? Move.SHOULD_HIT : Move.SHOULD_DOUBLE;
		} else if (handValue == 11) {
			myMove = dealer == Rank.ACE ? Move.SHOULD_HIT : Move.SHOULD_DOUBLE;
		} else if (handValue == 12) {
			if ((dealer == Rank.FOUR) || (dealer == Rank.FIVE)
					|| (dealer == Rank.SIX)) {
				myMove = Move.SHOULD_STAND;
			} else {
				myMove = Move.SHOULD_HIT;
			}
		} else if ((handValue >= 13) && (handValue <= 16)) {
			if ((dealer == Rank.TWO) || (dealer == Rank.THREE)
					|| (dealer == Rank.FOUR) || (dealer == Rank.FIVE)
					|| (dealer == Rank.SIX)) {
				myMove = Move.SHOULD_STAND;
			} else {
				myMove = Move.SHOULD_HIT;
			}
		} else {
			myMove = Move.SHOULD_STAND;
		}
		return myMove;
	}

	/**
	 * Use strategy to determine the next move.
	 * 
	 * @param hand
	 *            The ranks of the cards in the hand
	 * @param dealerUpCard
	 *            the rank of the dealer's up card
	 * @param handValue
	 *            the current value of this hand
	 * @return the next Move that the person should make
	 */
	public static Move decideStrategy(List<Rank> hand, Rank dealerUpCard,
			long handValue) {
		Move myMove = null;

		Collections.sort(hand);
		Collections.reverse(hand);

		if (dealerUpCard == null) {
			return handValue < 17 ? Move.SHOULD_HIT : Move.SHOULD_STAND;
		}

		// Two card scenarios
		if (hand.size() == 2) {
			Rank first = hand.get(0);
			Rank second = hand.get(1);
			if (first == Rank.ACE) {
				myMove = aceStrategies(first, second, dealerUpCard);
			} else if (first == second) {
				myMove = pairStrategies(first, second, dealerUpCard);
			}
		}
		if (myMove == null) {
			myMove = countStrategies(handValue, dealerUpCard);
		}

		return myMove;
	}
}
