package serialization;

import java.util.Scanner;

public class ObjectCreator {
	
	public static void main (String [] args){
		
		Interface();
	}
	
	public static void Interface(){
		Scanner in = new Scanner(System.in);
		String input;
		boolean done = false;
		while (done  != true){
			System.out.println("What type of Object would you like to create: ");
			System.out.println("(s)imple Object");
			System.out.println("Object with a reference to other (o)bjects");
			System.out.println("Object with array of (p)rimitives");
			System.out.println("Object with array of (r)efernces");
			System.out.println("Object using (c)ollection instance");
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
				System.out.println("Program Done");
				break;
			default:
				System.out.println("invalid input");
			}
		}
		
	}
	
	public static void SimpleObject(){
		//Object obj = new Object();
		System.out.println("in s");
	}
	
	public static void ObjectRef(){
		System.out.println("in o");
	}
	
	public static void ObjectArrayPrim(){
		System.out.println("in p");
	}
	
	public static void ObjectArrayRef(){
		System.out.println("in r");
	}
	
	public static void ObjectColl(){
		System.out.println("in c");
	}

}
