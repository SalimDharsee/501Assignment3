package serialization;

import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Array;
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
				int hash = obj.hashCode();
				String sHash = hash + "";
				object.setAttribute(new Attribute("ID", sHash ));
				Field[] aField = obj.getClass().getDeclaredFields();
				

				for(int i = 0; i < aField.length; i++){
					
					Element field = new Element("Field");
					field.setAttribute(new Attribute("name", aField[i].getName()));
					field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
					
	
					try {
						Element value = new Element("value");
						Object objValue = aField[i].get(obj);
						value.addContent(objValue.toString());
						field.addContent(value);
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
					
						System.out.println("Error has occured");
					} 
					
					object.addContent(field);
					
				}

				
				doc.getRootElement().addContent(object);
			
	
			}
			else if(obj.getClass().toString().equals("class serialization.ObjectReference")){
				Element objectRef = new Element ("Object");
				int hash = obj.hashCode();
				String sHash = hash + "";
				objectRef.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				objectRef.setAttribute(new Attribute("ID", sHash));
				Field[] aField = obj.getClass().getDeclaredFields();
				Element value = null;
				Element field = new Element("Field");
				Element afield = new Element("Field");

				for(int i = 0; i < aField.length; i++){
					if(aField[i].getType().isPrimitive()){
						field = new Element("Field");
						field.setAttribute(new Attribute("name", aField[i].getName()));
						field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
						try {
							value = new Element("value");
							Object objValue = aField[i].get(obj);
							value.addContent(objValue.toString());
							field.addContent(value);
							
						} catch (IllegalArgumentException | IllegalAccessException e) {
						
							System.out.println("Error has occured");
						} 
						objectRef.addContent(field);
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
				doc.getRootElement().addContent(objectRef);
				
			}
			else if(obj.getClass().toString().equals("class serialization.PrimitiveObject")){
				Element objectPrim = new Element ("Object");
				Element arrayObj = null;
				int hash = obj.hashCode();
				String sHash = hash + "";
				objectPrim.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				objectPrim.setAttribute(new Attribute("ID", sHash));
				Field[] aField = obj.getClass().getDeclaredFields();
				Element field = null;
				Element value = new Element("value");

				for(int i = 0; i < aField.length; i++){
					if(aField[i].getType().isArray() == false){
						field = new Element("Field");
						field.setAttribute(new Attribute("name", aField[i].getName()));
						field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
						try {
							value = new Element("value");
							Object objValue = aField[i].get(obj);
							value.addContent(objValue.toString());
							field.addContent(value);
							
						} catch (IllegalArgumentException | IllegalAccessException e) {
						
							System.out.println("Error has occured");
						} 
						objectPrim.addContent(field);
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
				doc.getRootElement().addContent(objectPrim);
				doc.getRootElement().addContent(arrayObj);
				
				
			}
			else if(obj.getClass().toString().equals("class serialization.ArrayRefObject")){
				Element objectAr = new Element ("Object");
				int hash = obj.hashCode();
				String sHash = hash + "";
				Element arrayObj = null;
				objectAr.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				objectAr.setAttribute(new Attribute("ID", sHash));
				Field[] aField = obj.getClass().getDeclaredFields();
				
				

				for(int i = 0; i < aField.length; i++){
					if(aField[i].getType().isArray() == false){
						Element field = new Element("Field");
						field.setAttribute(new Attribute("name", aField[i].getName()));
						field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
						//Element value = field.addContent(new Element("value"));
						try {
							Element value = new Element("value");
							Object objValue = aField[i].get(obj);
							value.addContent(objValue.toString());
							field.addContent(value);
							
						} catch (IllegalArgumentException | IllegalAccessException e) {
						
							System.out.println("Error has occured");
						} 
						objectAr.addContent(field);
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
				doc.getRootElement().addContent(objectAr);
			}
			else if(obj.getClass().toString().equals("class serialization.CollectionObject")){
				Element objectCol = new Element ("Object");
				int hash = obj.hashCode();
				String sHash = hash + "";
				objectCol.setAttribute(new Attribute("class",obj.getClass().getSimpleName()));
				objectCol.setAttribute(new Attribute("ID", sHash));
				Field[] aField = obj.getClass().getDeclaredFields();
				

				for(int i = 0; i < aField.length; i++){
					
					Element field = new Element("Field");
					field.setAttribute(new Attribute("name", aField[i].getName()));
					field.setAttribute("declaringclass",aField[i].getDeclaringClass().getSimpleName());
					try {
						Element value = new Element("value");
						Object objValue = aField[i].get(obj);
						value.addContent(objValue.toString());
						field.addContent(value);
						
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
