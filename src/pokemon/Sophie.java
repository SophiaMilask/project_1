public class Sophie extends TrainerCard {
    public Sophie(String name) {
        super(name);
    }

    @Override
    public void useForMe(Game game) {
        

        // Iterate through the deck to find the first Pokemon card
        for (int i = 0; i < game.getMyDeck().size(); i++) {
            Card card = game.getMyDeck().get(i);
            
            // Check if the card is a Pokemon card
            if (card instanceof PokemonCard) {
                // Add the Pokemon card to the hand
                game.getMyHand().add(card);
                // Remove the card from the deck
                game.getMyDeck().remove(i);
                
                System.out.println("You found a Pokémon card: " + card.getName());
                return; // Exit after adding the first Pokemon card
            }
        }

        // If no Pokemon card was found in the deck
        System.out.println("There were no Pokémon cards in the deck.");
    }
    
    @Override
    public void useForAI(Game game) {
        

        // Iterate through the deck to find the first Pokemon card
        for (int i = 0; i < game.getAIDeck().size(); i++) {
            Card card = game.getAIDeck().get(i);
            
            // Check if the card is a Pokemon card
            if (card instanceof PokemonCard) {
                // Add the Pokemon card to the hand
                game.getAIHand().add(card);
                // Remove the card from the deck
                game.getAIDeck().remove(i);
                
                System.out.println("AI found a Pokémon card: " + card.getName());
                return; // Exit after adding the first Pokemon card
            }
        }

        // If no Pokemon card was found in the deck
        System.out.println("There were no Pokémon cards in the deck.");
    }



}
