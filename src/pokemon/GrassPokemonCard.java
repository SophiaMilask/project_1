import java.util.ArrayList;

public class GrassPokemonCard extends PokemonCard {
	
	public GrassPokemonCard(String name, int hp, ArrayList<Attack> attacks, String weakness, int retreatCost) {
        super(name, hp, "Grass", attacks, weakness, retreatCost);
    }
}
