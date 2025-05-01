import java.util.ArrayList;

public class Player {
	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> bench;
	private PokemonCard activePokemon;
	
	public void retreatActivePokemon() {
		if (bench.size() >= 5) {
			return;
		}
		
	}

	public PokemonCard getActivePokemon() {
		return activePokemon;
	}
	
	
	
}
