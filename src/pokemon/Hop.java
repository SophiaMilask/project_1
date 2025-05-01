public class Hop extends TrainerCard {
    public Hop(String name) {
        super(name);
        
    }

    @Override
    public void useForMe(Game game) {
        if (game.getMyDeck().size() >= 3) {
            // Draw 3 cards from the deck and add them to the hand
            for (int i = 0; i < 3; i++) {
                game.getMyHand().add(game.getMyDeck().get(0));  // Add the first card from the deck to hand
                game.getMyDeck().remove(0);  // Remove that card from the deck
            }
            System.out.println("You drew 3 new cards.");
        } else {
            System.out.println("Not enough cards in the deck to draw 3 more.");
        }
    }

    @Override
    public void useForAI(Game game) {
        
        if (game.getAIDeck().size() >= 3) {
            for (int i = 0; i < 2; i++) {
                game.getAIHand().add(game.getAIDeck().get(0));  // Add card from deck to hand
                game.getAIDeck().remove(0);  // Remove card from deck
            }
            System.out.println("AI drew 3 new cards.");
        } else {
            System.out.println("Not enough cards in the deck to draw 3 more.");
        }
    }
}
