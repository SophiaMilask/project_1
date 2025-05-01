import java.util.ArrayList;
import java.util.List;

public class PokemonCard extends Card {
    private int hp;
    private String type;
    private ArrayList<Attack> attacks;
    private ArrayList<EnergyCard> energyCards;
    private String weakness;
    private int retreatCost;
    private List<EnergyCard> attachedEnergy; // List of energy actually attached to the Pokemon

    public PokemonCard(String name, int hp, String type, ArrayList<Attack> attacks, String weakness, int retreatCost) {
        super(name);
        this.hp = hp;
        this.type = type;
        this.attacks = attacks;
        this.energyCards = new ArrayList<EnergyCard>();
        this.attachedEnergy = new ArrayList<EnergyCard>(); // Initialize attached energy list
        this.weakness = weakness;
        this.retreatCost = retreatCost;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int newHp) {
        hp = newHp;
    }

    public String getType() {
        return type;
    }

    public void setType(String newType) {
        type = newType;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> newAttacks) {
        attacks = newAttacks;
    }

    public ArrayList<EnergyCard> getEnergyForAttacks() {
        return energyCards;
    }

    public void setEnergyForAttacks(ArrayList<EnergyCard> newEnergy) {
        energyCards = newEnergy;
    }

    public void addEnergyCard(EnergyCard energy) {
        energyCards.add(energy);
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String newWeakness) {
        weakness = newWeakness;
    }

    public int getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(int newCost) {
        retreatCost = newCost;
    }

    // New method to add energy to the attached list (used for attacks)
    public void attachEnergyCard(EnergyCard energy) {
        attachedEnergy.add(energy);
    }

    // Returns the total number of energy cards attached to this Pokemon
    public int getEnergyCount() {
        return attachedEnergy.size();
    }

    // Returns a specific type of energy count attached to this Pokemon
    public int getEnergyCountByType(String energyType) {
        int count = 0;
        for (EnergyCard energy : attachedEnergy) {
            if (energy.getEnergyType().equals(energyType)) {
                count++;
            }
        }
        return count;
    }


    public void display() {
        int width = 25;
        int height = 10;
        char[][] vals = new char[height][width];

        for (int i = 0; i < vals[0].length; i++) {
            vals[0][i] = '*';
            vals[height - 1][i] = '*';
        }

        for (int i = 1; i < height - 1; i++) {
            vals[i][0] = '*';
            vals[i][width - 1] = '*';
        }

        writeToCharArray(3, super.getName(), vals);
        writeToCharArray(4, "Type: " + getType(), vals);
    }

    private void writeToCharArray(int height, String s, char[][] vals) {
        int x = 3;
        while (vals[height][x] == 0) {
            x++;
        }
        for (int i = 0; i < s.length(); i++) {
            vals[height][x + i] = s.charAt(i);
        }
    }
    
   
}
