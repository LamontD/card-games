/**
 * 
 */
package lamontd.playcode.cardgames.uno;

/**
 * The Rank of individual cards.
 * 
 * @author Lamont
 * 
 */
public enum Rank {
	ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_TWO, REVERSE, SKIP, WILD, DRAW_FOUR;

	/**
	 * Determines if this is a number card or not.
	 * 
	 * @return true if this is a number card and false otherwise
	 */
	public boolean isNumberCard() {
		boolean numCard = false;
		switch (this) {
		case ZERO:
		case ONE:
		case TWO:
		case THREE:
		case FOUR:
		case FIVE:
		case SIX:
		case SEVEN:
		case EIGHT:
		case NINE:
			numCard = true;
			break;
		default:
			numCard = false;
			break;
		}
		return numCard;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		switch (this) {
		case ZERO:
			sb.append("0");
			break;
		case ONE:
			sb.append("1");
			break;
		case TWO:
			sb.append("2");
			break;
		case THREE:
			sb.append("3");
			break;
		case FOUR:
			sb.append("4");
			break;
		case FIVE:
			sb.append("5");
			break;
		case SIX:
			sb.append("6");
			break;
		case SEVEN:
			sb.append("7");
			break;
		case EIGHT:
			sb.append("8");
			break;
		case NINE:
			sb.append("9");
			break;
		case DRAW_TWO:
			sb.append("Draw Two (2)");
			break;
		case REVERSE:
			sb.append("Reverse");
			break;
		case SKIP:
			sb.append("Skip");
			break;
		case WILD:
			sb.append("");
			break;
		case DRAW_FOUR:
			sb.append("Draw Four (4)");
			break;
		}
		return sb.toString();
	}
}
