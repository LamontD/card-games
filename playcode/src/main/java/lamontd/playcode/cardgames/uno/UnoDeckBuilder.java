/**
 * 
 */
package lamontd.playcode.cardgames.uno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lamontd.playcode.cardgames.core.CardDeck;
import lamontd.playcode.cardgames.core.CardDeckFactory.Builder;

/**
 * A builder of Uno decks.
 * @author Lamont
 *
 */
public class UnoDeckBuilder implements Builder {

	// The name of this deck
	private static final String DECK_NAME = "UnoDeck";
	
	/* (non-Javadoc)
	 * @see lamontd.playcode.cardgames.core.CardDeckFactory.Builder#createDeck()
	 */
	public CardDeck createDeck() {
		CardDeck deck = new CardDeck();
		
		for (Color c : new Color[]{Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW}) {
			List<Rank> rankList = new ArrayList<Rank>(Arrays.asList(Rank.values()));
			rankList.remove(Rank.ZERO);
			rankList.remove(Rank.WILD);
			rankList.remove(Rank.DRAW_FOUR);
			
			// Now an Uno deck has one zero per color...
			deck.add(new UnoCard(c, Rank.ZERO));
			
			// ...and two of the other color cards
			for (Rank r : rankList) {
				deck.add(new UnoCard(c, r));
				deck.add(new UnoCard(c, r));
			}
		}
		
		// The deck also has four Wild cards and four Draw 4 ones
		for (int i=0; i < 4; i++) {
			deck.add(new UnoCard(Color.WILD, Rank.WILD));
			deck.add(new UnoCard(Color.WILD, Rank.DRAW_FOUR));
		}
		
		return deck;
	}

	/* (non-Javadoc)
	 * @see lamontd.playcode.cardgames.core.CardDeckFactory.Builder#getName()
	 */
	public String getName() {
		return DECK_NAME;
	}

}
