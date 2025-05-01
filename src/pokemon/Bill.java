public class Bill extends TrainerCard {
    public Bill(String name) {
        super(name);
    }

    @Override
    public void useForMe(Game game) {
       
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
