import java.util.ArrayList;
import java.util.Arrays;
public class SetOperationsTester {

	public static void main(String[] args) {
		Operations x = new Operations();
		ArrayList<Integer> listA = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		ArrayList<Integer> listB = new ArrayList<>(Arrays.asList(4, 5, 6 ));
		ArrayList<Integer> universalList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
		System.out.println("The sets are not disjoint\n\n");
		x.checkIfDisjoint(listA, listB);
		
		
		
		
		
		
		
		
		//x.unionSets(listA, listB);
		//x.intersectSets(listA, listB);
		//universalList must be a powerset of listA
		//x.findComplement(universalList, listA);
		//will output the difference going in both directions
		//x.findDifference(listA, listB);
		//x.findSymmetricDifference(listA, listB);
		//x.findCrossProduct(listA, listB);
		//x.getPowerSet(listA);
		//is listA a subset of listB?
		//x.checkIfSubset(listA, listB);
		//is listA a proper subset of listB?
		//x.checkIfProperSubset(listA, listB);
		//are listA and listB disjoint?
		//x.checkIfDisjoint(listA, listB);

	}

}
