/**
 * 
 */
package lamontd.playcode.cardgames;

import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * A set of utility methods for text-based games.
 * 
 * @author Lamont
 * 
 */
public class TextGameUtil {
	private static final Scanner scan = new Scanner(System.in);

	/**
	 * Method that takes an input map and returns one of the options.
	 * 
	 * @param optionMap
	 *            a Map of the options and the selection character
	 * @return the character representing one of the given options
	 */
	public static Character selectValidOption(Map<Character, String> optionMap) {
		Character option = null;
		while (option == null || !optionMap.containsKey(option)) {
			System.out.println();
			System.out.println("Your options are as follows:");
			for (Entry<Character, String> entry : optionMap.entrySet()) {
				System.out.print("\t");
				System.out.print(entry.getKey());
				System.out.print("\t");
				System.out.println(entry.getValue());
			}
			System.out.print("What would you like to do -->");
			String str = scan.nextLine().toUpperCase();
			if (!str.isEmpty()) {
				option = str.charAt(0);
			}
		}
		return option;
	}

}
