import java.util.ArrayList;

public class WaterPokemonCard extends PokemonCard {
	
	public WaterPokemonCard(String name, int hp, ArrayList<Attack> attacks, String weakness, int retreatCost) {
        super(name, hp, "Water", attacks, weakness, retreatCost);
    }
}
