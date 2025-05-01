
public class EnergyCard extends Card{
	
	private String energyType;
	
	public EnergyCard(String name, String energyType) {
		super(name);
		this.energyType = energyType;
	}
	
	public String getEnergyType() {
		return energyType;
	}
	
	public void setEnergyType(String newType) {
		energyType = newType;
	}

}
