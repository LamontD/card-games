package lamontd.playcode.cardgames.frenchdeck;


/**
 * An enum that represents the Suit in a standard French Deck of cards.
 * 
 * @author Lamont
 * 
 */
public enum Suit {
	
	CLUBS(1),
	DIAMONDS(2),
	HEARTS(3),
	SPADES(4);
	
	// The ordinal value for this Suit
	private Integer ordinalValue;
	
	/**
	 * Constructor.
	 * @param value the value for this suit
	 */
	Suit(int value) {
		ordinalValue = value;
	}
	
	/**
	 * @return the ordinal value of this Suit
	 */
	public Integer getOrdinalValue() {
		return ordinalValue;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		switch(this) {
		case CLUBS:
			sb.append("Clubs");
			break;
		case DIAMONDS:
			sb.append("Diamonds");
			break;
		case HEARTS:
			sb.append("Hearts");
			break;
		case SPADES:
			sb.append("Spades");
			break;
		}
		return sb.toString();
	}

}
