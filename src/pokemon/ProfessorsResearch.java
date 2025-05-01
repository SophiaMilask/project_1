public class ProfessorsResearch extends TrainerCard {
    public ProfessorsResearch(String name) {
        super(name);
    }

    @Override
    public void useForMe(Game game) {
        System.out.println("Professor's Research: Discard your hand and draw 7 cards.");
        
        // Discard all cards in the player's hand
        game.getMyHand().clear(); 

        // Draw 7 cards from the deck
        for (int i = 0; i < 7; i++) {
            if (game.getMyDeck().size() > 0) {
                // Draw the top card from the deck and add it to the hand
                game.getMyHand().add(game.getMyDeck().get(0));
                game.getMyDeck().remove(0);  // Remove the card from the deck
            } else {
                System.out.println("Not enough cards in the deck to draw any more.");
                break;  // Exit the loop if there aren't enough cards
            }
        }

        // Print the number of cards the player now has
        System.out.println("You now have 7 new cards.");
    }
    
    @Override
    public void useForAI(Game game) {
        System.out.println("Professor's Research: Discard your hand and draw 7 cards.");
        
        // Discard all cards in the AI's hand
        game.getAIHand().clear(); 

        // Draw 7 cards from the deck
        for (int i = 0; i < 7; i++) {
            if (game.getAIDeck().size() > 0) {
                // Draw the top card from the deck and add it to the hand
                game.getAIHand().add(game.getAIDeck().get(0));
                game.getAIDeck().remove(0);  // Remove the card from the deck
            } else {
                System.out.println("Not enough cards in the deck to draw any more.");
                break;  // Exit the loop if there aren't enough cards
            }
        }

        // Print the number of cards the player now has
        System.out.println("AI now has 7 new cards.");
    }


    

   
}
