package serialization;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import org.jdom.*;

public class Serializer {
	

	public org.jdom.Document serialize(Object obj){
		
			
			
			Element serialized = new Element("serialized");
			Document doc = new Document(serialized);
			doc.setRootElement(serialized);
			Element object = new Element ("Object");
			if(obj.getClass().toString().equals("class serialization.SimpleObject")){
				
			}
			else if(obj.getClass().toString().equals("class serialization.ObjectReference")){
				
			}
			else if(obj.getClass().toString().equals("class serialization.PrimitiveObject")){
				
			}
			else if(obj.getClass().toString().equals("class serialization.ArrayRefObject")){
				
			}
			else if(obj.getClass().toString().equals("class serialization.CollectionObject")){
				
			}
		
			
			
		return null;
	}


}
