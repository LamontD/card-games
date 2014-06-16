package lamontd.playcode.cardgames.core;

/**
 * A generic interface for a PlayingCard. This interface allows for decks,
 * shuffling, hands, in the most generic sense without making assumptions that
 * will prevent non-standard types of card games from being implemented.
 * 
 * @author Lamont
 * 
 */
public interface PlayingCard extends Comparable<PlayingCard> {

	/**
	 * 
	 * @return a CardID that is unique to this card. (There may be duplicates!)
	 */
	public String getCardId();
	
	/**
	 * Equality operator for Collection use. Don't want any spurious finds not working!
	 * @param o the object for comparison
	 * @return
	 */
	public boolean equals(Object o);
	
}
