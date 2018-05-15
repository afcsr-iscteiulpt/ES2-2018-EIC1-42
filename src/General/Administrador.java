package General;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Administrador {

	static String name;
	static String email;
	static String password;
	static String experimentDir;
	static String problemsDir;
	static String graphicsDir;
	
	public Administrador(String path) {
		try {
			
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();
		
//		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList listOfAdmin = doc.getElementsByTagName("administrador");
		for (int temp = 0; temp < listOfAdmin.getLength(); temp++) {
			Node nNode = listOfAdmin.item(temp);
//			System.out.println("Current Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				name = eElement.getElementsByTagName("name").item(0).getTextContent();
				email = eElement.getElementsByTagName("email").item(0).getTextContent();
				password = eElement.getElementsByTagName("password").item(0).getTextContent();
//				System.out.println("Name : " + name);
//				System.out.println("Email : " + email);	
//				System.out.println("Password : " + password);	
			}
		}
		
		NodeList listOfDir = doc.getElementsByTagName("directories");
		for (int temp = 0; temp < listOfDir.getLength(); temp++) {
			Node nNode = listOfDir.item(temp);
//			System.out.println("Current Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				experimentDir = eElement.getElementsByTagName("experimentDir").item(0).getTextContent();
				problemsDir = eElement.getElementsByTagName("problemsDir").item(0).getTextContent();
				graphicsDir = eElement.getElementsByTagName("graphicsDir").item(0).getTextContent();
//				System.out.println("experimentDir : " + experimentDir);
//				System.out.println("problemsDir : " + problemsDir);
//				System.out.println("graphicsDir : " + graphicsDir);
			}
		}
		
		
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public static String getName() {
		return name;
	}


	public static String getEmail() {
		return email;
	}


	public static String getPassword() {
		return password;
	}


	public static String getExperimentDir() {
		return experimentDir;
	}


	public static String getProblemsDir() {
		return problemsDir;
	}


	public static String getGraphicsDir() {
		return graphicsDir;
	}

	
}