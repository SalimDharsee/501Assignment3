package serialization;

import java.io.Serializable;

public class ObjectReference implements Serializable {
	
	int someNumber;
	SimpleObject anObject;
	
	public ObjectReference(){
		
	}
	
	public ObjectReference(int someNumber){
		
		this.someNumber = someNumber *2;
		anObject = new SimpleObject(this.someNumber);
		this.someNumber = anObject.getNumber();
		
	}
	
	public String toString(){
		return "Doubled + Incremented Number: " + someNumber;
	}

}
