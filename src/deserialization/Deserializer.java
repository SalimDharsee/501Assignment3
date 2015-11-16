package deserialization;

import java.io.*;

import org.jdom.*;
import org.jdom.input.SAXBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/*
 * This class is responsible for taking an XML file that was transfered through the socket
 * creating its corresponding doc file. the doc file is then used to create the objects in the 
 * array list.
 * The object would then be passed to be inspected *not implemented*
 * 
 */
public class Deserializer implements Serializable {

    public Object[] deserialize() {

    	// Creating a doc file from the XML that was transfered over 
    	SAXBuilder builder = new SAXBuilder();
    	Document document = null;
    	try {
			document = builder.build("src/deserialization//output.xml");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// Getting the root element so that we can parse through the document and veiw the objects 
        Element root = document.getRootElement();
        List<Element> children = root.getChildren();
        List<Object> desObjects = new ArrayList<>();
        
        for (Element c : children) {
            List<Attribute> attributes = c.getAttributes();
            String childClass = attributes.get(0).getValue();
            childProcess(desObjects, c, childClass);
        }
        return desObjects.toArray();
    }

	private void childProcess(List<Object> desObjects, Element c,
			String childClass) {
		try {
		    Class actChild = Class.forName(childClass);
		    Object childObject = actChild.newInstance();
		    List<Element> fields = c.getChildren();
		    for (Element f : fields) {
		        List<Attribute> fieldAtts = f.getAttributes();
		        String fValue = f.getValue();
		        String fName = fieldAtts.get(0).getValue();

		         Field fd = childObject.getClass().getDeclaredField(fName);
		         String varType = fd.getType().getName();
		         fd.setAccessible(true);
		         caseSwitch(childObject, fValue, fd, varType);
		    }
		    desObjects.add(childObject);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | SecurityException ex) {
		    ex.printStackTrace();
		}
	}

	private void caseSwitch(Object childObject, String fValue, Field fd,
			String varType) throws IllegalAccessException {
		switch (varType) {
		     case ("int"):
		         fd.setInt(childObject, Integer.parseInt(fValue));
		         break;
		     case ("double"):
		         fd.setDouble(childObject, Double.parseDouble(fValue));
		         break;
		     case ("boolean"):
		         fd.setBoolean(childObject, Boolean.parseBoolean(fValue));
		         break;
		 }
	}

}
