import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Hand extends ArrayDeque<Card> {
	private static final long serialVersionUID = 1L;

	public void show(){
		for (Card card : this) {
			System.out.println(card);
		}
	}

	public void shuffleHand(){
		List<Card> tempCards = new ArrayList<Card>();
		tempCards.addAll(this);
		Collections.shuffle(tempCards);
		this.clear();
		this.addAll(tempCards);
	}
}
