package lamontd.playcode.cardgames.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the functionality of a 'Hand' in a card game.
 * <p>
 * Implementors of this class will provide the definition of what constitutes a
 * 'hand' in the given game and will determine a hand's value by implementing
 * the evaluateHand method.
 * 
 * @author Lamont
 * 
 */
public abstract class AbstractHand implements Comparable<AbstractHand> {

	// The cards in the given hand
	private List<PlayingCard> cards = new ArrayList<PlayingCard>();

	// The map of HandObservers
	private Map<String, HandObserver> observers = new HashMap<String, HandObserver>();

	/**
	 * Adds the given card to this hand
	 * 
	 * @param pc
	 *            the card to add
	 */
	public void addCard(PlayingCard pc) {
		cards.add(pc);
	}

	/**
	 * Adds a HandObserver for this hand. Every time the hand changes, it will
	 * be notified.
	 * 
	 * @param ho
	 *            the HandObserver to add
	 */
	public void addObserver(HandObserver ho) {
		observers.put(ho.getObserverId(), ho);
	}

	/**
	 * Compares the hands
	 */
	public int compareTo(AbstractHand arg0) {
		return (int) (evaluateHand() - arg0.evaluateHand());
	}

	/**
	 * Does this hand contain this card - or a card that is equivalent to this
	 * one>
	 * 
	 * @param pc
	 * @return
	 */
	public boolean containsCard(PlayingCard pc) {
		return cards.contains(pc);
	}

	/**
	 * Discard (i.e. turn in) the current cards in the hand.
	 */
	public void discardHand() {
		cards.clear();
	}

	/**
	 * Provide a custom evaluation of the given hand, returning a distinct
	 * value. This value is only used depending on the game implementation.
	 * 
	 * @return a custom value of the hand
	 */
	public abstract long evaluateHand();

	/**
	 * Try to find the given {@link PlayingCard} in your hand.
	 * 
	 * @param pc
	 * @return
	 */
	public int findCard(PlayingCard pc) {
		return cards.indexOf(pc);
	}
	
	/**
	 * 
	 * @return all of the cards in the current hand
	 */
	public List<PlayingCard> getAllCards() {
		return new ArrayList<PlayingCard>(cards);
	}

	/**
	 * Gets the card at a particular index. Does not remove the card from the
	 * hand.
	 * 
	 * @param index
	 *            the lookup index
	 * @return the PlayingCard at that index
	 */
	public PlayingCard getCard(int index) {
		return cards.get(index);
	}

	/**
	 * 
	 * @return the number of cards in the current hand
	 */
	public int getNumberOfCards() {
		return cards.size();
	}

	/**
	 * 
	 * @return true if the hand is empty
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * Notify the observers of changes in the given hands. Intended to be called
	 * by updating methods or by subclasses that wish to do updates at the
	 * appropriate spots.
	 */
	protected void notifyObservers() {
		for (HandObserver obs : observers.values()) {
			obs.handChanged(this);
		}
	}

	/**
	 * Removes the card at the given index, returning it.
	 * 
	 * @param pc
	 *            the PlayingCard to remove
	 * @return the PlayingCard that was removed
	 */
	public PlayingCard removeCard(PlayingCard pc) {
		int index = cards.indexOf(pc);
		if (index < 0) {
			return null;
		}
		return cards.remove(index);
	}

	/**
	 * Removes the card at the given index, returning it.
	 * 
	 * @param index
	 *            the index of the card to remove
	 * @return the PlayingCard at that index
	 */
	public PlayingCard removeCard(int index) {
		return cards.remove(index);
	}

	/**
	 * Removes the given HandObserver from the map.
	 * 
	 * @param ho
	 *            the HandObserver to remove
	 */
	public void removeObserver(HandObserver ho) {
		if (ho != null) {
			removeObserver(ho.getObserverId());
		}
	}

	/**
	 * Removes the HandObserver with the given name from the map.
	 * 
	 * @param observerName
	 */
	public void removeObserver(String observerName) {
		observers.remove(observerName);
	}

	/**
	 * In the current hand, swap out a {@link PlayingCard} with a newer one.
	 * 
	 * @param oldCard
	 *            the old card that should be in the hand
	 * @param newCard
	 *            the newer card
	 * @return true if the original card
	 */
	public boolean replaceCard(PlayingCard oldCard, PlayingCard newCard) {
		int location = cards.indexOf(oldCard);
		if (location < 0) {
			return false;
		}
		cards.set(location, newCard);
		return true;
	}

	/**
	 * Sorts the given hand. This will sort the cards by the order that the
	 * {@link PlayingCard} implementation will determine.
	 */
	public void sort() {
		Collections.sort(cards);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (PlayingCard pc : cards) {
			sb.append("[").append(pc.toString()).append("] ");
		}
		return sb.toString();
	}

}
