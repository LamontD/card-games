/**
 * 
 */
package lamontd.playcode.cardgames.frenchdeck;

import lamontd.playcode.cardgames.core.CardDeck;
import lamontd.playcode.cardgames.core.CardDeckFactory;

/**
 * A builder of Standard French Card decks
 * 
 * @author Lamont
 * 
 */
public class StandardDeckBuilder implements CardDeckFactory.Builder {

	// Does this builder include Jokers?
	private boolean withJokers;

	/**
	 * Constructor that determines whether this builder should use jokers or
	 * not.
	 * 
	 * @param includeJokers
	 *            should it include jokers
	 */
	public StandardDeckBuilder(boolean includeJokers) {
		withJokers = includeJokers;
	}

	/* (non-Javadoc)
	 * @see lamontd.playcode.cardgames.core.CardDeckFactory.Builder#createDeck()
	 */
	public CardDeck createDeck() {
		CardDeck theDeck = new CardDeck();
		
		for(Suit s: Suit.values()) {
			theDeck.add(new FrenchCard(s, Rank.TWO));
			theDeck.add(new FrenchCard(s, Rank.THREE));
			theDeck.add(new FrenchCard(s, Rank.FOUR));
			theDeck.add(new FrenchCard(s, Rank.FIVE));
			theDeck.add(new FrenchCard(s, Rank.SIX));
			theDeck.add(new FrenchCard(s, Rank.SEVEN));
			theDeck.add(new FrenchCard(s, Rank.EIGHT));
			theDeck.add(new FrenchCard(s, Rank.NINE));
			theDeck.add(new FrenchCard(s, Rank.TEN));
			theDeck.add(new FrenchCard(s, Rank.JACK));
			theDeck.add(new FrenchCard(s, Rank.QUEEN));
			theDeck.add(new FrenchCard(s, Rank.KING));
			theDeck.add(new FrenchCard(s, Rank.ACE));
		}
		
		if (withJokers) {
			theDeck.add(new FrenchCard(Suit.SPADES, Rank.BIG_JOKER));
			theDeck.add(new FrenchCard(Suit.SPADES, Rank.LITTLE_JOKER));
		}

		return theDeck;
	}

	/* (non-Javadoc)
	 * @see lamontd.playcode.cardgames.core.CardDeckFactory.Builder#getName()
	 */
	public String getName() {
		return withJokers ? "FrenchDeck with Jokers" : "Standard FrenchDeck";
	}
	
	
}
