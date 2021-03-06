package serialization;

import java.util.List;
import java.util.Scanner;

public class ObjectCreator {
	
	/*
	 * This class is repsonsible for creating the objects and passing them to be serealized once they are 
	 * created. Each object class has its own method that is called when created. 
	 * Their constructor will either take 1 or 3 arguments based on the object type.
	 */
	
	public static void main (String [] args){
		
		
		Interface();
	}
	
	static Serializer demo = new Serializer();
	
	public static void Interface(){
		Scanner in = new Scanner(System.in);
		String input;
		boolean done = false;
		while (done  != true){
			System.out.println("What type of Object would you like to create: ");
			System.out.println("(s)imple Object (provide number to be incremented)");
			System.out.println("Object with a reference to other (o)bjects (provide number to be double and incremented)");
			System.out.println("Object with array of (p)rimitives (provide 3 numbers)");
			System.out.println("Object with array of (r)efernces (provide 3 numbers which get incremented)");
			System.out.println("Object using (c)ollection instance (provide 3 numbers which are added to a list, list is sorted in ascending order)");
			System.out.print("INPUT: ");
			input = in.nextLine();
		
			switch(input){
			case("s"):
				SimpleObject();
				break;
			case("o"):
				ObjectRef();
				break;
			case("p"):
				ObjectArrayPrim();
				break;
			case("r"):
				ObjectArrayRef();
				break;
			case("c"):
				ObjectColl();
				break;
			case("d"):
				done = true;
				ClientEmulator aClient = new ClientEmulator();
				aClient.transfer("192.168.1.41", "12345", "src//serialization//sero.xml");
				break;
			default:
				System.out.println("invalid input");
			}
			
			System.out.println("");
		}
		
	}
	
	public static void SimpleObject(){
		int input;
		Scanner in = new Scanner(System.in);
		//System.out.println("in s");
		System.out.println("Please choose any whole number: ");
		System.out.print("INPUT: ");
		input = in.nextInt();
		
		SimpleObject anObject = new SimpleObject(input);
		
		demo.serialize(anObject);
		
		
	}
	
	public static void ObjectRef(){
		int input;
		Scanner in = new Scanner(System.in);
		//System.out.println("in o");
		System.out.println("Please choose any whole number: ");
		System.out.print("INPUT: ");
		input = in.nextInt();
		
		ObjectReference anObject = new ObjectReference(input);
	

	demo.serialize(anObject);
		
	}
	
	public static void ObjectArrayPrim(){
		int input1;
		int input2;
		int input3;
		Scanner in = new Scanner(System.in);
		//System.out.println("in p");
		System.out.println("Please provide three integers: ");
		System.out.print("First Number: ");
		input1 = in.nextInt();
		System.out.print("Second Number: ");
		input2 = in.nextInt();
		System.out.print("Third Number: ");
		input3 = in.nextInt();
		
		PrimitiveObject anObject = new PrimitiveObject(input1, input2, input3);
		
		
		demo.serialize(anObject);
		
	}
	
	public static void ObjectArrayRef(){
		int input1;
		int input2;
		int input3;
		Scanner in = new Scanner(System.in);
		//System.out.println("in r");
		System.out.println("Please provide three integers: ");
		System.out.print("First Number: ");
		input1 = in.nextInt();
		System.out.print("Second Number: ");
		input2 = in.nextInt();
		System.out.print("Third Number: ");
		input3 = in.nextInt();
		
		ArrayRefObject anObject = new ArrayRefObject(input1, input2, input3);
		
		demo.serialize(anObject);
	}
	
	public static void ObjectColl(){
		int input1;
		int input2;
		int input3;
		Scanner in = new Scanner(System.in);
		//System.out.println("in c");
		System.out.println("Please provide three integers: ");
		System.out.print("First Number: ");
		input1 = in.nextInt();
		System.out.print("Second Number: ");
		input2 = in.nextInt();
		System.out.print("Third Number: ");
		input3 = in.nextInt();
		
		CollectionObject anObject = new CollectionObject(input1, input2, input3);
		demo.serialize(anObject);
	
		
	}

}
