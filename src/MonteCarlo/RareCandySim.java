/* 
 * Sophia Milask
 * Probability and Applied Statistics
 * 3/4/25
 * This program determines the odds of all rare candies in a Pokemon deck being in the prize pile. There are 4 deck configurations: 1, 2, 3, or 4 rare
 * candies, with the remaining cards being Pokemon or energy to fill 60. Each configuration will run an arbitrary amount of times and the resulting
 * probability of each of the 4 will be displayed. For each simulation, a deck will be made with the respective amount of rare candy, energy, and
 * Pokemon, the deck will be shuffled, a hand will be drawn until it is not a mulligan, and a prize pile will be made.
 * 
 */


import java.util.ArrayList;
import java.util.Collections;

public class RareCandySim {
    
    public RareCandySim() {
    }
    
    public void doRareCandySim(int numTrials) {
        //looping through the possibilities of a deck having 1, 2, 3, or 4 rare candies
        for (int i = 1; i <= 4; i++) {
        	//tracking the amount of times all of the rare candies are in the prize pile
            int lossCount = 0;
            //each configuration will iterate numTrials times
            for (int j = 0; j < numTrials; j++) {
                ArrayList<String> deck = new ArrayList<>();
                //adding the amount of rare candies respective to the outer loop iteration number
                for (int k = 0; k < i; k++) {
                    deck.add("Rare Candy");
                }
                //for each configuration, adding 20 energy cards
                for (int k = i; k < 20; k++) {
                    deck.add("Energy");
                }
                //filling the remaining deck with Pokemon cards
                for (int k = 0; k < 60 - i - 20; k++) {
                    deck.add("Pokemon");
                }
                boolean isValid = false;
                //loop to keep reshuffling and redrawing hands until a non-mulligan hand is drawn
                while(!isValid) {
                    Collections.shuffle(deck);
                    ArrayList<String> hand = new ArrayList<>();
                    //adding top 7 cards from the deck to our hand
                    for(int k = 0; k < 7; k++) {
                        hand.add(deck.get(0));
                        deck.remove(0);
                    }
                    boolean isMulligan = true;
                    //traversing through the hand to find if there are any Pokemon
                    for (String card : hand) {
                        if (card.equals("Pokemon")) {
                        	//once Pokemon is found, we can assure there is no mulligan and we can stop the enhanced for loop
                            isMulligan = false;
                            break;
                        }
                    }
                    //if there is a mulligan, isValid will remain false so the while loop will keep running until there is a valid hand
                    if(isMulligan) {
                        isValid = false;
                    } else {
                    	//if we know we have a valid hand, we can continue to make a prize pile and fill it with the next 6 cards from the deck
                        ArrayList<String> prizePile = new ArrayList<>();
                        for(int k = 0; k < 6; k++) {
                            prizePile.add(deck.get(0));
                            deck.remove(0);
                        }
                        int rareCandyCount = 0;
                        //traversing through the prize pile to count the number of rare candies
                        for (String card : prizePile) {
                            if (card.equals("Rare Candy")) {
                                rareCandyCount++;
                            }
                        }
                        //if the amount of rare candies found in the prize pile are equal to the amount in the entire deck, we increase lossCount
                        if (rareCandyCount == i) {
                            lossCount++;
                        }
                        //we exit the while loop
                        isValid = true;
                    }
                }
            }
            //output
            System.out.println("The probability of all " + i + " rare candies in prize pile: " + (double)lossCount / numTrials * 100 + "%");
        }
    }
}
