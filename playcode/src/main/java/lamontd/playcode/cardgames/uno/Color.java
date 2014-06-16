package lamontd.playcode.cardgames.uno;

/**
 * The colors of cards during an Uno game.
 * @author Lamont
 *
 */
public enum Color {
	BLUE,
	GREEN,
	RED,
	YELLOW,
	WILD;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		switch(this) {
		case BLUE:
			sb.append("Blue");
			break;
		case GREEN:
			sb.append("Green");
			break;
		case RED:
			sb.append("Red");
			break;
		case YELLOW:
			sb.append("Yellow");
			break;
		case WILD:
			sb.append("Wild");
			break;
		}
		return sb.toString();
	}

}
