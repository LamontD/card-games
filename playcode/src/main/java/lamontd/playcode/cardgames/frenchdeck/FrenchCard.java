/**
 * 
 */
package lamontd.playcode.cardgames.frenchdeck;

import lamontd.playcode.cardgames.core.PlayingCard;

/**
 * A {@link PlayingCard} implementation of a card that is used in a standard French deck of cards.
 * 
 * @author Lamont
 *
 */
public class FrenchCard implements PlayingCard {
	
	// The suit of this card
	private Suit suit;
	
	// The rank of this card
	private Rank rank;
	
	/**
	 * Constructor
	 * @param s the suit
	 * @param r the rank
	 */
	public FrenchCard(Suit s, Rank r) {
		suit = s;
		rank = r;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(PlayingCard o) {
		if (o instanceof FrenchCard) {
			FrenchCard fc = (FrenchCard)o;
			if (suit.equals(fc.getSuit())) {
				return rank.compareTo(fc.rank);
			} else {
				return suit.compareTo(fc.suit);
			}
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see lamontd.playcode.cardgames.core.PlayingCard#getCardId()
	 */
	public String getCardId() {
		return toString();
	}
	
	/**
	 * @return the Rank associated with this card
	 */
	public Rank getRank() {
		return rank;
	}
	
	/**
	 * @return the Suit associated with this card
	 */
	public Suit getSuit() {
		return suit;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rank).append(" of ").append(suit);
		return sb.toString();
	}

}
