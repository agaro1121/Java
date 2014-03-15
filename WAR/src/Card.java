public class Card implements Comparable<Card>{
	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit.toString();
	}

	public int getRank() {
		return rank.value();
	}

	public void show(){
		System.out.println(rank + " of " + suit + "s");
	}

	@Override
	public String toString() {
		return rank + " of " + suit + "s";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Card))
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		return true;
	}

	@Override
	public int compareTo(Card c) {
		if(this.rank.value() < c.rank.value()) return -1;
		if(this.rank.value() > c.rank.value()) return 1;
		return 0;
	}
}
