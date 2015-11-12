package serialization;

import java.io.Serializable;

public class PrimitiveObject implements Serializable {
	
	int numberOne;
	int numberTwo;
	int numberThree;
	int[] numberArray = new int[3];
	
	public PrimitiveObject(int numberOne, int numberTwo, int numberThree){
		
		this.numberOne = numberOne;
		this.numberTwo = numberTwo;
		this.numberThree = numberThree;
		
		numberArray[0] = this.numberOne;
		numberArray[1] = this.numberTwo;
		numberArray[2] = this.numberThree;
		
	}
	
	public String toString(){
		return "arr[0]: " + numberArray[0] + " arr[1]: " + numberArray[1] + " arr[2]: " + numberArray[2];
		
	}

}
