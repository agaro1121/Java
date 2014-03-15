import java.util.ArrayDeque;


public class War {
	private static Hand hand1 = new Hand();
	private static Hand hand2 = new Hand();
	private static Deck deck = new Deck();
	private static ArrayDeque<Card> tempCardHolder = new ArrayDeque<Card>();
	private static boolean continueGame = true;
	private static int winningPlayer;

	public static void main(String[] args){
		deck.shuffleDeck();
		distributeDeck();

		//		GAME LOOP
		do{
			System.out.println(hand1.element() + " vs " + hand2.element());
			System.out.println("Hand_1:"+hand1.size()+" Hand_2:"+hand2.size()+"\n");//Show Current Stats

			if( hand1.element().compareTo(hand2.element()) == -1 ){ //player 2 wins this round
				hand2.add(hand1.poll());
				hand2.add(hand2.poll());
			}else if( hand1.element().compareTo(hand2.element()) == 1 ){// player 1 wins this round
				hand1.add(hand2.poll());
				hand1.add(hand1.poll());
			}else{ //players are even. TIME TO GO TO WAR !
				winningPlayer = isPlayerAbleToGoToWar();//check if both players are able to go to war first
				if(winningPlayer == 0){
					System.out.println("I DE CLARE WAR!!!");
					goToWar();
				}else{
					continueGame = false;
					System.out.println("Player #"+winningPlayer+" is the Winner! cuz he can start a war");
				}
			}

			winningPlayer = checkEmptyHand();
			if(winningPlayer != 0){ //check if either player has an empty hand and declare winner
				System.out.println("Player #"+winningPlayer+" is the Winner!");
				System.out.println("Hand_1:"+hand1.size()+" Hand_2:"+hand2.size()+"\n");
			}
		}while(continueGame && checkEmptyHand() == 0);
	}


	private static void goToWar(){
		winningPlayer = isPlayerAbleToGoToWar();
		if(winningPlayer == 0){//If no one has won yet...
			for(int i=0;i<3;i++){ //remove top 3 cards from each players hand and add to temp card holder
				addTopCardsToTemp();
			}
			//Compare top cards from each "hand" for final "War" result
			if(hand1.element().compareTo(hand2.element()) == -1){ //player 1 lost this round
				System.out.println("War Results: " + hand1.element() + " vs " + hand2.element()+"\n");
				addTopCardsToTemp();
				hand2.addAll(tempCardHolder);//Player 2 gets spoils of wars (whole temp pile)
				tempCardHolder.clear();
			}else if(hand1.element().compareTo(hand2.element()) == 1){ //player 1 won this round
				System.out.println("War Results: " + hand1.element() + " vs " + hand2.element()+"\n");
				addTopCardsToTemp();
				hand1.addAll(tempCardHolder);//Player 1 gets spoils of war (whole temp pile)
				tempCardHolder.clear();
			}else{//Players tied so they go again !
				addTopCardsToTemp();
				System.out.println("THE WAR RAGES ON !!!!!!");
				goToWar();//go to war again keeping the temp pile as is so war winner can collect everything from both rounds
			}
		}else{//Player has already been determined
			System.out.println("Player #"+winningPlayer+" is the Winner because he can go to war again!");
			continueGame = false;
			System.out.println("Hand_1:"+hand1.size()+" Hand_2:"+hand2.size()+"\n");
		}
	}


	private static void addTopCardsToTemp() {
		tempCardHolder.add(hand1.poll());
		tempCardHolder.add(hand2.poll());
	}

	private static void distributeDeck() {
		for(int i=0; i<deck.size();i++){
			if(i%2 == 0){//alternates card distribution
				hand1.add(deck.get(i));
			}else{
				hand2.add(deck.get(i));
			}
			deck.remove(i);
		}
	}

	private static int isPlayerAbleToGoToWar(){
		if(hand1.size() < 4){
			return 2;
		}else if(hand2.size() < 4){
			return 1;
		}
		return 0;
	}

	private static int checkEmptyHand(){
		if(hand1.isEmpty()){
			return 2;
		}else if(hand2.isEmpty()){
			return 1;
		}
		return 0;
	}
}
