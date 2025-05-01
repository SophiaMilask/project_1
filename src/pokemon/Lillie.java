public class Lillie extends TrainerCard {
    public Lillie(String name) {
        super(name);
    }

    @Override
    public void useForMe(Game game) {
        
        while(game.getMyHand().size() < 6) {
        	if (game.getMyDeck().size() >= 1) {
                for (int i = 0; i < 1; i++) {
                    game.getMyHand().add(game.getMyDeck().get(0));  // Add card from deck to hand
                    game.getMyDeck().remove(0);  // Remove card from deck
                }
            } else {
                System.out.println("Not enough cards in the deck to draw any more.");
            }
        }
        System.out.println("You now have 6 cards");
        
    }
    
    
    @Override
    public void useForAI(Game game) {
        
        while(game.getAIHand().size() < 6) {
        	if (game.getAIDeck().size() >= 1) {
                for (int i = 0; i < 1; i++) {
                    game.getAIHand().add(game.getAIDeck().get(0));  // Add card from deck to hand
                    game.getAIDeck().remove(0);  // Remove card from deck
                }
            } else {
                System.out.println("Not enough cards in the deck to draw any more.");
            }
        }
        System.out.println("AI now has 6 cards");
        
    }
    
}
