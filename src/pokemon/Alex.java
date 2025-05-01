public class Alex extends TrainerCard {
    public Alex(String name) {
        super(name);
    }

    @Override
    public void useForMe(Game game) {
        
        // Discard the 1st card in hand
        if (!game.getMyHand().isEmpty()) {
            game.getMyHand().remove(0); // Removes the first card from the player's hand
        }

        // Draw 2 new cards
        if (game.getMyDeck().size() >= 2) {
            for (int i = 0; i < 2; i++) {
                game.getMyHand().add(game.getMyDeck().get(0));  // Add card from deck to hand
                game.getMyDeck().remove(0);  // Remove card from deck
            }
            System.out.println("You drew 2 new cards.");
        } else {
            System.out.println("Not enough cards in the deck to draw 2 more.");
        }
    }


    @Override
    public void useForAI(Game game) {
        
        
        // Discard the 1st card in hand
        if (!game.getAIHand().isEmpty()) {
            game.getAIHand().remove(0); // Removes the first card from the player's hand
        }

        // Draw 2 new cards
        if (game.getAIDeck().size() >= 2) {
            for (int i = 0; i < 2; i++) {
                game.getAIHand().add(game.getAIDeck().get(0));  // Add card from deck to hand
                game.getAIDeck().remove(0);  // Remove card from deck
            }
            System.out.println("AI drew 2 new cards.");
        } else {
            System.out.println("Not enough cards in the deck to draw 2 more.");
        }
    }
    
}
