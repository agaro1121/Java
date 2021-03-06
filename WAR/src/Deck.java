import java.util.ArrayList;
import java.util.Collections;


public class Deck extends ArrayList<Card>{
	private static final long serialVersionUID = 1L;

	public Deck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				add(new Card(suit, rank));
			}
		}
	}

	public Deck shuffleDeck(){
		Collections.shuffle(this);
		return this;
	}

	public void show(){
		for (Card card : this) {
			System.out.println(card);
		}
	}
}
