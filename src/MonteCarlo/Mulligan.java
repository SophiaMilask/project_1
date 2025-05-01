/*
 * Sophia Milask
 * Probability and Applied Statistics
 * 3/6/25
 * this program finds the probability of drawing a beginning hand in the Pokemon Card Trading Game with no Pokemon in it, otherwise known as a mulligan. 
 * A deck is made in 60 configurations: //1 Pokemon and 59 energy, 2 energy and 58 energy, ..., 60 pokemon and no energy. The deck is then shuffled and a
 * hand of 7 is drawn from the top of the deck. The probability of drawing a //mulligan for each deck configuration is found by running each simulation 
 * an arbitrary amount of times. 
 */
import java.util.ArrayList;
import java.util.Collections;

public class Mulligan {
    
    public Mulligan() {
    }
    
    public void doMulliganSim(int numTrials) {
        //running through 60 configurations for each number of Pokemon in the deck
        for (int i = 1; i <= 60; i++) {
            int mulliganCount = 0;
            //each configuration will run the amount of times arbitrarily decided
            for (int j = 0; j < numTrials; j++) {
                ArrayList<String> deck = new ArrayList<>();
                //adding the amount of Pokemon equal to the configuration it's on to the deck
                for (int k = 0; k < i; k++) {
                    deck.add("Pokemon");
                }
                //filling the rest of the deck with energy cards
                for (int k = i; k < 60; k++) {
                    deck.add("Energy");
                }
                
                Collections.shuffle(deck);
                
                boolean isMulligan = true;
                //checking the top 7 cards of the deck for a Pokemon card
                for (int k = 0; k < 7; k++) {
                    if (deck.get(k).equals("Pokemon")) {
                        isMulligan = false;
                        //exiting the loop if a Pokemon is found
                        break;
                    }
                }
                
                if (isMulligan) {
                    mulliganCount++;
                }
            }
            //output
            System.out.println("Probability of drawing a mulligan with " + i + " Pokemon cards is " + (double)mulliganCount / numTrials * 100 + "%");
            
        }
    }
}
