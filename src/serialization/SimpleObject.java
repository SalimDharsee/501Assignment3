package serialization;

import java.io.Serializable;

public class SimpleObject implements Serializable  {
	
	int someNumber;
	
	
	public SimpleObject(int someNumber){
		this.someNumber = someNumber + 1;
	
	}
	
	public String toString(){
		return "Incremented Number: " + someNumber;
	}

}
