/**
 * IST256 Exam 5 Take Home Question - 10 points, two parts 5 points each.
 * 
 * The end-game of this assignment is to implement a way to save a deck of cards
 * to a file and load save cards from a file back into a card deck object.
 * 
 * To get this to work, you must do the following:
 * 
 * PART 1 : In Deck.Java file (5 points total)
 * - Implement the Load() and Save() methods inside the Deck.java file.
 * - These methods should serialize / de-serialize the all the cards in 
 *   the deck to and from the file.
 *   
 * PART 2: In the Exam5TakeHomeQuestion.java file (5 points total)
 * - Implement the main() method to accomplish the following task and thus
 *   prove your Load() and Save() methods are working, specifically:
 * 
 *   1) create two objects deck1, and deck2 of class deck, each with 52 cards
 *   2) shuffle the first deck of cards deck1
 *   3) print out both deck1 and deck2 (deck1 is shuffled, deck2 is normal)
 *   4) Save deck1 to the file ==> "deck.txt"
 *   5) load the file ==> "deck.txt" into deck2
 *   6) print out deck 1 and deck2 (they should be the same)
 * 
 *  BE SURE TO HAND IN BOTH YOUR Deck.java AND YOUR Exam5TakeHomeQuestion.java
 *  ANY OTHER SUBMISSION WILL NOT BE AWARDED CREDIT
 * 
 */

package playingcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Exam5TakeHomeQuestion {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here, as specified above
    	System.out.println("\u2663\u2666\u2665\u2660");
    	Deck deck1 = new Deck();
    	File file1 = new File("DeckEXP.txt");
    	System.out.println(deck1.toString());
    	deck1.Save(file1);
    	
        
    }
}
