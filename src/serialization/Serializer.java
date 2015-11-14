package serialization;

import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

import org.jdom.*;
import org.jdom.output.XMLOutputter;

public class Serializer {
	
	
	Element serialized = new Element("serialized");
	Document doc = new Document(serialized);
	
	
	public org.jdom.Document serialize(Object obj)  {
		
			doc.setRootElement(serialized);
			
			
			
			if(obj.getClass().toString().equals("class serialization.SimpleObject")){
				Element object = new Element ("Object");
				object.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				object.setAttribute(new Attribute("ID", "0"));
				Field[] aField = obj.getClass().getDeclaredFields();
				

				for(int i = 0; i < aField.length; i++){
					
					Element field = new Element("Field");
					field.setAttribute(new Attribute("name", aField[i].getName()));
					field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
					
	
					try {
						Element value = field.addContent(new Element("value"));
						
						Object objValue = aField[i].get(obj);
						value.addContent(objValue.toString());
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
					
						System.out.println("Error has occured");
					} 
					object.addContent(field);
					
				}

				
				
				
				doc.getRootElement().addContent(object);
			
	
			}
			else if(obj.getClass().toString().equals("class serialization.ObjectReference")){
				Element objectRef = new Element ("Object");
				objectRef.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				objectRef.setAttribute(new Attribute("ID", "0"));
				Field[] aField = obj.getClass().getDeclaredFields();
				

				for(int i = 0; i < aField.length; i++){
					
					Element field = new Element("Field");
					field.setAttribute(new Attribute("name", aField[i].getName()));
					field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
					try {
						Element value = field.addContent(new Element("value"));
						
						Object objValue = aField[i].get(obj);
						value.addContent(objValue.toString());
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
					
						System.out.println("Error has occured");
					} 
					objectRef.addContent(field);
				}

				
				doc.getRootElement().addContent(objectRef);
				
			}
			else if(obj.getClass().toString().equals("class serialization.PrimitiveObject")){
				Element objectPrim = new Element ("Object");
				objectPrim.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				objectPrim.setAttribute(new Attribute("ID", "0"));
				Field[] aField = obj.getClass().getDeclaredFields();
				

				for(int i = 0; i < aField.length; i++){
					Element field = new Element("Field");
					field.setAttribute(new Attribute("name", aField[i].getName()));
					field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
					try {
						Element value = field.addContent(new Element("value"));
						
						Object objValue = aField[i].get(obj);
						value.addContent(objValue.toString());
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
					
						System.out.println("Error has occured");
					} 
					objectPrim.addContent(field);
				}

				
				doc.getRootElement().addContent(objectPrim);
				
			}
			else if(obj.getClass().toString().equals("class serialization.ArrayRefObject")){
				Element objectAr = new Element ("Object");
				objectAr.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				objectAr.setAttribute(new Attribute("ID", "0"));
				Field[] aField = obj.getClass().getDeclaredFields();
				

				for(int i = 0; i < aField.length; i++){
					
					Element field = new Element("Field");
					field.setAttribute(new Attribute("name", aField[i].getName()));
					field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
					try {
						Element value = field.addContent(new Element("value"));
						
						Object objValue = aField[i].get(obj);
						value.addContent(objValue.toString());
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
					
						System.out.println("Error has occured");
					} 
					objectAr.addContent(field);
				}

				
				doc.getRootElement().addContent(objectAr);
			}
			else if(obj.getClass().toString().equals("class serialization.CollectionObject")){
				Element objectCol = new Element ("Object");
				objectCol.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				objectCol.setAttribute(new Attribute("ID", "0"));
				Field[] aField = obj.getClass().getDeclaredFields();
				

				for(int i = 0; i < aField.length; i++){
					
					Element field = new Element("Field");
					field.setAttribute(new Attribute("name", aField[i].getName()));
					field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
					try {
						Element value = field.addContent(new Element("value"));
						
						Object objValue = aField[i].get(obj);
						value.addContent(objValue.toString());
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
					
						System.out.println("Error has occured");
					} 
					objectCol.addContent(field);
				}

			
				doc.getRootElement().addContent(objectCol);
				
			}
		
			XMLOutputter xmlOutput = new XMLOutputter();
			try {
				xmlOutput.output(doc, new FileWriter("src//serialization//sero.xml"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		return null;
	}


}
