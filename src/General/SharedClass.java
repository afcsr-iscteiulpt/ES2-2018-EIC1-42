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
import java.util.Arrays;
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
import Interface.GUIFinal;
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
	private GUIFinal guiFinal;
	private GUIGraphs guiGraphs;
	private Boolean verifyLoad = false;
	private int binaryVariableSize = 0;

	private Problem problem = new Problem("", "", "", null, null, 0); // name ;
																		// description
																		// varArray
																		// AlgorithmArray
																		// DaysToWait

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
		problem = new Problem("", "", "", new ArrayList<Variable>(), new ArrayList<String>(), 0);
		launch();

	}

	/*
	 * Função que lança o primeiro GUI
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

	public void setExistingPanelX(JPanel panel) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panel);
		frame.revalidate();
		frame.repaint();
	}

	// -------------------NP RELATED---------------------

	/*
	 * Função do but�o "New Problem" que inicializa o array dos GUI's
	 * pertencentes às funcionalidade do "New Problem">
	 */
	public void createNPArray() {
		// 0 in ArrayNewProblem
		ArrayNewProblem.clear();
		guidescricaoproblema = new GUIProblem(this);
		ArrayNewProblem.add(guidescricaoproblema.getContentPane());
		// 1 in ArrayNewProblem
		guiDefinicaoVariaveis = new GUIVariables(this);
		ArrayNewProblem.add(guiDefinicaoVariaveis.getContentPane());
		// 2 in ArrayNewProblem
		guiAlgo = new GUIAlgorithms(this);
		ArrayNewProblem.add(guiAlgo.getContentPane());
		// 3 in ArrayNewProblem
		guiFinal = new GUIFinal(this);
		ArrayNewProblem.add(guiFinal.getContentPane());
		// 3 in ArrayNewProblem
		// guiGraphs = new GUIGraphs(this);
		// ArrayNewProblem.add(guiGraphs.getPanel());
	}

	public ArrayList<JPanel> getNPArray() {
		return ArrayNewProblem;
	}

	// -------------------SP RELATED---------------------
	/*
	 * Fun��o do but�o "Stored Problem" que inicializa o array dos GUI's
	 * pertencentes � funcionalidade do "Stored Problem"
	 */
	public void acessStoredProblems() {
		setExistingPanelX(storedProblem.getContentPane());
	}

	// -------------------FAQ RELATED---------------------
	/*
	 * Fun��o do but�o "FAQ" que inicializa o array dos GUI's pertencentes �
	 * funcionalidade do "FAQ"
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
	 * Fun��o respons�vel pela cria��o do problema com os dados fornecidos pelo
	 * utilizador
	 */
	public void createProblem() {
		System.out.println(guidescricaoproblema.getName() + "   " + guidescricaoproblema.getDescription() + "   "
				+ guidescricaoproblema.getEmail());
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

			ArrayList<Variable> variablesArray = p.getVariablesArray();
			String type = variablesArray.get(0).getType();

			for (int i = 0; i < variablesArray.size(); i++) {
				String varName = "Var" + i;
				Element variable = doc.createElement(varName);
				variables.appendChild(variable);

				if (type.equals("Integer") || type.equals("Double")) {
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
					variableMax.appendChild(doc.createTextNode(String.valueOf(variablesArray.get(i).getMAX())));
					variable.appendChild(variableMax);
				} else if (type.equals("Binary")) {
					Element variableName = doc.createElement("Name");
					variableName.appendChild(doc.createTextNode(String.valueOf(variablesArray.get(i).getName())));
					variable.appendChild(variableName);

					Element variableType = doc.createElement("Type");
					variableType.appendChild(doc.createTextNode(String.valueOf(variablesArray.get(i).getType())));
					variable.appendChild(variableType);

					Element variableValue = doc.createElement("Value");
					variableValue.appendChild(doc.createTextNode(String.valueOf(variablesArray.get(i).getValue())));
					variable.appendChild(variableValue);
				}
			}

			Element algorithms = doc.createElement("Algorithms");
			root.appendChild(algorithms);
			algorithms.appendChild(doc.createTextNode(""));
			ArrayList<String> algorithmsArray = p.getAlgorithms();
			for (int i = 0; i < algorithmsArray.size(); i++) {
				String algorithmName = algorithmsArray.get(i);
				Element algorithm = doc.createElement(algorithmName);
				algorithms.appendChild(algorithm);
			}
			Element daysToWait = doc.createElement("Days");
			root.appendChild(daysToWait);
			daysToWait.appendChild(doc.createTextNode(String.valueOf(p.getNumberOfDays())));

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

				String problemName = p.getName() + " - " + date;

				FileWriter fos = new FileWriter("/Users/albertoramos/Desktop/" + problemName);
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
			File file = new File("");
			;
			ArrayList<Variable> variablesFromXML = new ArrayList<Variable>();
			ArrayList<String> algorithmsFromXML = new ArrayList<String>();

			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
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
			System.out.println("Total number of problems : " + totalPersons);

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
					if (((Node) EmailList.item(0)).getNodeValue().trim().equals(null))
						System.out.println("Email : ");
					else
						System.out.println("Email : " + ((Node) EmailList.item(0)).getNodeValue().trim());

					NodeList variables = firstPersonElement.getElementsByTagName("Variables");
					Element VarElement = (Element) variables.item(0);
					NodeList varList = VarElement.getChildNodes();
					if (varList != null) {
						int varLength = varList.getLength();
						for (int i = 1; i < varLength; i = i + 2) {
							if (varList.item(i).getNodeType() == Node.ELEMENT_NODE) {
								Element varElement = (Element) varList.item(i);
								if (varElement.getNodeName().contains("Var")) {
									String varName = varElement.getElementsByTagName("Name").item(0).getTextContent();
									String varType = varElement.getElementsByTagName("Type").item(0).getTextContent();
									if (varType.equals("Integer")) {
										String varMin = varElement.getElementsByTagName("Min").item(0).getTextContent();
										String varMax = varElement.getElementsByTagName("Max").item(0).getTextContent();
										Variable v = new Variable(varName, varType, Integer.parseInt(varMin),
												Integer.parseInt(varMax));
										variablesFromXML.add(v);
										guiDefinicaoVariaveis.getVariablesArray().add(v);
									} else if (varType.equals("Double")) {
										String varMin = varElement.getElementsByTagName("Min").item(0).getTextContent();
										String varMax = varElement.getElementsByTagName("Max").item(0).getTextContent();
										Variable v = new Variable(varName, varType, Double.parseDouble(varMin),
												Double.parseDouble(varMax));
										variablesFromXML.add(v);
										guiDefinicaoVariaveis.getVariablesArray().add(v);
									} else if (varType.equals("Binary")) {
										String value = varElement.getElementsByTagName("Value").item(0)
												.getTextContent();
										Variable v = new Variable(varName, varType, value);
										variablesFromXML.add(v);
										guiDefinicaoVariaveis.getVariablesArray().add(v);
									}
								}
							}
						}
					}

					NodeList algorithms = firstPersonElement.getElementsByTagName("Algorithms");
					Element AlgoriElement = (Element) algorithms.item(0);
					NodeList algoriList = AlgoriElement.getChildNodes();

					if (algoriList != null) {
						int algoriLength = algoriList.getLength();
						for (int i = 1; i < algoriLength; i = i + 2) {
							if (algoriList.item(i).getNodeType() == Node.ELEMENT_NODE) {
								Element SingleAlgoriElement = (Element) algoriList.item(i);
								algorithmsFromXML.add(SingleAlgoriElement.getNodeName());
							}
						}
					}

					NodeList days = firstPersonElement.getElementsByTagName("Days");
					Element daysElement = (Element) days.item(0);

					NodeList daysContent = daysElement.getChildNodes();
					System.out.println("Days : " + ((Node) daysContent.item(0)).getNodeValue().trim());

					LoadProblem(((Node) textFNList.item(0)).getNodeValue().trim(),
							((Node) textLNList.item(0)).getNodeValue().trim(),
							((Node) EmailList.item(0)).getNodeValue().trim(), variablesFromXML, algorithmsFromXML,
							Integer.parseInt(((Node) daysContent.item(0)).getNodeValue().trim()));

					// -------

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
		// teste

	public void LoadProblem(String name, String description, String email, ArrayList<Variable> variables,
			ArrayList<String> algorithms, int days) {
		setVerifyLoad(true);

		guidescricaoproblema.setName(name);
		guidescricaoproblema.setDescription(description);
		guidescricaoproblema.setEmail(email);

		guiAlgo.setAlgorithms(algorithms);
		guiAlgo.getTFTime().setText("" + days);
		guiAlgo.setDays(days);

		for (int i = 0; i < algorithms.size(); i++) {
			guiAlgo.addAlgorithm(algorithms.get(i));
		}

		String s1 = guiDefinicaoVariaveis.getTextAreaStringIntDouble();
		for (int i = 0; i < variables.size(); i++) {
			s1 += "\n" + variables.get(i).toStringVariable();
			guiDefinicaoVariaveis.setTextAreaStringIntDouble(s1);
			guiDefinicaoVariaveis.getTextArea().setText(s1);
		}
		guiDefinicaoVariaveis.setTextAreaStringIntDouble(s1);

		String s2 = "Algorithms chosen: " + "\n";
		for (int i = 0; i < algorithms.size(); i++) {
			s2 += algorithms.get(i) + "\n";
			guiAlgo.getTAManu().setText(s2);
		}

		if (!problem.getVariablesArray().isEmpty()) {
			problem.getVariablesArray().clear();
		}
		if (!problem.getAlgorithms().isEmpty()) {
			problem.getAlgorithms().clear();
		}

		problem.setName(name);
		problem.setDescription(description);
		problem.setEmail(email);
		problem.setVariablesArray(variables);
		problem.setType(variables.get(0).getType());
		problem.setAlgorithms(algorithms);
		problem.setNumberOfDays(days);

		guiDefinicaoVariaveis.setComboBoxValue(problem.getType());
		guiDefinicaoVariaveis.getComboBox().setEnabled(false);

		setReviewProblem();
	}

	public void setReviewProblem() {
		String s1 = "";
		String s2 = "";
		guiFinal.getTFName().setText(problem.getName());
		guiFinal.getTADescription().setText(problem.getDescription());

		if (!problem.getVariablesArray().isEmpty()) {
			for (int i = 0; i < problem.getVariablesArray().size(); i++) {
				s1 += problem.getVariablesArray().get(i).getName() + "\n";
				guiFinal.getTAVariables().setText(s1);
			}
		} else {
			guiFinal.getTAVariables().setText(s1);
		}

		if (!problem.getAlgorithms().isEmpty()) {
			for (int i = 0; i < problem.getAlgorithms().size(); i++) {
				s2 += problem.getAlgorithms().get(i) + "\n";
				guiFinal.getTAAlgorit().setText(s2);
			}
		} else {
			guiFinal.getTAAlgorit().setText(s2);
		}

		guiFinal.getTFDays().setText("" + problem.getNumberOfDays());
	}

	public void setBinaryVariableSize(int size) {
		binaryVariableSize = size;
	}

	public int getBinaryVariableSize() {
		return binaryVariableSize;
	}

	public void setGUI(GUI gui) {
		this.gui = gui;
	}

	public ArrayList<JPanel> getArrayOfPanels() {
		return ArrayOfPanels;
	}

	public Problem getProblem() {
		return problem;
	}

	public Boolean getVerifyLoad() {
		return verifyLoad;
	}

	public void setVerifyLoad(boolean b) {
		verifyLoad = b;
	}
}
