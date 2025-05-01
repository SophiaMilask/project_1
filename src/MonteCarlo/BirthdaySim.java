/*
 * Sophia Milask
 * Probability and Applied Statistics
 * 3/4/25
 * This program runs a simulation an arbitrary amount of times to find the probability of a shared birthday being found in a class of arbitrary size
 */



import java.util.ArrayList;
import java.util.Random;

public class BirthdaySim {
    private ArrayList<Person> people;
    private Random rand;

    public BirthdaySim() {
        this.people = new ArrayList<>();
        this.rand = new Random();
    }
    
    public void doBirthdaySimulation(int classSize, int numRuns) {
        int sharedCount = 0;
        //runs arbitrary amount of simulations
        for (int i = 0; i < numRuns; i++) {
            people.clear();
            boolean hasShared = false;
            
            for (int j = 0; j < classSize; j++) {
            	//a new Person object is made with a random birthday classSize amount of times
                int randBirthday = rand.nextInt(365) + 1;
                Person person = new Person(randBirthday);
                //checking if the current person has the same birthday as the random one
                for (Person p : people) {
                    if (p.getBirthday() == randBirthday) {
                        hasShared = true;
                        break;
                    }
                }
                //exiting the loop if a shared birthday was found
                if (hasShared) {
                    break;
                }
                //if not shared yet, adding to list of people to check against future birthdays
                people.add(person);
            }

            if (hasShared) {
                sharedCount++;
            }
        }

        double probability = (double) sharedCount / numRuns;
        //output
        System.out.println("Probability of at least two people sharing a birthday: " + probability * 100 + "%");
    }
}
