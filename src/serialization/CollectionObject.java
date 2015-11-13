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
		
		
		SimpleObject anObject = new SimpleObject(numberOne);
		SimpleObject anObject2 = new SimpleObject(numberTwo);
		SimpleObject anObject3 = new SimpleObject(numberThree);
		Collections.addAll(firstList, numberOne, numberTwo, numberThree);
		this.numberOne = anObject.getNumber();
		this.numberTwo = anObject2.getNumber();
		this.numberThree = anObject3.getNumber();
		
		Collections.addAll(firstList, this.numberOne, this.numberTwo, this.numberThree);
		Collections.sort(firstList);
	
	}
	
	public List<Integer> getList(){
		
		return firstList;
	}
	
	

}
