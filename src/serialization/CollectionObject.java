package serialization;

import java.io.Serializable;
import java.util.*;

public class CollectionObject implements Serializable {
	
	int numberOne;
	int numberTwo;
	int numberThree;
	List<Integer> firstList = new ArrayList<Integer>();
	
	public CollectionObject(){
		
	}
	
	public CollectionObject(int numberOne, int numberTwo, int numberThree){
		
		this.numberOne = numberOne;
		this.numberTwo = numberTwo;
		this.numberThree = numberThree;
		Collections.addAll(firstList, numberOne, numberTwo, numberThree);
		Collections.sort(firstList);
	
	}
	
	public List<Integer> getList(){
		
		return firstList;
	}
	
	

}
