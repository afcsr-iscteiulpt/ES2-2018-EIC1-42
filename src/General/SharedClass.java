package General;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import Interface.GUI;
import Interface.GUIAlgorithms;
import Interface.GUIVariables;
import Interface.GUIProblem;
import Interface.GUIStoredProblems;
import Interface.GUIFAQ;
import Interface.GUIGraphs;


public class SharedClass {
	private JFrame frame = new JFrame("Optimization on Demand 1.0");
	private ArrayList<JPanel> ArrayOfPanels = new ArrayList<JPanel>();
	private ArrayList<JPanel> ArrayNewProblem = new ArrayList<JPanel>();
	private GUIStoredProblems storedProblem = new GUIStoredProblems(this);
	private ArrayList<JPanel> ArrayFAQ = new ArrayList<JPanel>();
	private GUI gui;
	private GUIProblem guidescricaoproblema;
	private GUIVariables guiDefinicaoVariaveis;
	private GUIFAQ guifaq;
	private GUIAlgorithms guiAlgo;
	private GUIGraphs guiGraphs;
	
	private Problem problem = new Problem("", "", "", null);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SharedClass();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SharedClass() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 700, 500);
		launch();
	}

	/*
	 * Fun��o que lan�a o primeiro GUI
	 */
	public void launch() {
		gui = new GUI(this);
		ArrayOfPanels.add(gui.getContentPane());
		setExistingPanel(ArrayOfPanels, 0);
	}

	public void setFrame(JFrame frameX) {
		this.frame = frameX;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setExistingPanel(ArrayList<JPanel> panels, int i) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panels.get(i));
		frame.revalidate();
		frame.repaint();
	}
	public void setExistingPanelX(JPanel panel){
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panel);
		frame.revalidate();
		frame.repaint();
	}
	
	// -------------------NP RELATED---------------------
	
	/*
	 * Fun��o do but�o "New Problem" que inicializa o array dos GUI's pertencentes � funcionalidade do "New Problem"
	 */
	public void createNPArray() {
		// 0 in ArrayNewProblem
		ArrayNewProblem.clear();
		guidescricaoproblema = new GUIProblem(this);
		ArrayNewProblem.add(guidescricaoproblema.getContentPane());
		// 1 in ArrayNewProblem
		guiDefinicaoVariaveis = new GUIVariables(this);
		ArrayNewProblem.add(guiDefinicaoVariaveis.getContentPane());
		//2 in ArrayNewProblem
		guiAlgo = new GUIAlgorithms(this);
		ArrayNewProblem.add(guiAlgo.getContentPane());
		//3 in ArrayNewProblem
		guiGraphs = new GUIGraphs(this);
		ArrayNewProblem.add(guiGraphs.getPanel());
	}
	public ArrayList<JPanel> getNPArray() {
		return ArrayNewProblem;
	}

	// -------------------SP RELATED---------------------
	/*
	 * Fun��o do but�o "Stored Problem" que inicializa o array dos GUI's pertencentes � funcionalidade do "Stored Problem"
	 */
	public void acessStoredProblems() {
		setExistingPanelX(storedProblem.getContentPane());
	}


	// -------------------FAQ RELATED---------------------
	/*
	 * Fun��o do but�o "FAQ" que inicializa o array dos GUI's pertencentes � funcionalidade do "FAQ"
	 */
	public void createFAQArray() {
		// 0 in ArrayFAQ
		ArrayFAQ.clear();
		guifaq = new GUIFAQ(this);
		ArrayFAQ.add(guifaq.getContentPane());
	}

	public ArrayList<JPanel> getFAQArray() {
		return ArrayFAQ;
	}
	// --------------------------------------------------

	
	/*
	 * Fun��o respons�vel pela cria��o do problema com os dados fornecidos pelo utilizador
	 */
	public void createProblem(){
		System.out.println(guidescricaoproblema.getName() +"   "+guidescricaoproblema.getDescription() +"   "+guidescricaoproblema.getEmail() );
		problem.setName(guidescricaoproblema.getName());
		problem.setDescription(guidescricaoproblema.getDescription());
		problem.setEmail(guidescricaoproblema.getEmail());
		problem.setVariablesArray(guiDefinicaoVariaveis.getVariablesArray());
		
		storedProblem.addProblem(problem);
		
		writeXmlFile(problem);
	}
	
	/*
	 * Fun��o respons�vel pela cria��o/escrita do ficheiro XML
	 * 
	 * @param Problem p
	 */
	public void writeXmlFile(Problem p) {
	    try {
	        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
	        DocumentBuilder build = dFact.newDocumentBuilder();
	        Document doc = build.newDocument();
	        
	        Element root = doc.createElement("Problem");
	        doc.appendChild(root);
	       
	        Element name = doc.createElement("Name");
	        root.appendChild(name);
	        	name.appendChild(doc.createTextNode(p.getName()));	    
	        Element description = doc.createElement("Description");
	        root.appendChild(description);
        		description.appendChild(doc.createTextNode(p.getDescription()));	    
	        Element usermail = doc.createElement("Email");
	        root.appendChild(usermail);
    			usermail.appendChild(doc.createTextNode(p.getEmail()));	    
    		Element variables = doc.createElement("Variables");
    		root.appendChild(variables);
    			variables.appendChild(doc.createTextNode(""));
    			
    			ArrayList<Variable> variablesArray = guiDefinicaoVariaveis.getVariablesArray();

                for (int i = 0; i < variablesArray.size(); i++) {
                	String varName = "Var"+i;
                	Element variable = doc.createElement(varName);
                		variables.appendChild(variable);
                			
                			Element variableName = doc.createElement("Name");
            	            variableName.appendChild(doc.createTextNode(String.valueOf(variablesArray.get(i).getName())));
                			variable.appendChild(variableName);
                			
                			Element variableType = doc.createElement("Type");
            	            variableType.appendChild(doc.createTextNode(String.valueOf(variablesArray.get(i).getType())));
                			variable.appendChild(variableType);
                			
                			Element variableMin = doc.createElement("Min");
            	            variableMin.appendChild(doc.createTextNode(String.valueOf(variablesArray.get(i).getMIN())));
                			variable.appendChild(variableMin);
                			
                			Element variableMax = doc.createElement("Max");
            	            variableMax.appendChild(doc.createTextNode(String.valueOf(variablesArray.get(i).getMIN())));
                			variable.appendChild(variableMax);	
    			
                }

	        TransformerFactory tranFactory = TransformerFactory.newInstance();
	        Transformer aTransformer = tranFactory.newTransformer();
	        /*
	         * Formata��o do ficheiro XML
	         */
	        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
	        aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        try {
	        	
	        	String date = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss").format(new Date());
	 
	        	System.out.println(date);
	        	
	            String problemName = p.getName()+" - " +date ;
	            
	            FileWriter fos = new FileWriter("C:/Users/andre/Desktop/File.xml");
	            StreamResult result = new StreamResult(fos);
	            aTransformer.transform(source, result);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } catch (TransformerException ex) {
	        System.out.println("Error outputting document");
	    } catch (ParserConfigurationException ex) {
	        System.out.println("Error building document");
	    }
	}
	
	
	
	
	/*
	 * Fun��o respons�vel pela leitura do ficheiro XML
	 */
	public void readXMLFile() {

		try {
			
			JFileChooser fileChooser = new JFileChooser();
			StringBuilder sb = new StringBuilder();
			File file = new File("");;
			
			if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				 file = fileChooser.getSelectedFile();
			}
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);

			/*
			 * Normaliza a apresenta��o do texto
			 */
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			NodeList listOfProblems = doc.getElementsByTagName("Problem");
			int totalPersons = listOfProblems.getLength();
			System.out.println("Total no of people : " + totalPersons);

			for (int s = 0; s < listOfProblems.getLength(); s++) {

				Node firstProblemNode = listOfProblems.item(s);
				if (firstProblemNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstPersonElement = (Element) firstProblemNode;

					// -------
					NodeList name = firstPersonElement.getElementsByTagName("Name");
					Element NameElement = (Element) name.item(0);

					NodeList textFNList = NameElement.getChildNodes();
					System.out.println("Name : " + ((Node) textFNList.item(0)).getNodeValue().trim());

					// -------
					NodeList description = firstPersonElement.getElementsByTagName("Description");
					Element DescriptionElement = (Element) description.item(0);

					NodeList textLNList = DescriptionElement.getChildNodes();
					System.out.println("Description : " + ((Node) textLNList.item(0)).getNodeValue().trim());

					// ----
					NodeList email = firstPersonElement.getElementsByTagName("Email");
					Element EmailElement = (Element) email.item(0);

					NodeList EmailList = EmailElement.getChildNodes();
					if(((Node) EmailList.item(0)).getNodeValue().trim().equals(null))
						System.out.println("Email : ");
					else
						System.out.println("Email : " + ((Node) EmailList.item(0)).getNodeValue().trim());

					LoadProblem(((Node) textFNList.item(0)).getNodeValue().trim() , ((Node) textLNList.item(0)).getNodeValue().trim(), ((Node) EmailList.item(0)).getNodeValue().trim());

					// ------

				} // end of if clause

			} // end of for loop with s var

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		// System.exit (0);

	}// end of main
	
	
	public void LoadProblem(String name, String description, String email){
		
		guidescricaoproblema.setName(name);
		guidescricaoproblema.setDescription(description);
		guidescricaoproblema.setEmail(email);
	}
	
	
	
	
	public void setGUI(GUI gui) {
		this.gui = gui;
	}

	public ArrayList<JPanel> getArrayOfPanels() {
		return ArrayOfPanels;
	}
}
