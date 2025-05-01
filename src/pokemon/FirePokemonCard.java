import java.util.ArrayList;

public class FirePokemonCard extends PokemonCard {
	
	public FirePokemonCard(String name, int hp, ArrayList<Attack> attacks, 
		 String weakness, int retreatCost) {
        super(name, hp, "Fire", attacks, weakness, retreatCost);
    }
}
