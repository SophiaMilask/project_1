/* 
 * Sophia Milask
 * Probability and Applied Statistics
 * 3/15/25
 * Stats library for all of the formulas covered in the first semester of the class
 */




import java.util.Arrays;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class StatsOperations {
    private static final DecimalFormat format = new DecimalFormat("#.####");

    public StatsOperations() {
    }

    public double findMean(double[] userInputNumbers) {
    	//check to make sure list isn't empty
        if (userInputNumbers == null || userInputNumbers.length == 0) {
            System.out.println("Invalid input: Array is empty or null.");
            return Double.NaN;
        }

        double total = 0;
        //adding together every number in the set
        for (double singleNumber : userInputNumbers) {
            total += singleNumber;
        }
        //returning the sum of all the numbers divided by the amount of numbers
        return Double.parseDouble(format.format(total / userInputNumbers.length));
    }

    public double findMedian(double[] userInputNumbers) {
    	//check to make sure list isn't empty
        if (userInputNumbers == null || userInputNumbers.length == 0) {
            System.out.println("Invalid input: Array is empty or null.");
            return Double.NaN;
        }
        //putting the numbers in ascending order
        Arrays.sort(userInputNumbers);
        //if there are an even amount of numbers, the median must be the average of the middle 2 numbers
        if (userInputNumbers.length % 2 == 0) {
            return Double.parseDouble(format.format((userInputNumbers[(userInputNumbers.length / 2) - 1] + userInputNumbers[(userInputNumbers.length / 2)]) / 2.0));
            //if there are an odd amount of numbers, the median is just the middle number
        } else {
            return Double.parseDouble(format.format(userInputNumbers[userInputNumbers.length / 2]));
        }
    }

    public ArrayList<Double> findMode(double[] userInputNumbers) {
    	//check to make sure the set isn't empty
        if (userInputNumbers == null || userInputNumbers.length == 0) {
            System.out.println("Invalid input: Array is empty or null.");
            return null;
        }
        //put set in ascending order
        Arrays.sort(userInputNumbers);
        ArrayList<Double> modes = new ArrayList<>();
        //tracks the highest occurence of a number
        int max = 0;
        //count of the current number
        int count = 1;

        for (int i = 1; i < userInputNumbers.length; i++) {
        	//if the current number is the same as the previous number, the current count increases
            if (userInputNumbers[i] == userInputNumbers[i - 1]) {
                count++;
            } else {
            	//if the current count is higher than the max count, the current is the new maximum
            	//current modes clear and the new current mode of the iteration is added
                if (count > max) {
                    max = count;
                    modes.clear();
                    modes.add(userInputNumbers[i - 1]);
                    //if the current count is the same as the highest count, the current number is added to the modes
                } else if (count == max) {
                    modes.add(userInputNumbers[i - 1]);
                }//current count is set back to 1 for the next iteration
                count = 1;
            }
        }
        //checking the last number for edge cases, since not handled in the previous loop
        if (count > max) {
            modes.clear();
            modes.add(userInputNumbers[userInputNumbers.length - 1]);
        } else if (count == max) {
            modes.add(userInputNumbers[userInputNumbers.length - 1]);
        }


        return modes;
    }

    public double findSampleVariance(double[] userInputNumbers) {
    	//check to make sure the set has at least 2 elements to take the variance of
        if (userInputNumbers == null || userInputNumbers.length < 2) {
            System.out.println("Invalid input: Set must have at least two elements.");
            return Double.NaN;
        }
        //calling findMean for the input set
        double mean = findMean(userInputNumbers);
        double sum = 0;
        //for every element in the set: subtracting the mean from the number, squaring the difference, and adding up all of the differences
        for (double element : userInputNumbers) {
            sum += Math.pow(element - mean, 2);
        }
        //return sum of squares divided by 1 less than the amount of elements
        return Double.parseDouble(format.format(sum / (userInputNumbers.length - 1)));
    }

    public double findPopulationVariance(double[] userInputNumbers) {
    	//check to make sure the set has at least 2 elements to take the variance of
        if (userInputNumbers == null || userInputNumbers.length < 2) {
            System.out.println("Invalid input: Set must have at least two elements.");
            return Double.NaN;
        }

        double mean = findMean(userInputNumbers);
        double sum = 0;
      //for every element in the set: subtracting the mean from the number, squaring the difference, and adding up all of the differences
        for (double element : userInputNumbers) {
            sum += Math.pow(element - mean, 2);
        }
      //return sum of squares divided by the amount of elements
        return Double.parseDouble(format.format(sum / (userInputNumbers.length)));
    }
    
    public double findDiscreteExpectedValue(double[] yValues, double[] probabilities) {
    	//check to make sure neither set is empty
        if (yValues == null || probabilities == null || yValues.length == 0 || probabilities.length == 0) {
            System.out.println("Invalid input: y values and probabilities must not be empty.");
            return Double.NaN;
        }
        //check to make sure the y values and the probabilities have the same number of elements
        if (yValues.length != probabilities.length) {
            System.out.println("Invalid input: Every y value must have a corresponding probability");
            return Double.NaN;
        }

        double sumOfProbabilities = 0;
        //check to make sure all of the probabilities are non-negative
        for (double p : probabilities) {
            if (p < 0) {
                System.out.println("Invalid input: Probabilities must be non-negative.");
                return Double.NaN;
            }
            sumOfProbabilities += p;
        }
        //check to make sure all of the probabilities add up to 1
        if (sumOfProbabilities != 1) {
            System.out.println("Invalid input: The sum of probabilities must equal 1.");
            return Double.NaN;
        }

        double sum = 0;
        //multiplying each y value by its corresponding probability and adding together all of the products
        for (int i = 0; i < yValues.length; i++) {
            sum += yValues[i] * probabilities[i];
        }
        return Double.parseDouble(format.format(sum));
    }
    
    public double findDiscreteVariance(double[] yValues, double[] probabilities) {
    	//check to make sure neither set is empty
        if (yValues == null || probabilities == null || yValues.length == 0 || probabilities.length == 0) {
            System.out.println("Invalid input: y values and probabilities must not be empty.");
            return Double.NaN;
        }
      //check to make sure the y values and the probabilities have the same number of elements 
        if (yValues.length != probabilities.length) {
            System.out.println("Invalid input: Every y value must have a corresponding probability.");
            return Double.NaN;
        }

        double sumOfProbabilities = 0;
        double expectedValue = findDiscreteExpectedValue(yValues, probabilities);
        double expectedValueSquared = 0;

        for (int i = 0; i < yValues.length; i++) {
            double p = probabilities[i];
            //check to make sure all probabilities are non-negative
            if (p < 0) {
                System.out.println("Invalid input: Probabilities must be non-negative.");
                return Double.NaN;
            }
            
            sumOfProbabilities += p;
            
            
            //summation of all of the squared y values being multiplied by their respective probabilities
            expectedValueSquared += Math.pow(yValues[i], 2) * p;
        }

        //check to make sure the sum of the probabilities is 1
        if (sumOfProbabilities != 1) { 
            System.out.println("Invalid input: The sum of probabilities must equal 1.");
            return Double.NaN;
        }
        //returning the difference of the summation of the sqaured value products and the expected value being squared
        return Double.parseDouble(format.format(expectedValueSquared - Math.pow(expectedValue, 2)));
    }

    public BigInteger doFactorial(int num) {
    	//check to make sure the input is non-negative
        if (num < 0) {
            System.out.println("Invalid input: Factorial is undefined for negative numbers.");
            return BigInteger.ZERO;
        }
        //starting off with a factor of 1
        BigInteger answer = BigInteger.ONE;
        //starting at the input number, and then every decreasing integer until 1, multiplying that integer by the answer
        for (int i = num; i > 0; i--) {
            answer = answer.multiply(BigInteger.valueOf(i));
        }
        return answer;
    }

    public BigInteger doPermutation(int n, int r) {
    	//check to make sure n and r are non-negative and that n is at least r
        if (n < 0 || r < 0 || r > n) {
            System.out.println("Invalid input: Ensure n < 0, r < 0, and n is at least r.");
            return BigInteger.ZERO;
        }
        //returning n factorial divided by n - r factorial
        return doFactorial(n).divide(doFactorial(n - r));
    }

    public BigInteger doCombination(int n, int r) {
    	//check to make sure n and r are non-negative and that n is at least r
        if (n < 0 || r < 0 || r > n) {
            System.out.println("Invalid input: Ensure n < 0, r < 0, and n is at least r");
            return BigInteger.ZERO;
        }
        //returning n factorial divided by the product of r factorial and n - r factorial
        return doFactorial(n).divide(doFactorial(r).multiply(doFactorial(n - r)));
    }
    
    //helper method for cumulative distributive methods
    int getUserChoice(String prompt) {
        Scanner scan = new Scanner(System.in);
        //printing the prompt for the respective method
        System.out.println(prompt);
        //taking in the user's choice as an int
        int userChoice = scan.nextInt();
        //if the user doesn't choose one of the valid options, -1 is returned to indicate that invalidity
        if (userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != 4) {
            System.out.println("Invalid input: You must enter a number 1, 2, 3, or 4");
            return -1;  
        }
        //otherwise returning their valid choice
        return userChoice;
    }
    //helper method to determine if arguments are valid for all methods regarding binomial distribution
    boolean isValidBinomialParameters(double p, int n, int y) {
    	//checks that the probability is between 0 and 1 (1 inclusive), n and y are non-negative, and n is greater than y
        if (p <= 0 || p > 1 || n < 0 || y < 0 || y > n) {
            System.out.println("Invalid input for binomial distribution: Ensure that 0 < p ≤ 1, 0 ≤ n, and 0 ≤ y ≤ n");
            return false;
        }
        //returns true otherwise
        return true;
    }
    //helper method to determine if arguments are valid for all methods regarding geometric distribution
    boolean isValidGeometricParameters(double p, int y) {
    	//checks that the probability is between 0 and 1 (1 inclusive) and that y is at least 1
        if (p <= 0 || p > 1 || y < 1) {
            System.out.println("Invalid input for geometric distribution: Ensure that 0 < p ≤ 1 and 1 ≤ y");
            return false;
        }
        //returns true otherwise
        return true;
    }

    public double findBinomialDistribution(double p, int n, int y) {
    	//calls helper method to determine argument validity
        if (!isValidBinomialParameters(p, n, y)) {
            return Double.NaN;
        }
        //calling doCombination to do n choose y
        BigInteger combination = doCombination(n, y);
        //the rest of the probability is calculated by raising the probability to the y value and multiplying that by the fail probability to the n - y
        double probability = Math.pow(p, y) * Math.pow(1 - p, n - y);
        //returning the product of the combination and powers
        return Double.parseDouble(format.format(combination.doubleValue() * probability));
    }

    public double findBinomExpectedValue(double p, int n) {
    	//calls helper method to determine validity of arguments
        if (!isValidBinomialParameters(p, n, 0)) {
            return Double.NaN;
        }
        //returning the probability by the n value
        return Double.parseDouble(format.format(p * n));
    }

    public double findBinomVariance(double p, int n) {
    	//calls helper method to determine validity of arguments
        if (!isValidBinomialParameters(p, n, 0)) {
            return Double.NaN;
        }
        //returning the product of the n value, probability of success, and fail probability
        return Double.parseDouble(format.format(n * p * (1 - p)));
    }

    
    public double findCumulativeBinomialDistribution(double p, int n, int y) {
    	//calls helper method to determine validity of arguments
        if (!isValidBinomialParameters(p, n, y)) {
            return Double.NaN;
        }
        //calling other helper method to determine what the user wants to find
        int userChoice = getUserChoice(
            "1. Less than your y value\n2. Less than or equal to your y value\n3. Greater than your y value\n4. Greater than or equal to your y value\n(Please type a number)");
        //returning nothing if the user didn't choose a valid choice
        if (userChoice == -1) {
            return Double.NaN;
        }

        double sum = 0;
        //if the user chose less than the y value, the sum will be all of the binomial distributions from 0 to one less than the y value
        if (userChoice == 1) {
            for (int i = 0; i < y; i++) {
                sum += findBinomialDistribution(p, n, i);
            }
            return Double.parseDouble(format.format(sum));
        //if the user chose less than or equal to the y value, the sum will be all of the binomial distributions from 0 to the y value
        } else if (userChoice == 2) {
            for (int i = 0; i <= y; i++) {
                sum += findBinomialDistribution(p, n, i);
            }
            return Double.parseDouble(format.format(sum));
        //if the user chose greater than the y value, the answer will be the complement of all of the binomial distributions from 0 to the y value
        } else if (userChoice == 3) {
            for (int i = 0; i <= y; i++) {
                sum += findBinomialDistribution(p, n, i);
            }
            return Double.parseDouble(format.format(1 - sum));
        //if the user chose greater than or equal to the y value, the answer will be the complement of all of the binomial distributions from 0
        //to 1 less than the y value
        } else if (userChoice == 4) {
            for (int i = 0; i < y; i++) {
                sum += findBinomialDistribution(p, n, i);
            }
            return Double.parseDouble(format.format(1 - sum));
        }
        return Double.NaN;
    }
    
    
    public double findGeometricDistribution(double p, int y) {
    	//calling the helper method to determine validity of arguments
        if (!isValidGeometricParameters(p, y)) {
            return Double.NaN;
        }
        //returning the fail probability to 1 less than the y values multiplied by the probability of success
        return Double.parseDouble(format.format(p * Math.pow(1 - p, y - 1)));
    }

    public double findGeomExpectedValue(double p, int y) {
    	//calling helper method to determine validity of arguments
        if (!isValidGeometricParameters(p, y)) {
            return Double.NaN;
        }
        //returning 1 divided by the probability of success
        return Double.parseDouble(format.format(1 / p));
    }

    public double findGeomVariance(double p, int y) {
    	//calling helper method to determine validity of arguments
        if (!isValidGeometricParameters(p, y)) {
            return Double.NaN;
        }
        //returning the probability of failure divided by the probability of success being squared
        return Double.parseDouble(format.format((1 - p) / (p * p)));
    }

    public double findCumulativeGeometricDistribution(double p, int y) {
    	//calling helper method to determine validity of arguments
        if (!isValidGeometricParameters(p, y)) {
            return Double.NaN;
        }
        //calling the other helper method to determine what user wants to find
        int userChoice = getUserChoice(
            "1. Less than your y value\n2. Less than or equal to your y value\n3. Greater than your y value\n4. Greater than or equal to your y value\n(Please type a number)");
        //check to make sure the user's choice is valid
        if (userChoice == -1) {
            return Double.NaN;
        }

        //if the user chose less than the y value, returning the complement of the probability of failure raised to 1 less than the y value
        if (userChoice == 1) {
            return Double.parseDouble(format.format(1 - Math.pow(1 - p, y - 1)));
        //if user chose less than or equal to the y value, returning the complement of the probability of failure raised to the y value
        } else if (userChoice == 2) {
            return Double.parseDouble(format.format(1 - Math.pow(1 - p, y)));
        //if user chose greater than y value, returning the probability of failure raised to the y value
        } else if (userChoice == 3) {
            return Double.parseDouble(format.format(Math.pow(1 - p, y)));
        //if user chose greater than or equal to y value, returning the probability of failure raised to 1 less than the y value
        } else if (userChoice == 4) {
            return Double.parseDouble(format.format(Math.pow(1 - p, y - 1)));
        }
        return Double.NaN;
    }
    
    public double findHypergeometricDistribution(int N, int n, int r, int y) {
    	//check to make sure all inputs are non-negative, N is at least n and r, r is at least y, and n is at least y
        if (N < 0 || n < 0 || r < 0 || y < 0 || n > N || r > N || y > r || y > n) {
            System.out.println("Invalid input: All values must be at least 0, n ≤ N, r ≤ N, y ≤ r, and y ≤ n");
            return Double.NaN;
        }
        //the first factor of the numerator is the combination r choose y
        BigInteger num1 = doCombination(r, y);
        //the second factor of the numerator is the combination N - r choose n - y
        BigInteger num2 = doCombination(N - r, n - y);
        //the denominator is the combination N choose n
        BigInteger den = doCombination(N, n);
        //calculating the numerator by multiplying the 2 factors of the numerator
        BigInteger numerator = num1.multiply(num2);
        //making the numerator a BigDecimal object (credit: Oracle, https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html)
        BigDecimal numeratorDecimal = new BigDecimal(numerator);
        //making the denominator a BigDecimal
        BigDecimal denominatorDecimal = new BigDecimal(den);
        //result is the numerator divided by the denominator, and rounds up after 10 decimal places to control precision
        //(credit: Oracle, https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html)
        BigDecimal result = numeratorDecimal.divide(denominatorDecimal, 10, RoundingMode.HALF_UP);
        //return the formatted result as a double
        return Double.parseDouble(format.format(result.doubleValue()));
    }
    
    public double findHypergeometricExpectedValue(int N, int n, int r) {
    	//checks all inputs are non-negative and N is at least n and r
    	if(N < 0 || n < 0 || r < 0 || n > N || r > N) {
    		System.out.println("Invalid input: All values must be at least 0, n ≤ N, and r ≤ N");
    		return Double.NaN;
    	}
    	//returns the product of n and r divided by N
    	return Double.parseDouble(format.format((double) n * r / N));
    }
    
    public double findHypergeometricVariance(int N, int n, int r) {
    	//check that all inputs are non-negative and N is at least n and r
    	if(N < 0 || n < 0 || r < 0 || n > N || r > N) {
    		System.out.println("Invalid input: All values must be at least 0, n ≤ N, and r ≤ N");
    		return Double.NaN;
    	}
    	//returning variance formula for hypergeometric function
    	return Double.parseDouble(format.format((double) n * r * (N - r) * (N - n) / (N * N * (N - 1))));
    }
    
    public double findNegativeBinomialDistribution(double p, int y, int r) {
    	//checks that the probability is between 1 and 0 (1 inclusive), y and r are at least 1, and y is at least r
    	if (p <= 0 || p > 1 || y < 1 || r < 1 || r > y) {
    		System.out.println("Invalid input: Ensure that 0 < p ≤ 1, y ≥ 1, r ≥ 1, and y ≥ r");
    		return Double.NaN;
    	}
    	//the combination part of the function is y - 1 choose r - 1
    	BigInteger combination = doCombination(y - 1, r - 1);
    	//the rest of the function is the product of the probability of success raised to the r and the probability of failure raised to the y - r
    	double rest = Math.pow(p, r) * Math.pow(1 - p, y - r);
    	//returning the product of the combination and the rest of the formula
    	return Double.parseDouble(format.format(combination.doubleValue() * (rest)));
    }
    
    public double findNegativeBinomExpectedValue(double p, int r) {
    	//check to make sure probability is between 0 and 1 (1 inclusive) and r is at least 1
    	if (p <= 0 || p > 1 || r < 1) {
    		System.out.println("Invalid input: Ensure that 0 < p ≤ 1 and r ≥ 1");
    		return Double.NaN;
    	}
    	//returning r value divided by the probability
    	return Double.parseDouble(format.format(r / p));
    }
    
    public double findNegativeBinomVariance(double p, int r) {
    	//check to make sure probability is between 0 and 1 (1 inclusive) and r is at least 1
    	if (p <= 0 || p > 1 || r < 1) {
    		System.out.println("Invalid input: Ensure that 0 < p ≤ 1 and r ≥ 1");
    		return Double.NaN;
    	}
    	//returning the product of the r value and probability of failure divided by the probability of success being squared
    	return Double.parseDouble(format.format(r * (1 - p) / (p * p)));
    }

}
