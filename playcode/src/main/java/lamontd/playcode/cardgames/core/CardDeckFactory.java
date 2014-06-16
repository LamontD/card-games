/**
 * 
 */
package lamontd.playcode.cardgames.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Factory pattern that can build {@link CardDeck} implementations for
 * particular games.
 * <p>
 * To build a card deck, a subclass should:
 * <ol>
 * <li>Implement the CardDeckFactory.Builder interface</li>
 * <li>Register this with the CardDeckFactory instance</li>
 * </ol>
 * 
 * @author Lamont
 * 
 */
public class CardDeckFactory {

	/**
	 * An interface that creates {@link CardDeck} instances.
	 * 
	 * @author Lamont
	 * 
	 */
	public interface Builder {

		/**
		 * @return a CardDeck
		 */
		public CardDeck createDeck();

		/**
		 * @return the unique name of this Builder
		 */
		public String getName();
	}

	// The single instance
	private static CardDeckFactory Instance = null;

	// The map of builders
	private Map<String, Builder> builderMap;

	/**
	 * Private constructor.
	 */
	private CardDeckFactory() {
		builderMap = new HashMap<String, Builder>();
	}

	/**
	 * Adds a Builder to this Factory
	 * 
	 * @param b
	 */
	public void addBuilder(Builder b) {
		builderMap.put(b.getName(), b);
	}

	/**
	 * Retrieves a Builder with the given name, if it is registered.
	 * 
	 * @param builderName
	 *            the name of the builder to retrieve
	 * @return the build with this name if it exists, or null otherwise
	 */
	public Builder getBuilder(String builderName) {
		if (builderMap.containsKey(builderName)) {
			return builderMap.get(builderName);
		}

		return null;
	}

	/**
	 * @return the name of all of the builders that are registered
	 */
	public List<String> getBuilderNames() {
		return new ArrayList<String>(builderMap.keySet());
	}

	/**
	 * @return the singular CardDeckFactory instance
	 */
	public static CardDeckFactory getInstance() {
		if (Instance == null) {
			Instance = new CardDeckFactory();
		}
		return Instance;
	}

}
