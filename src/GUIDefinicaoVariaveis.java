import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;

public class GUIDefinicaoVariaveis extends JFrame {

	private JPanel contentPane;
	private JTextField TFName;
	private JTextField TFMin;
	private JTextField TFMax;
	private String textAreaString = "Name:		Type:		Interval:";
	private int positionInArray;
	private ArrayList<Variable> variablesArray = new ArrayList<Variable>();

	public GUIDefinicaoVariaveis(SharedClass shared) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LabelNewVariable = new JLabel("New Variable:");
		LabelNewVariable.setForeground(Color.WHITE);
		LabelNewVariable.setFont(new Font("Avenir Next", Font.BOLD, 16));
		LabelNewVariable.setBounds(20, 6, 161, 16);
		contentPane.add(LabelNewVariable);

		JLabel LabelNewVariableName = new JLabel("Name:");
		LabelNewVariableName.setForeground(Color.WHITE);
		LabelNewVariableName.setFont(new Font("Avenir Next", Font.BOLD, 13));
		LabelNewVariableName.setBounds(44, 34, 61, 16);
		contentPane.add(LabelNewVariableName);

		TFName = new JTextField();
		TFName.setForeground(new Color(0, 128, 128));
		TFName.setBounds(40, 51, 169, 26);
		contentPane.add(TFName);
		TFName.setColumns(10);

		JLabel LabelType = new JLabel("Type:");
		LabelType.setForeground(Color.WHITE);
		LabelType.setFont(new Font("Avenir Next", Font.BOLD, 13));
		LabelType.setBounds(44, 89, 61, 16);
		contentPane.add(LabelType);

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 128, 128));
		comboBox.setBounds(40, 112, 169, 27);
		contentPane.add(comboBox);
		comboBox.addItem(new String("Integer"));
		comboBox.addItem(new String("Double"));
		comboBox.addItem(new String("Boolean"));

		JLabel LabelVariablesList = new JLabel("Variables List:");
		LabelVariablesList.setForeground(Color.WHITE);
		LabelVariablesList.setFont(new Font("Avenir Next", Font.BOLD, 16));
		LabelVariablesList.setBounds(20, 177, 161, 16);
		contentPane.add(LabelVariablesList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 211, 598, 173);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(0, 128, 128));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setText(textAreaString);

		JLabel LabelIntervalo = new JLabel("Interval (*):");
		LabelIntervalo.setForeground(Color.WHITE);
		LabelIntervalo.setFont(new Font("Avenir Next", Font.BOLD, 13));
		LabelIntervalo.setBounds(273, 33, 76, 16);
		contentPane.add(LabelIntervalo);

		TFMin = new JTextField();
		TFMin.setForeground(new Color(0, 128, 128));
		TFMin.setBounds(273, 51, 46, 26);
		TFMin.setText("0");
		contentPane.add(TFMin);
		TFMin.setColumns(10);

		TFMax = new JTextField();
		TFMax.setForeground(new Color(0, 128, 128));
		TFMax.setColumns(10);
		TFMax.setBounds(331, 51, 46, 26);
		TFMax.setText("0");
		contentPane.add(TFMax);

		JLabel LabelNotMandatory = new JLabel("(*) Not mandatory");
		LabelNotMandatory.setForeground(Color.WHITE);
		LabelNotMandatory.setFont(new Font("Avenir Next", Font.ITALIC, 10));
		LabelNotMandatory.setBounds(273, 77, 120, 16);
		contentPane.add(LabelNotMandatory);

		JButton BotaoBack = new JButton("◀");
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.setBounds(34, 401, 53, 35);
		BotaoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setExistingPanel(shared.getNPArray(), 0);
			}
		});
		contentPane.add(BotaoBack);
		
		JButton BotaoNext = new JButton("▶");
		BotaoNext.setForeground(new Color(0, 128, 128));
		BotaoNext.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoNext.setBounds(614, 401, 53, 35);
		contentPane.add(BotaoNext);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(10);
		contentPane.add(progressBar);
		
		JButton ButtonAddVariable = new JButton("Add Variable");
		ButtonAddVariable.setForeground(new Color(0, 128, 128));
		ButtonAddVariable.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		ButtonAddVariable.setBounds(273, 112, 104, 29);
		contentPane.add(ButtonAddVariable);
		ButtonAddVariable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Variable v = new Variable();
				v.setName(TFName.getText());
				v.setType((String) comboBox.getSelectedItem());
				v.setMin(Integer.parseInt(TFMin.getText()));
				v.setMax(Integer.parseInt(TFMax.getText()));
				textAreaString += "\n" + v.toStringVariable();
				variablesArray.add(v);
				writeXmlFile(variablesArray);
				textArea.setText(textAreaString);
			}
		});

		JLabel LabelInformation = new JLabel("<html>Note: <br/>Optimization means minimize the objectives given</html>", SwingConstants.CENTER);
		LabelInformation.setForeground(Color.WHITE);
		LabelInformation.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		LabelInformation.setBounds(443, 29, 169, 76);
		contentPane.add(LabelInformation);
		JLabel LabelLogo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
	}
//
//	public void saveInXML(Variable v) {
//		try {
//			File file = new File("/Users/albertoramos/Desktop/file.xml");
//			JAXBContext jaxbContext = JAXBContext.newInstance(Variable.class);
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			// output pretty printed
//			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//			jaxbMarshaller.marshal(v, file);
//			jaxbMarshaller.marshal(v, System.out);
//
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
//	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setPosition(int i) {
		positionInArray = i;
	}
	
	public void writeXmlFile(ArrayList<Variable> list) {
	    try {
	        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
	        DocumentBuilder build = dFact.newDocumentBuilder();
	        Document doc = build.newDocument();
	        Element root = doc.createElement("Studentinfo");
	        doc.appendChild(root);
	        Element Details = doc.createElement("Details");
	        root.appendChild(Details);
	        for (Variable dtl : list) {
	            Element name = doc.createElement("Name");
	            name.appendChild(doc.createTextNode(String.valueOf(dtl.getName())));
	            Details.appendChild(name);
	            Element type = doc.createElement("Type");
	            type.appendChild(doc.createTextNode(String.valueOf(dtl.getType())));
	            Details.appendChild(type);
	            Element min = doc.createElement("Min");
	            min.appendChild(doc.createTextNode(String.valueOf(dtl.getMin())));
	            Details.appendChild(min);
	            Element max = doc.createElement("Max");
	            max.appendChild(doc.createTextNode(String.valueOf(dtl.getMax())));
	            Details.appendChild(max);
	        }
	        // Save the document to the disk file
	        TransformerFactory tranFactory = TransformerFactory.newInstance();
	        Transformer aTransformer = tranFactory.newTransformer();
	        // format the XML nicely
	        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
	        aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        try {
	            // location and name of XML file you can change as per need
	        	//WRITE DOWN WHERE YOU WANT TO SAVE YOUR XML FILE
	            FileWriter fos = new FileWriter("/Users/albertoramos/Desktop/File.xml");
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
	
	
}
