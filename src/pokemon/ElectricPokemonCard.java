import java.util.ArrayList;

public class ElectricPokemonCard extends PokemonCard {
	
	public ElectricPokemonCard(String name, int hp, ArrayList<Attack> attacks, String weakness, int retreatCost) {
        super(name, hp, "Electric", attacks, weakness, retreatCost);
    }
}
