/*
 * Sophia Milask
 * Probability and Applied Statistics
 * 3/8/25
 * This program simulates the Monty Hall 3 Door Paradox. For an arbitrarily chosen number of trials, the probability of 2 different cases will be found
 * There are 3 doors. 2 goat doors and 1 prize door with a car. The probability of winning the prize is calculated
 * First: a player chooses a door randomly. A door is revealed--it must not be the prize door or the chosen door. The player chooses to not switch doors
 * Second: a player chooses a door randomly. A door is revealed--it must not be the prize door or the chosen door. The player chooses to switch doors
 */

import java.util.Random;
public class ThreeDoors {
	//number of times the prize door was accurately chosen
	private int wins;
	private Random rand;
	
	public ThreeDoors() {
		this.wins = wins;
		this.rand = rand;
	}
	
	public void doDoorSimulation(int numTrials) {
		rand = new Random();
		//tracking the wins for a player choosing to stay
		wins = 0;
		//iterating the amount of trials chosen
		for(int i = 0; i < numTrials; i++) {
			//randomly choosing the prize door out of 3 options
			int prizeDoor = rand.nextInt(3);
			//randomly choosing contestant's chosen door out of 3
			int chosenDoor = rand.nextInt(3);
			//player wins if they chose the prize door
			if(chosenDoor==prizeDoor) {
				wins++;
			}
		}
		//output for staying player
		System.out.println("If contestant stays, chance of winning is " + (double)wins/numTrials * 100 + "%");
		
		//tracking the wins for when a player switches
		wins = 0;
		//iterating the amount of trials chosen
		for(int i = 0; i < numTrials; i++) {
			//randomly choosing the door with the prize out of 3 options
			int prizeDoor = rand.nextInt(3);
			//randomly choosing the player's chosen door out of 3 choices
			int chosenDoor = rand.nextInt(3);
			//initializing goatDoor to prizeDoor. This may seem incorrect, but the proceeding loop corrects this 
			int goatDoor = prizeDoor;
			//this while loop makes sure the goat door is chosen over and over until it's a different door than the prize and chosen door
			while(goatDoor == prizeDoor || goatDoor == chosenDoor) {
				goatDoor = rand.nextInt(3);
			}
			//initializing the last door to not be the revealed goat door or what the player chose
			int otherDoor = 3 - (goatDoor + chosenDoor);
			//the player switched to the other door. If it is the prize door, then they win
			if(otherDoor == prizeDoor) {
				wins++;
			}
		}
		//output
		System.out.println("If contestant switches, chance of winning is " + (double)wins/numTrials * 100 + "%");
		
	}
}
