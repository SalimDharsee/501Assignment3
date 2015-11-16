package serialization;

import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

import org.jdom.*;
import org.jdom.output.XMLOutputter;

/* Salim Dharsee 
 * ID: 10062458
 * This class will serialize each object passed to it based on the formatting procedures outlined on the 
 * assignment page. The class is called from the object creator which passes its created object into this
 * class which will attach the serialized fields and content into an xml document.
 * The document is returned by the constructor method of this class.
 * 
 * Some of the code was aided with the reference of online material, any similarities should be assumed to be from this.
 */
public class Serializer {
	
	static Element serialized = new Element("serialized");
	static Document doc = new Document(serialized);
	
	public org.jdom.Document serialize(Object obj)  {
		
			doc.setRootElement(serialized);
			
			// The following will call the corresponding method to format the XML document
			if(obj.getClass().toString().equals("class serialization.SimpleObject")){
				serializeSimple(obj);
			}
			else if(obj.getClass().toString().equals("class serialization.ObjectReference")){
				serializeObjRef(obj);
			}
			else if(obj.getClass().toString().equals("class serialization.PrimitiveObject")){
				serializeObjPrim(obj);
			}
			else if(obj.getClass().toString().equals("class serialization.ArrayRefObject")){
				serializeArrayRef(obj);
			}
			else if(obj.getClass().toString().equals("class serialization.CollectionObject")){
				serializeObjCol(obj);
				
			}
			createXML();
			
		return doc;
	}
	// accessor method for obtaining the doc file
	public org.jdom.Document getDoc(){
		return doc;
	}

	// The following method creates an outputter which allows use to write to an xml document 
	private void createXML() {
		XMLOutputter xmlOutput = new XMLOutputter();
		try {
			
			xmlOutput.output(doc, new FileWriter("src//serialization//sero.xml"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	// The following method will serialize the collection objects
	private void serializeObjCol(Object obj) {
		Element object = elementCreation(obj);
		Field[] aField = obj.getClass().getDeclaredFields();
		

		for(int i = 0; i < aField.length; i++){
			
			Element field = fieldCreation(aField, i);
			try {
				elementValue(obj, aField, i, field);
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
			
				System.out.println("Error has occured");
			} 
			object.addContent(field);
		}


		doc.getRootElement().addContent(object);
	}

	// The following is a method used in a refactor to remove duplicate code
	// This method is now called whenever the code is needed 
	private Element elementCreation(Object obj) {
		Element object = new Element ("Object");
		int hash = obj.hashCode();
		String sHash = hash + "";
		object.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
		object.setAttribute(new Attribute("ID", sHash));
		return object;
	}

	// The following method is used to serialize arrays with references objects 
	private void serializeArrayRef(Object obj) {
		
		Element object = elementCreation(obj);
		Element arrayObj = null;
		Field[] aField = obj.getClass().getDeclaredFields();
		
		// The for loop will gather all of the fields for this object
		for(int i = 0; i < aField.length; i++){
			// checks if it is an array
			if(aField[i].getType().isArray() == false){
				Element field = fieldCreation(aField, i);
				try {
					elementValue(obj, aField, i, field);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
				
					System.out.println("Error has occured");
				} 
				object.addContent(field);
				// goes to else if the field is an array, and formats it as stated 
			} else{
				arrayObj = new Element("Object");
				arrayObj.setAttribute("class", aField[i].getDeclaringClass().getSimpleName());
				arrayObj.setAttribute("id", String.valueOf(aField[i].hashCode()));
				Object objValue = null;

				try {
					objValue = aField[i].get(obj);
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				}
				String length = String.valueOf(Array.getLength((objValue)));
				arrayObj.setAttribute("length",length );
				int iLength = Integer.parseInt(length);
				for(int a = 0; a < iLength; a++ ){
					Element reference = new Element("reference");
					reference.addContent(String.valueOf(aField[i].hashCode()));
					arrayObj.addContent(reference);
				}
			}
		}
		doc.getRootElement().addContent(arrayObj);	
		doc.getRootElement().addContent(object);
	}

	// the following method is used to serialize objects with an array of primitives 
	private void serializeObjPrim(Object obj) {
		
		Element object = elementCreation(obj);
		Element arrayObj = null;
		Field[] aField = obj.getClass().getDeclaredFields();
		Element field = null;

		// goes through all the fields of the object
		for(int i = 0; i < aField.length; i++){
			// checks to see if the field is an array 
			if(aField[i].getType().isArray() == false){
				field = new Element("Field");
				field.setAttribute(new Attribute("name", aField[i].getName()));
				field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
				try {
					elementValue(obj, aField, i, field);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
				
					System.out.println("Error has occured");
				} 
				object.addContent(field);
				// goes to the else if the field is an array, and formats it as stated 
			}else{
				arrayObj = new Element("Object");
				arrayObj.setAttribute("class", aField[i].getDeclaringClass().getSimpleName());
				arrayObj.setAttribute("id", String.valueOf(aField[i].hashCode()));
				Object objValue = null;
				try {
					objValue = aField[i].get(obj);
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				}
				String length = String.valueOf(Array.getLength((objValue)));
				arrayObj.setAttribute("length",length );
				int iLength = Integer.parseInt(length);
				for(int a = 0; a < iLength; a++ ){
					Object arrayElement = Array.get(objValue, a);
					Element aValue = new Element("value");
					aValue.addContent(arrayElement.toString());
					arrayObj.addContent(aValue);
				}
			}
		}
		doc.getRootElement().addContent(object);
		doc.getRootElement().addContent(arrayObj);
	}

	// this method is used to serialize methods with object references. 
	private void serializeObjRef(Object obj) {
		Element object = elementCreation(obj);
		Field[] aField = obj.getClass().getDeclaredFields();
		Element field = new Element("Field");
		Element afield = new Element("Field");
		
		// goes through all the field values in the object
		for(int i = 0; i < aField.length; i++){
			// if it is a primitive it will format it as stated 
			if(aField[i].getType().isPrimitive()){
				field = new Element("Field");
				field.setAttribute(new Attribute("name", aField[i].getName()));
				field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
				try {
					elementValue(obj, aField, i, field);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
				
					System.out.println("Error has occured");
				} 
				object.addContent(field);
				// otherwise it will be formated as a reference 
			}
			else{
				afield = new Element("Field");
				afield.setAttribute(new Attribute("name", aField[i].getName()));
				afield.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
				Element reference = new Element("reference");
				reference.addContent(String.valueOf(aField[i].hashCode()));
				afield.addContent(reference);	
			}
		}

		doc.getRootElement().addContent(afield);
		doc.getRootElement().addContent(object);
	}

	// This method is used to serialize simple objects, it will format the object as stated 
	private void serializeSimple(Object obj) {
		Element object = elementCreation(obj);
		Field[] aField = obj.getClass().getDeclaredFields();
		

		for(int i = 0; i < aField.length; i++){
			
			Element field = fieldCreation(aField, i);
			try {
				elementValue(obj, aField, i, field);
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
			
				System.out.println("Error has occured");
			} 
			object.addContent(field);
		}
		doc.getRootElement().addContent(object);
	}

	// this method is used in a refactor to remove duplicated code
	// this method is now called wherever the code was needed 
	private Element fieldCreation(Field[] aField, int i) {
		Element field = new Element("Field");
		field.setAttribute(new Attribute("name", aField[i].getName()));
		field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
		return field;
	}

	// this method is used in a refactor to remove duplicated code
	// this method is now called wherever the code was needed 
	private void elementValue(Object obj, Field[] aField, int i, Element field)
			throws IllegalAccessException {
			Element value = new Element("value");
			Object objValue = aField[i].get(obj);
			value.addContent(objValue.toString());
			field.addContent(value);
	}
}
