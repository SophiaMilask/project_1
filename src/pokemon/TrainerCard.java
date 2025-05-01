public class TrainerCard extends Card {
    public TrainerCard(String name) {
        super(name);
    }

    // Override this method in subclasses to define the specific effect of each trainer card.
    public void useForMe(Game game) {
        System.out.println(this.getName() + " was used.");
    }
    
    public void useForAI(Game game) {
        System.out.println(this.getName() + " was used.");
    }
}
