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

	
	public static void readConfigXML(String path) {
		try {
			
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList listOfAdmin = doc.getElementsByTagName("administrador");
		for (int temp = 0; temp < listOfAdmin.getLength(); temp++) {
			Node nNode = listOfAdmin.item(temp);
			System.out.println("Current Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
				System.out.println("Email : " + eElement.getElementsByTagName("email").item(0).getTextContent());	
			}
		}
		
		NodeList listOfDir = doc.getElementsByTagName("directories");
		for (int temp = 0; temp < listOfDir.getLength(); temp++) {
			Node nNode = listOfDir.item(temp);
			System.out.println("Current Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("experimentDir : " + eElement.getElementsByTagName("experimentDir").item(0).getTextContent());
				System.out.println("problemsDir : " + eElement.getElementsByTagName("problemsDir").item(0).getTextContent());
				System.out.println("graphicsDir : " + eElement.getElementsByTagName("graphicsDir").item(0).getTextContent());
			}
		}
		
		
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	public static void main(String[] args) {
		readConfigXML("C:/Users/bruno/Desktop/config.xml");
	}
	
}