import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Game {
	private ArrayList<Card> myDeck;
	private ArrayList<Card> AIDeck;
	private ArrayList<Card> myHand;
	private ArrayList<Card> AIHand;
	private ArrayList<Card> myPrizePile;
	private ArrayList<Card> AIPrizePile;
	private ArrayList<Card> myDiscard;
	private ArrayList<Card> AIDiscard;
	private boolean isUserFirst;
	private Scanner scan;
	private ArrayList<PokemonCard> myBench;
	private ArrayList<PokemonCard> AIBench;
	private ArrayList<PokemonCard> myActive;
	private ArrayList<PokemonCard> AIActive;
	
	public Game() {
		myDeck = new ArrayList<>();
		AIDeck = new ArrayList<>();
		myHand = new ArrayList<>();
		AIHand = new ArrayList<>();
		myPrizePile = new ArrayList<>();
		AIPrizePile = new ArrayList<>();
		myDiscard = new ArrayList<>();
		AIDiscard = new ArrayList<>();
		isUserFirst = true;
		scan = new Scanner(System.in);
		myBench = new ArrayList<>();
		AIBench = new ArrayList<>();
		myActive = new ArrayList<>();
		AIActive = new ArrayList<>();
	}
	
	public ArrayList<Card> getMyHand() {
        return myHand;
    }

    public ArrayList<Card> getAIHand() {
        return AIHand;
    }

    public ArrayList<Card> getMyDeck() {
        return myDeck;
    }

    public ArrayList<Card> getAIDeck() {
        return AIDeck;
    }
	
	
	
	public void setupGame() {
	    coinToss();
	    try {
	        Thread.sleep(2000); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    
	    makeMyDeck();
	    makeAIDeck();
	    
	    drawMyHand();
	    while (!validateMyHand()) {
	        System.out.println("You drew a mulligan. Shuffling your deck and drawing a new hand. Your opponent will draw another card");
	        
	        myDeck.addAll(myHand);
	        myHand.clear();
	        Collections.shuffle(myDeck);
	        drawMyHand();
	        
	        drawAICard();
	    }
	    drawAIHand();
	    while (!validateAIHand()) {
	        System.out.println("Your opponent drew a mulligan. They get a new hand, and you draw another card.");
	        
	        AIDeck.addAll(AIHand);
	        AIHand.clear();
	        Collections.shuffle(AIDeck);
	        drawAIHand();
	        drawMyCard();
	        
	        
	    }
	    
	    
	    makeMyPrize();
	    makeAIPrize();
	    displayMyHand();
	    chooseAIActive();
	}
	
	public void displayWinner() {
		if (AIActive.get(0).getHp() <= 0 || myPrizePile.isEmpty() || AIDeck.size() <= 0) {
			System.out.println("You win!");
		}else {
			System.out.println("Display");
		}
	}
	
	public boolean checkForWinner() {
		if(AIActive.get(0).getHp() <= 0 || myPrizePile.isEmpty() || AIPrizePile.isEmpty() || myDeck.size() <= 0 || AIDeck.size() <= 0){
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void loopGame() {
		boolean gameOver = false;
		while(!gameOver) {
			if(isUserFirst) {
				myTurn();
			} else {
				AITurn();
			}
			gameOver = checkForWinner();
			isUserFirst = !isUserFirst;
		}
		displayWinner();
	}
	
	public void myTurn() {
	    System.out.println("Your turn, drawing a card");
	    drawMyCard();
	    displayMyHand();
	    
	    boolean finished = false;
	    
	    while (!finished) {
	        System.out.println("Would you like to: 1. Bench a Pokemon\n2. Attach energy to a Pokemon\n3. Use a trainer card\n"
	                + "4. Switch your active pokemon with a benched one\n5. Attack with your active pokemon\n"
	                + "6. Retreat Pokemon\n7. Choose your active Pokemon\n8. Type 0 to end your turn\n(Type the number)");
	        int action = scan.nextInt();
	        
	        switch(action) {
	        case 1:
	            if(myBench.size() >= 5) {
	                System.out.println("Your bench is too full, please choose something else");
	            } else if (!validateMyHand()) {
	                System.out.println("You don't have any Pokemon cards in your hand to bench, please choose something else");
	            } else {
	                benchPokemon();
	            }
	            break;
	        case 2:
	            boolean hasEnergy = false;
	            for(Card card : myHand) {
	                if(card instanceof EnergyCard) {
	                    hasEnergy = true;
	                    break;
	                }
	            }
	            if(!hasEnergy) {
	                System.out.println("You don't have any energy in your hand, please choose something else");
	            } else if(myActive.isEmpty()) {
	                System.out.println("You don't have an active pokemon, please choose something else");
	            } else {
	                attachEnergy();
	            }
	            break;
	        case 3:
	            boolean hasTrainer = false;
	            for(Card card : myHand) {
	                if(card instanceof TrainerCard) {
	                    hasTrainer = true;
	                    break;
	                }
	            }
	            if(!hasTrainer) {
	                System.out.println("You don't have any trainer cards, please choose something else");
	            } else {
	                useTrainerCard();
	            }
	            break;
	        case 4:
	            if(!myBench.isEmpty()) {
	                switchActiveAndBenched();
	            } else {
	                System.out.println("You don't have Pokemon in your bench, please choose something else");
	            }
	            break;
	        case 5: 
	            if(!myActive.isEmpty()) {
	                attack();
	                finished = true;
	            } else {
	                System.out.println("You don't have an active Pokemon to attack with, please choose something else");
	            }
	            break;
	        case 6:
	            retreatPokemon();
	            break;
	        case 7:
	            chooseActivePokemon();
	            break;
	        case 0: 
	            finished = true;
	            System.out.println("Your turn is finished");
	            break;
	        default:
	            System.out.println("Invalid choice, please choose a valid number option");
	        }
	    }
	}
	public ArrayList<Card> findDifference(ArrayList<Card> listA, ArrayList<Card> listB) {
		ArrayList<Card> aMinusB = new ArrayList<>();
		//for every element in A, if the element is not in B and already not in the difference set, it is added to the difference of A - B
		for (Card elementA : listA) {
            if (!listB.contains(elementA) && !aMinusB.contains(elementA)) {
                aMinusB.add(elementA);
            }
        }
		return aMinusB;
		
		
	}
	
	
	public void AITurn() {
		System.out.println("Your opponent's turn, they are drawing a card");
		drawAICard();
		
		ArrayList<Card> updatedAIHand = new ArrayList<>();
		for(Card card : AIHand) {
			if (card instanceof PokemonCard && AIBench.size() < 5) {
				System.out.println("AI benched " + card.getName());
				AIBench.add((PokemonCard) card);
			}else {
				updatedAIHand.add(card);
			}
		}
		AIHand = updatedAIHand;
		
		
		
		
		ArrayList<TrainerCard> trainerCards = new ArrayList<>();
		for(Card card : AIHand) {
			if (card instanceof TrainerCard) {
				TrainerCard x = (TrainerCard)card;
				trainerCards.add(x);
				
				
				AIDiscard.add(card);
			}
		}
		
		
		
		AIHand = findDifference(AIHand, AIDiscard);
		for (TrainerCard card : trainerCards) {
			System.out.println("AI is playing " + card.getName());
			card.useForAI(this);
		}
		ArrayList<EnergyCard> energyCards = new ArrayList<>();
		for(Card card : AIHand) {
			if(card instanceof EnergyCard) {
				EnergyCard x = (EnergyCard)card;
				energyCards.add(x);
				AIDiscard.add(x);
			}
		}
		AIHand = findDifference(AIHand, AIDiscard);
		for (Card card : energyCards) {
			System.out.println("AI attached an energy to their active Pokemon");
			
		}
		PokemonCard activePokemon = AIActive.get(0);
		ArrayList<Attack> attacks = activePokemon.getAttacks();
		int damage = attacks.get(0).getDamage();
		Attack attack = new Attack(attacks.get(0).getName(), damage);
		if(!myActive.isEmpty()) {
			System.out.println("AI has attacked with " + activePokemon.getName() + " for " + damage + " damage");
			
			attack.applyDamage(myActive.get(0));
			System.out.println("Your active Pokemon has an HP of " + myActive.get(0).getHp());
		}
		
		
		
				
	}
	
	public void benchPokemon() {
	    // Display available Pokemon in hand
	    System.out.println("Select a Pokemon to bench:");
	    for (int i = 0; i < myHand.size(); i++) {
	        if (myHand.get(i) instanceof PokemonCard) {
	            PokemonCard p = (PokemonCard) myHand.get(i);
	            System.out.println(i + 1 + ": " + p.getName());
	        }
	    }

	    int choice = scan.nextInt() - 1;
	    if (choice >= 0 && choice < myHand.size() && myHand.get(choice) instanceof PokemonCard) {
	        PokemonCard chosenPokemon = (PokemonCard) myHand.get(choice);
	        
	        // Add chosen Pokemon to the bench
	        myBench.add(chosenPokemon);
	        myHand.remove(choice);
	        System.out.println(chosenPokemon.getName() + " has been benched.");
	        displayMyHand();
	    } else {
	        System.out.println("Invalid choice. Please try again.");
	    }
	}

	
	public void attachEnergy() {
	    // Display energy cards in hand
	    System.out.println("Select an energy card to attach to your active Pokémon:");
	    ArrayList<EnergyCard> energyCards = new ArrayList<>();
	    for (Card card : myHand) {
	        if (card instanceof EnergyCard) {
	            energyCards.add((EnergyCard) card);
	            System.out.println(energyCards.size() + ": " + card.getName());
	        }
	    }

	    if (energyCards.isEmpty() || myActive.isEmpty()) {
	        System.out.println("No energy cards or no active Pokémon to attach to.");
	        return;
	    }

	    // Ask the user to choose an energy card to attach
	    int choice = scan.nextInt() - 1;
	    if (choice >= 0 && choice < energyCards.size()) {
	        EnergyCard chosenEnergy = energyCards.get(choice);
	        PokemonCard activePokemon = myActive.get(0); // Assuming only one active Pokémon

	        // Attach the energy to the active Pokémon
	        System.out.println("Attaching " + chosenEnergy.getName() + " to " + activePokemon.getName());
	        activePokemon.addEnergyCard(chosenEnergy);  // This method should update activePokemon's energy pool
	        myHand.remove(chosenEnergy);

	        // Display updated hand
	        displayMyHand();  // Show updated hand
	    } else {
	        System.out.println("Invalid choice. Please try again.");
	    }
	}

	
	public void useTrainerCard() {
	    while (true) {
	        System.out.println("Choose a Trainer card to use:");

	        // Display available trainer cards in the player's hand
	        displayTrainerCardsInHand();

	        if (myHand.isEmpty()) {
	            System.out.println("No Trainer cards in your hand.");
	            return; // Exit if there are no Trainer cards left
	        }

	        // Ask the player to choose a Trainer card
	        int selectedCardIndex = getCardSelectionFromPlayer();

	        // If the player selects an invalid index, ask again
	        if (selectedCardIndex < 0 || selectedCardIndex >= myHand.size() || !(myHand.get(selectedCardIndex) instanceof TrainerCard)) {
	            System.out.println("Invalid selection. Please choose a valid card.");
	            continue; // Restart the loop if the input is invalid
	        }
	        
	        
	        TrainerCard chosenCard = (TrainerCard) myHand.get(selectedCardIndex);
	        System.out.println("Using Trainer card: " + chosenCard.getName());
	        
	         chosenCard.useForMe(this);  // Use the trainer card, passing the current game as context

	        // Optionally, remove the Trainer card from the hand after usage
	        myHand.remove(selectedCardIndex);  
	        displayMyHand();  // Display the updated hand
	        break;
	    }
	}


	// Helper method to display the trainer cards in hand
	public void displayTrainerCardsInHand() {
	    if (myHand.isEmpty()) {
	        System.out.println("No Trainer cards in hand.");
	        return;
	    }

	    System.out.println("Your hand contains the following Trainer cards:");

	    // Iterate through the cards in hand
	    for (int i = 0; i < myHand.size(); i++) {
	        // Check if the card is an instance of TrainerCard
	        if (myHand.get(i) instanceof TrainerCard) {
	            TrainerCard trainerCard = (TrainerCard) myHand.get(i); // Cast the card to TrainerCard
	            System.out.println((i + 1) + ": " + trainerCard.getName());
	        }
	    }
	    System.out.println();
	}

	// Helper method to get the card index from the player (e.g., using Scanner)
	private int getCardSelectionFromPlayer() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter the number of the Trainer card to use: ");
	    return scanner.nextInt() - 1;  // Convert to 0-based index
	}



	public void switchActiveAndBenched() {
	    if (myBench.isEmpty()) {
	        System.out.println("No Pokémon in your bench.");
	        return;
	    }

	    // Display bench Pokemon
	    System.out.println("Select a Pokémon from your bench to switch with your active Pokémon:");
	    for (int i = 0; i < myBench.size(); i++) {
	        System.out.println((i + 1) + ": " + myBench.get(i).getName());
	    }

	    int choice = scan.nextInt() - 1;
	    if (choice >= 0 && choice < myBench.size()) {
	        PokemonCard chosenPokemon = myBench.get(choice);

	        // Switch active and benched Pokemon
	        if (!myActive.isEmpty()) {
	            myBench.add(myActive.get(0));  // Move the current active Pokémon to the bench
	        }
	        myActive.clear();  // Clear the active Pokemon slot
	        myActive.add(chosenPokemon);  // Set the chosen bench Pokemon as active
	        myBench.remove(choice);  // Remove the Pokemon from the bench

	        System.out.println(chosenPokemon.getName() + " is now your active Pokémon.");
	        displayMyHand();
	    } else {
	        System.out.println("Invalid choice. Please try again.");
	    }
	}

	public void attack() {
	    // Check if there is an active Pokemon
	    if (myActive.isEmpty()) {
	        System.out.println("You don't have an active Pokémon to attack with.");
	        return;
	    }

	    // Choose the active Pokemon
	    PokemonCard activePokemon = myActive.get(0);  // Assumes there's only one active Pokemon

	    // Display the attacks of the active Pokemon
	    System.out.println("Choose an attack for " + activePokemon.getName() + ":");
	    ArrayList<Attack> attacks = activePokemon.getAttacks();
	    for (int i = 0; i < attacks.size(); i++) {
	        System.out.println(i + ": " + attacks.get(i).toString());
	    }

	    // Get the attack choice from the player
	    int attackChoice = scan.nextInt();
	    if (attackChoice < 0 || attackChoice >= attacks.size()) {
	        System.out.println("Invalid attack choice.");
	        return;
	    }

	    Attack selectedAttack = attacks.get(attackChoice);

	    // Check if the player has enough energy of the correct type to perform the attack
	    
	    

	    

	    // Use the energy cards and apply damage
	    // Remove the energy cards from the hand (simplified here)
	    int energyUsed = 0;
	    for (int i = 0; i < myHand.size() && energyUsed < selectedAttack.getEnergyCost(); i++) {
	        if (myHand.get(i) instanceof EnergyCard && ((EnergyCard) myHand.get(i)).getEnergyType().equals(selectedAttack.getEnergyCostType())) {
	            myHand.remove(i);  // Remove the energy card from hand
	            energyUsed++;
	            i--;  // Adjust the index due to removal
	        }
	    }

	    // Apply the attack to the opponent's active Pokemon
	    if (!AIActive.isEmpty()) {
	        PokemonCard opponentPokemon = AIActive.get(0);
	        selectedAttack.applyDamage(opponentPokemon);

	        // Check if the opponent's Pokemon fainted
	        if (opponentPokemon.getHp() <= 0) {
	            System.out.println(opponentPokemon.getName() + " has fainted!");
	            // Handle the fainting (e.g., move to the next prize card, switch active Pokemon, etc.)
	        }
	    }

	    // End of turn message
	    System.out.println(activePokemon.getName() + " used " + selectedAttack.getName() + "!");
	}


	public void retreatPokemon() {
	    if (myActive.isEmpty()) {
	        System.out.println("No active Pokémon to retreat.");
	        return;
	    }

	    PokemonCard activePokemon = myActive.get(0);
	    if (activePokemon.getRetreatCost() > 0) {
	        // Check if the user has enough energy to retreat
	        System.out.println("Retreat cost is " + activePokemon.getRetreatCost() + " energy.");
	        boolean hasEnergyForRetreat = false;

	        // Check for sufficient energy
	        for (Card card : myHand) {
	            if (card instanceof EnergyCard) {
	                EnergyCard energy = (EnergyCard) card;
	                if (energy.getEnergyType().equals(activePokemon.getType())) {
	                    hasEnergyForRetreat = true;
	                    break;
	                }
	            }
	        }

	        if (hasEnergyForRetreat) {
	            System.out.println("Retreating Pokémon...");
	            // Move the active Pokemon to the bench and switch it
	            myBench.add(activePokemon);
	            myActive.clear();
	            // Move a new Pokemon from the bench to the active position
	            switchActiveAndBenched();  // Reuse your switchActiveAndBenched logic
	        } else {
	            System.out.println("Not enough energy to retreat.");
	        }
	    } else {
	        System.out.println("No retreat cost for your active Pokémon.");
	    }
	}

	
	public void chooseActivePokemon() {
	    // Check if the player has any Pokemon on the bench
	    if (myBench.isEmpty()) {
	        System.out.println("You have no Pokémon on the bench to make active.");
	        return;
	    }

	    // Display the Pokemon on the bench
	    System.out.println("Choose a Pokémon from your bench to make active:");
	    for (int i = 0; i < myBench.size(); i++) {
	        PokemonCard pokemon = myBench.get(i);
	        System.out.println((i + 1) + ". " + pokemon.getName() + " - HP: " + pokemon.getHp());
	    }

	    // Let the player choose a Pokemon from the bench
	    int choice = scan.nextInt();
	    if (choice < 1 || choice > myBench.size()) {
	        System.out.println("Invalid choice, please select a valid Pokémon.");
	        return;
	    }

	    // Remove the chosen Pokemon from the bench and set it as the active Pokémon
	    PokemonCard chosenPokemon = myBench.get(choice - 1);
	    myActive.clear(); // Clear current active Pokemon (if any)
	    myActive.add(chosenPokemon);

	    // Remove the chosen Pokemon from the bench
	    myBench.remove(chosenPokemon);

	    System.out.println(chosenPokemon.getName() + " is now your active Pokémon!");
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void chooseAIActive() {
		ArrayList<PokemonCard> possibleActivePokemon = new ArrayList<>();
		for(Card card : AIHand) {
			if(card instanceof PokemonCard) {
				possibleActivePokemon.add((PokemonCard) card);
			}
		}
		if(possibleActivePokemon.isEmpty()) {
			System.out.println("Your opponent has no Pokemon to make active");
			return;
		} 
		PokemonCard bestPokemon = possibleActivePokemon.get(0);
		for (PokemonCard pokemon : possibleActivePokemon) {
			if(pokemon.getHp() > bestPokemon.getHp()) {
				bestPokemon = pokemon;
			}
		}
		AIActive.clear();
		AIActive.add(bestPokemon);
		AIHand.remove(bestPokemon);
		System.out.println("Your opponent's active pokemon: " + bestPokemon.getName() + "   HP: " + bestPokemon.getHp());
	}
	
	
	public void makeMyDeck() {
		// Charmander
		ArrayList<Attack> charmanderAttacks = new ArrayList<>();
		charmanderAttacks.add(new Attack(1, "Fire", "Tail on Fire", 10));
		charmanderAttacks.add(new Attack(1, "Fire", "Ember", 30));

		// Moltres
		ArrayList<Attack> moltresAttacks = new ArrayList<>();
		moltresAttacks.add(new Attack(1, "Fire", "Inferno Wings", 20));
		moltresAttacks.add(new Attack(1, "Fire", "Assisting Heater", 30));

		// Squirtle
		ArrayList<Attack> squirtleAttacks = new ArrayList<>();
		squirtleAttacks.add(new Attack(1, "Water", "Bubble", 10));
		squirtleAttacks.add(new Attack(2, "Water", "Water Gun", 20));

		// Poliwhirl
		ArrayList<Attack> poliwhirlAttacks = new ArrayList<>();
		poliwhirlAttacks.add(new Attack(1, "Water", "Rain Splash", 20));
		poliwhirlAttacks.add(new Attack(2, "Water", "Bubble", 20));

		// Bulbasaur
		ArrayList<Attack> bulbasaurAttacks = new ArrayList<>();
		bulbasaurAttacks.add(new Attack(1, "Grass", "Vine Whip", 10));
		bulbasaurAttacks.add(new Attack(1, "Grass", "Tackle", 10));

		// Oddish
		ArrayList<Attack> oddishAttacks = new ArrayList<>();
		oddishAttacks.add(new Attack(1, "Grass", "Stun Spore", 10));
		oddishAttacks.add(new Attack(2, "Grass", "Hook", 10));

		// Pikachu
		ArrayList<Attack> pikachuAttacks = new ArrayList<>();
		pikachuAttacks.add(new Attack(1, "Electric", "Gnaw", 10));
		pikachuAttacks.add(new Attack(2, "Electric", "Pika Strike", 20));

		// Raichu
		ArrayList<Attack> raichuAttacks = new ArrayList<>();
		raichuAttacks.add(new Attack(1, "Electric", "Pain-Full Punch", 40));
		raichuAttacks.add(new Attack(1, "Electric", "Ambushing Spark", 40));


	    // Now add each card with its own unique attributes
	    myDeck.add(new FirePokemonCard("Charmander", 60, charmanderAttacks, "Water", 1));
	    myDeck.add(new FirePokemonCard("Moltres", 120, moltresAttacks, "Water", 1));
	    myDeck.add(new WaterPokemonCard("Squirtle", 70, squirtleAttacks, "Grass", 1));
	    myDeck.add(new WaterPokemonCard("Poliwhirl", 90, poliwhirlAttacks, "Electric", 1));
	    myDeck.add(new GrassPokemonCard("Bulbasaur", 70, bulbasaurAttacks, "Fire", 2));
	    myDeck.add(new GrassPokemonCard("Oddish", 50, oddishAttacks, "Fire", 1));
	    myDeck.add(new ElectricPokemonCard("Pikachu", 70, pikachuAttacks, "None", 1));
	    myDeck.add(new ElectricPokemonCard("Raichu", 80, raichuAttacks, "None", 1));
	    myDeck.add(new FirePokemonCard("Charmander", 60, charmanderAttacks, "Water", 1));
	    myDeck.add(new FirePokemonCard("Moltres", 120, moltresAttacks, "Water", 1));
	    myDeck.add(new WaterPokemonCard("Squirtle", 70, squirtleAttacks, "Grass", 1));
	    myDeck.add(new WaterPokemonCard("Poliwhirl", 90, poliwhirlAttacks, "Electric", 1));
	    myDeck.add(new GrassPokemonCard("Bulbasaur", 70, bulbasaurAttacks, "Fire", 2));
	    myDeck.add(new GrassPokemonCard("Oddish", 50, oddishAttacks, "Fire", 1));
	    myDeck.add(new ElectricPokemonCard("Pikachu", 70, pikachuAttacks, "None", 1));
	    myDeck.add(new ElectricPokemonCard("Raichu", 80, raichuAttacks, "None", 1));

	    // Energy Cards
	    for (int i = 0; i < 3; i++) {
	        myDeck.add(new EnergyCard("Fire Energy", "Fire"));
	    }
	    for (int i = 0; i < 4; i++) {
	        myDeck.add(new EnergyCard("Water Energy", "Water"));
	        myDeck.add(new EnergyCard("Grass Energy", "Grass"));
	        myDeck.add(new EnergyCard("Electric Energy", "Electric"));
	    }

	    // Trainer Cards
	    for (int i = 0; i < 5; i++) {
	        myDeck.add(new Sophie("Sophie: Look in your deck for the first Pokemon card and add to hand"));
	    }
	    for (int i = 0; i < 5; i++) {
	        myDeck.add(new ProfessorsResearch("Professor's Research: Discard your hand and draw 7 cards"));
	    }
	    for (int i = 0; i < 5; i++) {
	        myDeck.add(new Hop("Hop: Draw 3 cards"));
	    }
	    for (int i = 0; i < 5; i++) {
	        myDeck.add(new Bill("Bill: Draw 2 cards"));
	    }
	    for (int i = 0; i < 5; i++) {
	        myDeck.add(new Alex("Alex: Discard a card from your hand and draw 2 more"));
	    }
	    for (int i = 0; i < 4; i++) {
	        myDeck.add(new Lillie("Lillie: Draw cards until you have 6"));
	    }
	    Collections.shuffle(myDeck);
	}

	
	public void makeAIDeck() {
		AIDeck = myDeck;
		Collections.shuffle(AIDeck);
	}
	
	public void drawMyHand() {
		if (myDeck.size() >= 7) {
	        for (int i = 0; i < 7; i++) {
	            myHand.add(myDeck.get(0)); 
	            myDeck.remove(0);
	        }
	    } else {
	    	System.out.println("There are not enough cards in the deck to draw a new hand.");
	    }
    }
	
	public boolean validateMyHand() {
		for(Card card : myHand) {
			if(card instanceof PokemonCard) {
				return true;
			}
		}
		return false;
	}
	
	public void drawAIHand() {
		AIHand.clear(); 
		if (AIDeck.size() >= 7) {
	        for (int i = 0; i < 7; i++) {
	            AIHand.add(AIDeck.get(0)); 
	            AIDeck.remove(0);
	        }
	    } else {
	    	System.out.println("There are not enough cards in the deck to draw a new hand.");
	    }
	}
	
	public boolean validateAIHand() {
		for(Card card : AIHand) {
			if(card instanceof PokemonCard) {
				return true;
			}
		}
		return false;
	}
	
	public void makeMyPrize() {
		if (myDeck.size() >= 6) {
	        for (int i = 0; i < 6; i++) {
	            myPrizePile.add(myDeck.get(0)); 
	            myDeck.remove(0);
	        }
	    } else {
	    	System.out.println("There are not enough cards in the deck to make a new prize pile.");
	    }
	}
	
	public void makeAIPrize() {
		if (AIDeck.size() >= 6) {
	        for (int i = 0; i < 6; i++) {
	            AIPrizePile.add(AIDeck.get(0)); 
	            AIDeck.remove(0);
	        }
	    } else {
	    	System.out.println("There are not enough cards in the deck to make a new prize pile.");
	    }
	}
	
	public void displayMyHand() {
	    System.out.println("Your hand contains the following cards:\n");

	    for (Card card : myHand) {
	        if (card instanceof PokemonCard) {
	            PokemonCard pokemonCard = (PokemonCard) card;
	            System.out.println("Pokémon Card: " + pokemonCard.getName());
	            System.out.println("  HP: " + pokemonCard.getHp());
	            System.out.println("  Type: " + pokemonCard.getType());
	            System.out.println("  Weakness: " + pokemonCard.getWeakness());
	            System.out.println("  Retreat Cost: " + pokemonCard.getRetreatCost() + " energy");
	            System.out.println("  Attacks:");

	            for (Attack attack: pokemonCard.getAttacks()) {
	                System.out.println(attack.toString());
	            }
	            System.out.println();
	        } 
	        else if (card instanceof EnergyCard) {
	            EnergyCard energyCard = (EnergyCard) card;
	            System.out.println("Energy Card: " + energyCard.getName());
	            System.out.println();
	        } 
	        else if (card instanceof TrainerCard) {
	            TrainerCard trainerCard = (TrainerCard) card;
	            System.out.println("Trainer Card: " + trainerCard.getName());
	            System.out.println();
	        }
	    }

	    System.out.println();
	}

	
	public void coinToss() {
        String userChoice = "";
        while (!userChoice.equals("heads") && !userChoice.equals("tails")) {
            System.out.println("Choose heads or tails");
            userChoice = scan.nextLine().toLowerCase();

            if (!userChoice.equals("heads") && !userChoice.equals("tails")) {
                System.out.println("That input is invalid, please enter heads or tails");
            }
        }

        String result = (Math.random() < 0.5) ? "heads" : "tails";

        System.out.println("It was " + result);
        
        if (userChoice.equals(result)) {
            String userDecision = "";
            while (!userDecision.equals("first") && !userDecision.equals("second")) {
                System.out.println("You won the coin toss. Do you want to go first or second");
                userDecision = scan.nextLine().toLowerCase();

                if (!userDecision.equals("first") && !userDecision.equals("second")) {
                    System.out.println("That input is invalid, please enter first or second");
                }
            }

            if (userDecision.equals("first")) {
                isUserFirst = true;
                System.out.println("You will go first");
            } else {
                isUserFirst = false;
                System.out.println("You will go second");
            }
        } else {
            isUserFirst = false;
            System.out.println("You lost the coin toss, so your opponent will go first");
        }
    }
	
	public void drawMyCard() {
	    if (myDeck.size() > 0) {
	        myHand.add(myDeck.get(0)); 
	        myDeck.remove(0);
	    } else {
	        System.out.println("There are no more cards in your deck");
	    }
	}

	public void drawAICard() {
	    if (AIDeck.size() > 0) {
	        AIHand.add(AIDeck.get(0)); 
	        AIDeck.remove(0);
	    } else {
	        System.out.println("There are no more cards in the opponent's deck");
	    }
	}


    
	
}

