import java.util.ArrayList;
import java.util.Arrays;
public class Operations {
	public Operations() {
		
	}
	//union of 2 sets
	public void unionSets(ArrayList<Integer> listA, ArrayList<Integer> listB ) {
		ArrayList<Integer> union = new ArrayList<>();
		//for every element in list A, we are adding the element in the union set if it isn't already in there
		for (int elementA : listA) {
			if(!union.contains(elementA)) {
				union.add(elementA);
			}
		}
		//for every element in list B, we are adding the element in the union set if it isn't already there
		for (int elementB : listB) {
			if(!union.contains(elementB)) {
				union.add(elementB);
			}
		}
		//output
		System.out.println("The union of set A and set B is " + union);
	}
	
	//intersecting 2 sets
	public void intersectSets(ArrayList<Integer> listA, ArrayList<Integer> listB) {
		ArrayList<Integer> intersection = new ArrayList<>();
		//for every element in list A, if that element is in list B and not already in the intersection set, then it is added to the intersection
		for(int elementA : listA) {
			if(listB.contains(elementA) && (!intersection.contains(elementA))) {
				intersection.add(elementA);
			}
		}
		//output
		System.out.println("The intersection of set A and set B is " + intersection);
	}
	
	//finding the complement of a set given a universal set
	public void findComplement(ArrayList<Integer> universalList, ArrayList<Integer> listA) {
		boolean isSubset = true;
		//checking if A is a subset of the universal set
		for(int elementA : listA) {
			if(!universalList.contains(elementA)) {
				isSubset = false;
				break;
			}
		}
		ArrayList<Integer> complement = new ArrayList<>();
		//if A is a subset, then for every element in the universal list, if the element is not in A and already not in the complement set, it is
		//added to the complement
		if(isSubset) {
			for(int elementU : universalList) {
				if(!listA.contains(elementU) && (!complement.contains(elementU))) {
					complement.add(elementU);
				}
			}
		}
		//output
		System.out.println("The complement of set A is " + complement);
	}
	
	//finding all of the elements in one list that is not in the other
	public void findDifference(ArrayList<Integer> listA, ArrayList<Integer> listB) {
		ArrayList<Integer> aMinusB = new ArrayList<>();
		//for every element in A, if the element is not in B and already not in the difference set, it is added to the difference of A - B
		for (int elementA : listA) {
            if (!listB.contains(elementA) && !aMinusB.contains(elementA)) {
                aMinusB.add(elementA);
            }
        }
		ArrayList<Integer> bMinusA = new ArrayList<>();
		//for every element in B, if the element is not in A ad already not in the difference set, it is added to the difference of B - A
		for (int elementB : listB) {
            if (!listA.contains(elementB) && !bMinusA.contains(elementB)) {
                bMinusA.add(elementB);
            }
        }
		//outputs 
		System.out.println("The difference of set A minus set B is " + aMinusB);
		System.out.println("The difference of set B minus set A is " + bMinusA);
	}
	
	
	//finding the set of elements in A and B but not in both
	public void findSymmetricDifference(ArrayList<Integer> listA, ArrayList<Integer> listB) {
		ArrayList<Integer> symmetricDifference = new ArrayList<>();
		//for every element in A, if the element is not in B and not already in the symmetric difference set, it is added to the symmetric difference
		//set
		for(int elementA : listA) {
			if(!listB.contains(elementA) && !symmetricDifference.contains(elementA)) {
				symmetricDifference.add(elementA);
			}
		}
		//for every element int B, if the element is not in A and not already in the symmetric difference set, it is added to the symmetric difference 
		//set
		for(int elementB : listB) {
			if(!listA.contains(elementB) && !symmetricDifference.contains(elementB)) {
				symmetricDifference.add(elementB);
			}
		}
		//output
		System.out.println("The symmetric difference of set A and set B is " + symmetricDifference);
	}
	
	//Cartesian product of 2 sets
	public void findCrossProduct(ArrayList<Integer> listA, ArrayList<Integer> listB) {
		ArrayList<String> crossProduct = new ArrayList<>();
		//traversing through every combination of one elements from A and one element from B and making them an ordered pair in the cross Product set
		for(int elementA : listA) {
			for(int elementB : listB) {
				crossProduct.add("(" + elementA + ", " + elementB + ")");
			}
		}
		//output
		System.out.println("The cross product of set A and set B is " + crossProduct);
	}
	
	//finding all possible subsets of a set
	public void getPowerSet(ArrayList<Integer> listA) {
        ArrayList<ArrayList<Integer>> powerSet = new ArrayList<>();
        //adding the empty set 
        powerSet.add(new ArrayList<>());
        //iterating the cardinality of A times
        for (int i = 0; i < listA.size(); i++) {
        	//the current element of the iteration will be added to all currently existing subsets
            int element = listA.get(i);
            int cardinality = powerSet.size();
            //adds the current element to all existing subsets of A
            for (int j = 0; j < cardinality; j++) {
            	//create a copy of the subset
                ArrayList<Integer> subset = new ArrayList<>(powerSet.get(j));
                //add the current element
                subset.add(element);
                //add the subset to the power set
                powerSet.add(subset);
            }
        }
        //output
        System.out.println("The power set of set A is " + powerSet);
    }
	
	//checks if the first set is a subset of the second
	public void checkIfSubset(ArrayList<Integer> possibleSubset, ArrayList<Integer> possibleSuperset) {
		//seeing if every element in the first set is in the second
		if(possibleSuperset.containsAll(possibleSubset)) {
			//output if true
			System.out.println("The first list is a subset of the second list");
		}else {
			//output if false
			System.out.println("The first list is not a subset of the second list");
		}
		
	}
	//checks if the first set is a subset of the second, but the second has at least one element not in the first set
	public void checkIfProperSubset(ArrayList<Integer> possibleSubset, ArrayList<Integer> possibleSuperset) {
		//seeing if every element in the first set is in the second set and the second set is larger than the first
		if(possibleSuperset.containsAll(possibleSubset) && possibleSuperset.size() > possibleSubset.size()) {
			//output if true
			System.out.println("The first list is a proper subset of the second list");
		}else {
			//output if false
			System.out.println("The first list is not a proper subset of the second list");
		}
		
	}
	//checks if there is no intersection between 2 sets
	public void checkIfDisjoint(ArrayList<Integer> listA, ArrayList<Integer> listB) {
		//for every element in A, if at least one element is in B, then they are not disjoint
		for(int elementA : listA) {
			if(listB.contains(elementA)) {
				System.out.println("The sets are not disjoint");
				break;
			} else {
				System.out.println("The sets are disjoint");
				break;
			}
		}
	}

}

