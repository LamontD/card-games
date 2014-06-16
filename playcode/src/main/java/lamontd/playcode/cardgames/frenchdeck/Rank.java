/**
 * 
 */
package lamontd.playcode.cardgames.frenchdeck;

/**
 * Specification of rank for a French Deck of cards. Includes all of the
 * standard ranks (Two through Ace) as well as the Joker ranks used in
 * specialized French decks.
 * 
 * @author Lamont
 * 
 */
public enum Rank {

	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(
			10), JACK(11), QUEEN(12), KING(13), ACE(14), LITTLE_JOKER(15), BIG_JOKER(
			16);

	// The ordinal value of this Rank
	private Integer ordinalValue;

	/**
	 * Constructor.
	 * 
	 * @param value
	 *            the value of this Rank
	 */
	Rank(int value) {
		ordinalValue = value;
	}

	/**
	 * @return the ordinal value of this enum
	 */
	public Integer getOrdinalValue() {
		return ordinalValue;
	}

	/**
	 * Shortcut to determine whether this is a face card or not. For the
	 * purposes of this, Jokers are considered face cards.
	 * 
	 * @return true if this is a face card and false otherwise.
	 */
	public boolean isFaceCard() {
		boolean value = false;
		switch (this) {
		case JACK:
		case QUEEN:
		case KING:
		case ACE:
		case BIG_JOKER:
		case LITTLE_JOKER:
			value = true;
			break;
		default:
			value = false;
			break;
		}
		return value;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		switch (this) {
		case TWO:
			sb.append("Two");
			break;
		case THREE:
			sb.append("Three");
			break;
		case FOUR:
			sb.append("Four");
			break;
		case FIVE:
			sb.append("Five");
			break;
		case SIX:
			sb.append("Six");
			break;
		case SEVEN:
			sb.append("Seven");
			break;
		case EIGHT:
			sb.append("Eight");
			break;
		case NINE:
			sb.append("Nine");
			break;
		case TEN:
			sb.append("Ten");
			break;
		case JACK:
			sb.append("Jack");
			break;
		case QUEEN:
			sb.append("Queen");
			break;
		case KING:
			sb.append("King");
			break;
		case ACE:
			sb.append("Ace");
			break;
		case LITTLE_JOKER:
			sb.append("Joker (L)");
			break;
		case BIG_JOKER:
			sb.append("Joker (B)");
			break;
		}
		return sb.toString();
	}

}
