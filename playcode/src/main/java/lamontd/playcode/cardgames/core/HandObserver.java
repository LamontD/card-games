package lamontd.playcode.cardgames.core;

/**
 * An interface to implement the Observer pattern for {@link AbstractHand} objects.
 * @author Lamont
 *
 */
public interface HandObserver {

	/**
	 * Called when a hand that this method is observing has changed.
	 * @param newHand The new hand
	 */
	public void handChanged(AbstractHand newHand);
	
	/**
	 * Gets the ID for a given observer
	 * @return
	 */
	public String getObserverId();
}
