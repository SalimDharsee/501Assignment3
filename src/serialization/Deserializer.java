package serialization;

import java.beans.XMLDecoder;
import java.io.*;
import org.jdom.*;
public class Deserializer {
	
	public ObjectReference deserialize(org.jdom.Document document){
		
		ObjectReference anObject = null;
		
		try (XMLDecoder x = new XMLDecoder(new BufferedInputStream(new FileInputStream(document)))){
			
			System.out.println("DESERIALIZED");
			anObject = (ObjectReference)x.readObject();
			
		}catch(IOException ex){
			
			System.out.println("problem occured during deserialization");
			System.out.println(ex.getMessage());
			
		}
		
		return anObject;
		
	}

}
