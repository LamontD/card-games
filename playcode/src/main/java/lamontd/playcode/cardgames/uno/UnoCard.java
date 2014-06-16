/**
 * 
 */
package lamontd.playcode.cardgames.uno;

import lamontd.playcode.cardgames.core.PlayingCard;

/**
 * Represents an Uno Card.
 * <p>
 * Uno is a very different card game, relatively speaking.
 * 
 * @author Lamont
 * 
 */
public class UnoCard implements PlayingCard {

	// The color of this card
	private Color color;

	// The rank of this card
	private Rank rank;

	/**
	 * Constructor.
	 * 
	 * @param color
	 *            the color for this card
	 * @param rank
	 *            the rank for this card
	 */
	UnoCard(Color color, Rank rank) {
		this.color = color;
		this.rank = rank;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(PlayingCard o) {
		if (o instanceof UnoCard) {
			UnoCard uc = (UnoCard)o;
			if (color.compareTo(uc.color) == 0) {
				return rank.compareTo(uc.rank);
			}
			else {
				return color.compareTo(uc.color);
			}
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lamontd.playcode.cardgames.core.PlayingCard#getCardId()
	 */
	public String getCardId() {
		return toString();
	}
	
	/**
	 * 
	 * @return the Color of this card
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return the Rank of this card
	 */
	public Rank getRank() {
		return rank;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// Wild cards are special cases!
		if (color != Color.WILD) {
			sb.append(color).append(" ");
		}
		sb.append(rank);
		
		return sb.toString();
	}

}
