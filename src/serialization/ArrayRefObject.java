package serialization;

import java.io.Serializable;

public class ArrayRefObject implements Serializable {
	
	SimpleObject[] refArray = new SimpleObject[3];
	int numberOne;
	int numberTwo;
	int numberThree;
	
	public ArrayRefObject(){
		
	}
	
	ArrayRefObject(int numberOne, int numberTwo, int numberThree){
		
		this.numberOne = numberOne;
		this.numberTwo = numberTwo;
		this.numberThree = numberThree;
		
		refArray[0] = new SimpleObject(numberOne);
		refArray[1] = new SimpleObject(numberTwo);
		refArray[2] = new SimpleObject(numberThree);
		
	}
	
	public SimpleObject[] getArray(){
		return refArray;
	}
	
	public String toString(){
		return "arr[0]: " + refArray[0] + " arr[1]: " + refArray[1] + " arr[2]: " + refArray[2];
		
	}

}
