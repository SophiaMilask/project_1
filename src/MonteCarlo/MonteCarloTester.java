/*
 * Sophia Milask
 * Probability and Applied Statistics
 * 3/4/25
 * This is the tester class for BirthdaySim, ThreeDoors, Mulligan, and RareCandySim
 */
public class MonteCarloTester {

	public static void main(String[] args) {
		BirthdaySim b = new BirthdaySim();
		//number of people in the class, number of trials
		b.doBirthdaySimulation(75, 10000);
		
		ThreeDoors d = new ThreeDoors();
		//number of trials
		d.doDoorSimulation(10000);
		
		Mulligan m = new Mulligan();
		//number of trials
		m.doMulliganSim(10000);
		
		RareCandySim r = new RareCandySim();
		//number of trials
		r.doRareCandySim(10000);

	}

}
