/*
 * Sophia Milask
 * 3/16/25
 * Tester for the stats operations
 */
public class StatsTester {

	public static void main(String[] args) {
		StatsOperations x = new StatsOperations();
		//passing through arbitrary probability double, int r
		System.out.println("Variance For Negative Binomial Distribution: " + x.findNegativeBinomVariance(.3, 2) + "\n\n");
		
		
		
		
		
		
		
		
		
		
		
		
		//set of y values
		double[] set = {2, 5, 3, 4};
		//set of respective probabilities
		double[] p = {.3, .1, .4, .2};
		//passing through set of y values and respective probabilities
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		System.out.println("Median: " + x.findMedian(set) + "\n\n");
		System.out.println("Mode: " + x.findMode(set) + "\n\n");
		System.out.println("Sample variance: " + x.findSampleVariance(set) + "\n\n");
		System.out.println("Population variance: " + x.findPopulationVariance(set) + "\n\n");
		System.out.println("Mean: " + x.findMean(set) + "\n\n");
		
		
		
		//passing through set of y values and their respective probabilities
		System.out.println("Expected Value For Discrete Random Variable: " + x.findDiscreteExpectedValue(set, p) + "\n\n");
		System.out.println("Variance For Discrete Random Variable: " + x.findDiscreteVariance(set, p) + "\n\n");
		
		
		
		//passing through an arbitrary integer
		System.out.println("Factorial: " + x.doFactorial(0) + "\n\n");
		
		
		
		//passing through 2 integers, 
		System.out.println("Permutation: " + x.doPermutation(5, 6) + "\n\n");
		System.out.println("Combination: " + x.doCombination(5, 6) + "\n\n");
		System.out.println("Binomial Distribution: " + x.findBinomialDistribution(.4, 4, 3) + "\n\n");
		System.out.println("Expected Value for Binomial Distribution: " + x.findBinomExpectedValue(.4, 5) + "\n\n");
		System.out.println("Variance for Binomial Distribution: " + x.findBinomVariance(.4, 5) + "\n\n");
		System.out.println("Expected Value for Geometric Distribution: " + x.findGeomExpectedValue(.4, 5) + "\n\n");
		System.out.println("Variance for Geometric Distribution: " + x.findGeomVariance(.4, 5) + "\n\n");
		System.out.println("Cumulative Binomial Distribution: " + x.findCumulativeBinomialDistribution(.437, 4, 3) + "\n\n");
		System.out.println("Geometric Distribution: " + x.findGeometricDistribution(.4, 5) + "\n\n");
		System.out.println("Cumulative Geometric Distribution: " + x.findCumulativeGeometricDistribution(.4, 5) + "\n\n");
		System.out.println("Hypergeometric Distribution: " + x.findHypergeometricDistribution(20, 5, 6, 4) + "\n\n");
		System.out.println("Expected Value For Hypergeometric Distribution: " + x.findHypergeometricExpectedValue(20, 5, 6) + "\n\n");
		System.out.println("Variance For Hypergeometric Distribution: " + x.findHypergeometricVariance(20, 5, 6) + "\n\n");
		System.out.println("Negative Binomial Distribution: " + x.findNegativeBinomialDistribution(.2, 5, 3) + "\n\n");
		System.out.println("Expected Value For Negative Binomial Distribution: " + x.findNegativeBinomExpectedValue(.2, 5) + "\n\n");
		System.out.println("Variance For Negative Binomial Distribution: " + x.findNegativeBinomVariance(.2, 5) + "\n\n");
		
	}

}
