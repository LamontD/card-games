package lamontd.playcode.cardgames.blackjack;

import java.util.HashMap;
import java.util.Map;

import lamontd.playcode.cardgames.TextGameUtil;
import lamontd.playcode.cardgames.blackjack.BlackjackHandStrategy.Move;
import lamontd.playcode.cardgames.core.CardDeck;
import lamontd.playcode.cardgames.core.CardDeckFactory;
import lamontd.playcode.cardgames.core.PlayingCard;
import lamontd.playcode.cardgames.frenchdeck.FrenchCard;
import lamontd.playcode.cardgames.frenchdeck.StandardDeckBuilder;

public class BasicBlackjackDemo {

	private CardDeck deck;
	

	public BasicBlackjackDemo() {

	}

	public void setDeck(CardDeck deck) {
		this.deck = deck;
	}

	public boolean gameSetup() {
		Map<Character, String> optMap = new HashMap<Character, String>();
		optMap.put('N', "New Game");
		optMap.put('Q', "Quit Game");

		Character option = TextGameUtil.selectValidOption(optMap);
		return (option == 'N');
	}

	public void showIntro() {
		System.out.println(" =============");
		System.out.println("|  BLACKJACK  |");
		System.out.println(" =============");
		System.out.println("Welcome to the BlackJack demo!");
	}

	public void playGame() {
		System.out.println("Dealing cards...");

		// Setup the dealer
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackHandObserver dealerO = new BlackjackHandObserver("Dealer");
		dealerHand.addObserver(dealerO);
		dealerHand.addCard(deck.dealCard());
		FrenchCard dealerUpCard = (FrenchCard) deck.dealCard();
		dealerHand.addCard(dealerUpCard);

		// Send two cards to each player
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackHandObserver playerO = new BlackjackHandObserver("Player",
				dealerUpCard);
		playerHand.addObserver(playerO);
		playerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());

		// Start the evaluation!
		dealerHand.update();
//		System.out.println("The dealer is starting out "
//				+ dealerO.getHandState() + " and he should "
//				+ dealerO.getNextMove());
		while (dealerO.getNextMove() == Move.SHOULD_HIT) {
			dealerHand.addCard(deck.dealCard());
			dealerHand.update();
		}

		// Now that the dealer is done...
		playerHand.update();
		System.out.println("The player is starting out "
				+ playerO.getHandState());
		Map<Character, String> playerOptMap = new HashMap<Character, String>();
		playerOptMap.put('H', "Hit");
		playerOptMap.put('D', "Double Down");
		playerOptMap.put('S', "Stand");
		playerOptMap.put('P', "Split");

		boolean keepMoving = true;
		while (keepMoving) {
			System.out.println("Your current hand is " + playerHand.toString());
			System.out.println("The dealer is showing ["+dealerUpCard+"]");
			System.out.print("Your current score is ");
			System.out.println(playerHand.evaluateHand());
			System.out.println("The book says you should "+playerO.getNextMove());
			Character opt = TextGameUtil.selectValidOption(playerOptMap);
			PlayingCard dealtCard = null;
			switch (opt) {
			case 'H':
				System.out.println("Hitting...");
				dealtCard = deck.dealCard();
				System.out.println("Hit card is " + dealtCard);
				playerHand.addCard(dealtCard);
				break;
			case 'D':
				System.out.println("Doubling down on this card...");
				dealtCard = deck.dealCard();
				System.out.println("Next card is "+ dealtCard);
				playerHand.addCard(dealtCard);
				keepMoving = false;
				break;
			case 'S':
				System.out.println("Standing put");
				keepMoving = false;
				break;
			case 'P':
				System.out.println("Sorry; split is not implemented");
				break;
			}
			
			playerHand.update();
			switch(playerO.getHandState()) {
			case BUSTED:
				System.out.println("BUSTED!!!");
				keepMoving = false;
				break;
			case BLACKJACK:
				System.out.println("BLACKJACK!!!");
				keepMoving = false;
				break;
			case OKAY:
				// TODO: Implement a large hand size
				break;
			}
		}
		
		System.out.println("The game is over!");
		System.out.println("-----------------");
		System.out.print("Dealer --> ");
		System.out.println(dealerHand.toString());
		System.out.print("Player --> ");
		System.out.println(playerHand.toString());
		
		// So...who won?
		if (dealerHand.hasBlackjack()) {
			if (playerHand.hasBlackjack()) {
				System.out.println("It's a tie! Both players have BLACKJACK");
			} else {
				System.out.println("The dealer wins with BLACKJACK");
			}
		} else if (dealerHand.isBusted()) {
			if (playerHand.isBusted()) {
				System.out.println("It's a push -- both players are BUSTED");
			} else {
				System.out.println("Player wins due to dealer busting");
			}
		} else if (playerHand.hasBlackjack()) {
			System.out.println("Player wins with BLACKJACK");
		} else if (playerHand.isBusted()) {
			System.out.println("Dealer wins; player busts out");
		} else if (dealerHand.evaluateHand() == playerHand.evaluateHand()){
			System.out.println("It's a push");
		} else if (dealerHand.evaluateHand() > playerHand.evaluateHand()) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("Player wins!");
		}
	}

	public static void main(String[] args) {
		// First, initialize the CardDeckFactory
		CardDeckFactory deckFactory = CardDeckFactory.getInstance();
		deckFactory.addBuilder(new StandardDeckBuilder(false));

		// Next, get a Deck from our builder!
		CardDeckFactory.Builder myBuilder = deckFactory.getBuilder(deckFactory
				.getBuilderNames().get(0));
		CardDeck theDeck = myBuilder.createDeck();
		theDeck.add(myBuilder.createDeck().getAllCards());
		theDeck.add(myBuilder.createDeck().getAllCards());
		theDeck.shuffleDeck();
		BasicBlackjackDemo demo = new BasicBlackjackDemo();
		demo.setDeck(theDeck);
		demo.showIntro();
		
		boolean shouldContinue = true;

		while (shouldContinue) {
			shouldContinue = demo.gameSetup();
			if (shouldContinue) {
				demo.playGame();
			}
		}
		
		System.out.println("Thanks!!");

	}

}
