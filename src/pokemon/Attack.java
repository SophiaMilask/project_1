public class Attack {
    private int energyCost;
    private String name;
    private int damage;
    private String energyCostType;

    public Attack(int energyCost, String energyCostType, String name, int damage) {
        this.energyCost = energyCost;
        this.name = name;
        this.damage = damage;
        this.energyCostType = energyCostType;
    }
    
    
    public Attack(String name, int damage) {
        
        
        this.damage = damage;
        this.energyCostType = energyCostType;
    }

    public String toString() {
        return "    - " + name + ": Damage = " + damage + " | Energy = " + energyCost + " " + energyCostType;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getEnergyCostType() {
        return energyCostType;
    }
    
    


    // Apply damage to a Pokemon
    public void applyDamage(PokemonCard targetPokemon) {
        targetPokemon.setHp(targetPokemon.getHp() - this.damage);
        System.out.println("Damage applied: " + this.damage + " to " + targetPokemon.getName());
    }
}
