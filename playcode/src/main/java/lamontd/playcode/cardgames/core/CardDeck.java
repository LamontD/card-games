package lamontd.playcode.cardgames.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Representation of a Deck of {@link PlayingCard} objects.
 * <p>
 * This implements the functionality of a deck without any particulars regarding
 * individual playing cards. This allows for implementors to create whatever
 * deck types they like (likely utilizing a Factory pattern) without worrying
 * about the functionality of how card decks operate.
 * <p>
 * This CardDeck implements the following behavior:
 * <ul>
 * <li>Shuffling of a deck -- which both randomizes the cards and resets the deck to its pristine state</li>
 * <li>Adding/removing {@link PlayingCard} members</li>
 * <li>Dealing cards from the deck</li>
 * </ul>
 * 
 * @author Lamont
 * 
 */
public class CardDeck {
	
	// The underlying deck of cards
	private List<PlayingCard> cardDeck;
	private int deckIndex = 0;
	
	/**
	 * Creates an empty card deck.
	 */
	public CardDeck() {
		cardDeck = new ArrayList<PlayingCard>();
		deckIndex = 0;
	}
	
	public void add(PlayingCard c) {
		cardDeck.add(c);
	}
	
	public void add(Collection<? extends PlayingCard> cards) {
		cardDeck.addAll(cards);
	}
	
	public List<PlayingCard> getAllCards() {
		return new ArrayList<PlayingCard>(cardDeck);
	}
	
	/**
	 * @return the total size of the deck
	 */
	public int getSizeOfDeck() {
		return cardDeck.size();
	}

	/**
	 * @return the number of cards remaining in the deck.
	 */
	public int getNumRemainingCards() {
		return getSizeOfDeck() - deckIndex;
	}
	
	/**
	 * Deals a {@link PlayingCard} from this deck
	 * @return the next card in this deck, or null if the deck is empty
	 */
	public PlayingCard dealCard() {
		if (deckIndex >= cardDeck.size()) {
			return null;
		}
		return cardDeck.get(deckIndex++);
	}
	
	/**
	 * Determines if this CardDeck is empty. The CardDeck is empty if the next dealCard operation will return null.
	 * @return true if the deck is empty and false otherwise
	 */
	public boolean isEmpty() {
		return deckIndex >= cardDeck.size();
	}
	
	/**
	 * Restores the deck to its full state. This is functionally equivalent to restoring the index.
	 */
	public void restoreDeck() {
		deckIndex = 0;
	}
	
	/**
	 * Shuffles and resets the current deck.
	 */
	public void shuffleDeck() {
		Collections.shuffle(cardDeck);
		deckIndex = 0;
	}

}
