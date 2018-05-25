package Interface;

import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import General.SharedClass;
import General.Variable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.text.LabelView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;

public class GUIVariables extends JFrame {
	// test-
	private JPanel contentPane;
	private JTextField TFName;
	private JTextField TFMin;
	private JTextField TFMax;
	private String textAreaStringIntDouble = "Name:		Type:		Interval:";
	private String textAreaStringBinary = "Name:		Type:  		Value:";
	private JTextArea textArea;
	private ArrayList<Variable> variablesArray = new ArrayList<Variable>();
	private JComboBox comboBox;
	private SharedClass shared;
	private String variableName;
	private String variableType;
	private String variableMin;
	private String variableMax;
	private JButton ButtonAddVariable;
	private JTextField TFValue;
	private JLabel LabelValue;
	private JLabel LabelInterval;

	public GUIVariables(SharedClass shared) {
		this.shared = shared;

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

		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 128, 128));
		comboBox.setBounds(40, 112, 169, 27);
		contentPane.add(comboBox);
		comboBox.addItem(new String(" "));
		comboBox.addItem(new String("Integer"));
		comboBox.addItem(new String("Double"));
		comboBox.addItem(new String("Binary"));
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				checkSelectedComboBox();
			}
		});

		JLabel LabelVariablesList = new JLabel("Variables List:");
		LabelVariablesList.setForeground(Color.WHITE);
		LabelVariablesList.setFont(new Font("Avenir Next", Font.BOLD, 16));
		LabelVariablesList.setBounds(44, 183, 161, 16);
		contentPane.add(LabelVariablesList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 211, 598, 173);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setForeground(new Color(0, 128, 128));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		setDefaultTAText();

		LabelInterval = new JLabel("Interval (Integer & Double) :");
		LabelInterval.setForeground(Color.WHITE);
		LabelInterval.setFont(new Font("Avenir Next", Font.BOLD, 13));
		LabelInterval.setBounds(273, 33, 176, 16);
		LabelInterval.setVisible(false);
		contentPane.add(LabelInterval);

		TFMin = new JTextField();
		TFMin.setForeground(new Color(0, 128, 128));
		TFMin.setBounds(273, 51, 46, 26);
		TFMin.setText("");
		TFMin.setColumns(10);
		TFMin.setVisible(false);
		contentPane.add(TFMin);

		TFMax = new JTextField();
		TFMax.setForeground(new Color(0, 128, 128));
		TFMax.setColumns(10);
		TFMax.setBounds(331, 51, 46, 26);
		TFMax.setText("");
		TFMax.setVisible(false);
		contentPane.add(TFMax);

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
		BotaoNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shared.getProblem().setVariablesArray(variablesArray);
				shared.setGUIRestrictions();
				shared.setExistingPanel(shared.getNPArray(), 2);
				shared.verifyType();

			}
		});
		contentPane.add(BotaoNext);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(40);
		contentPane.add(progressBar);

		ButtonAddVariable = new JButton("Add Variable");
		ButtonAddVariable.setForeground(new Color(0, 128, 128));
		ButtonAddVariable.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		ButtonAddVariable.setBounds(273, 154, 104, 29);
		contentPane.add(ButtonAddVariable);
		ButtonAddVariable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validateDetails();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel LabelInformation = new JLabel("<html>Note: <br/>Optimization means minimize the objectives given</html>",
				SwingConstants.CENTER);
		LabelInformation.setForeground(Color.WHITE);
		LabelInformation.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		LabelInformation.setBounds(531, 1, 169, 76);
		contentPane.add(LabelInformation);

		JButton ButtonClearAll = new JButton("Clear All");
		ButtonClearAll.setForeground(new Color(0, 128, 128));
		ButtonClearAll.setFont(new Font("Avenir", Font.PLAIN, 12));
		ButtonClearAll.setBounds(566, 177, 76, 29);
		contentPane.add(ButtonClearAll);
		ButtonClearAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setVerifyLoad(false);
				shared.getProblem().getVariablesArray().clear();
				variablesArray.clear();
				textAreaStringIntDouble = "Name:		Type:		Interval:";
				textAreaStringBinary = "Name:		Type:  		Value:";
				if (comboBox.getSelectedItem().equals("Integer") || comboBox.getSelectedItem().equals("Double")) {
					textArea.setText(textAreaStringIntDouble);
				}
				if (comboBox.getSelectedItem().equals("Binary")) {
					textArea.setText(textAreaStringBinary);
					shared.setBinaryVariableSize(0);
				}
				comboBox.setEnabled(true);
			}
		});
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));

		LabelValue = new JLabel("Value (Binary) :");
		LabelValue.setForeground(Color.WHITE);
		LabelValue.setFont(new Font("Avenir Next", Font.BOLD, 13));
		LabelValue.setBounds(273, 33, 176, 16);
		LabelValue.setVisible(false);
		contentPane.add(LabelValue);

		TFValue = new JTextField();
		TFValue.setText("");
		TFValue.setForeground(new Color(0, 128, 128));
		TFValue.setColumns(10);
		TFValue.setBounds(273, 51, 76, 26);
		TFValue.setVisible(false);
		contentPane.add(TFValue);

		JLabel LabelLogo = new JLabel();
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void validateDetails() throws SAXException, IOException {
		if (comboBox.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "You must fill all the mandatory variables.");
		} else if ((comboBox.getSelectedItem().equals("Integer") || comboBox.getSelectedItem().equals("Double"))
				&& (TFName.getText().equals("") || TFMin.getText().equals("") || TFMax.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "You must fill all the mandatory variables.");
		} else if (comboBox.getSelectedItem().equals("Binary")
				&& (TFName.getText().equals("") || TFValue.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "You must fill all the mandatory variables.");
		} else if ((comboBox.getSelectedItem().equals("Integer") || comboBox.getSelectedItem().equals("Double"))
				&& (!TFName.getText().equals("") && !TFMin.getText().equals("") && !TFMax.getText().equals(""))) {
			validateVariableIntDouble(TFMin, TFMax);
		} else if ((comboBox.getSelectedItem().equals("Binary"))
				&& (!TFName.getText().equals("") && !TFValue.equals(""))) {
			validateVariableBinary(TFValue);
		}
	}

	public void validateVariableIntDouble(JTextField tfmin, JTextField tfmax) {
		boolean b = false;
		if (comboBox.getSelectedItem().equals("Integer")) {
			try {
				// is an integer!
				int num1 = Integer.parseInt(tfmin.getText());
				int num2 = Integer.parseInt(tfmax.getText());
				if (num1 < num2 && validateName(TFName.getText())) {
					b = true;
					Variable v;
					if(/*getQuantaty */30>1) {
						for(int q=1;q<=/*getQuantaty*/30;q++) {
						v = new Variable(TFName.getText()+"_"+q, (String) comboBox.getSelectedItem(),
								Integer.parseInt(TFMin.getText()), Integer.parseInt(TFMax.getText()),
								new ArrayList<Integer>(), false);
						shared.getProblem().getVariablesArray().add(v);
						shared.getProblem().setType((String) comboBox.getSelectedItem());
						variablesArray.add(v);
						textAreaStringIntDouble += "\n" + v.toStringVariable();
						textArea.setText(textAreaStringIntDouble);
						}
					}else {
						v = new Variable(TFName.getText(), (String) comboBox.getSelectedItem(),
								Integer.parseInt(TFMin.getText()), Integer.parseInt(TFMax.getText()),
								new ArrayList<Integer>(), false);
						shared.getProblem().getVariablesArray().add(v);
						shared.getProblem().setType((String) comboBox.getSelectedItem());
						variablesArray.add(v);
						textAreaStringIntDouble += "\n" + v.toStringVariable();
						textArea.setText(textAreaStringIntDouble);
					}

				} else if (num1 > num2) {
					JOptionPane.showMessageDialog(null, "The Minimum value must be lower than the Maximum value.");
				}
			} catch (NumberFormatException e) {
				b = false;
				JOptionPane.showMessageDialog(null, "The variable interval must be Integer.");
				// not an integer!!
			}
		}
		if (comboBox.getSelectedItem().equals("Double")) {
			try {
				double num1 = Double.parseDouble(tfmin.getText());
				double num2 = Double.parseDouble(tfmax.getText());
				if (num1 < num2 && validateName(TFName.getText())) {
					b = true;
					Variable v = new Variable(TFName.getText(), (String) comboBox.getSelectedItem(),
							Double.parseDouble(TFMin.getText()), Double.parseDouble(TFMax.getText()),
							new ArrayList<Double>(), false);
					textAreaStringIntDouble += "\n" + v.toStringVariable();
					variablesArray.add(v);
					shared.getProblem().setType((String) comboBox.getSelectedItem());
					textArea.setText(textAreaStringIntDouble);
				} else if (num1 > num2) {
					JOptionPane.showMessageDialog(null, "The Minimum value must be lower than the Maximum value.");
				}

				// is an double!
			} catch (NumberFormatException e) {
				b = false;
				JOptionPane.showMessageDialog(null, "The variable interval must be Double.");
				// not an double!
			}
		}
		if (b == true) {
			comboBox.setEnabled(false);
		}
	}

	public void validateVariableBinary(JTextField tf) {
		boolean b = false;
		if (comboBox.getSelectedItem().equals("Binary")) {
			if (TFValue.getText().matches("[01]+") && validateName(TFName.getText())) {
				if (variablesArray.size() == 0) {
					shared.setBinaryVariableSize((TFValue.getText().length()));
					b = true;
					Variable v = new Variable(TFName.getText(), (String) comboBox.getSelectedItem(), TFValue.getText(),
							false);
					textAreaStringBinary += "\n" + v.toStringVariable();
					variablesArray.add(v);
					shared.getProblem().setType((String) comboBox.getSelectedItem());
					textArea.setText(textAreaStringBinary);
				} else if (variablesArray.size() > 0 && TFValue.getText().length() == shared.getBinaryVariableSize()) {
					b = true;
					Variable v = new Variable(TFName.getText(), (String) comboBox.getSelectedItem(), TFValue.getText(),
							false);
					textAreaStringBinary += "\n" + v.toStringVariable();
					variablesArray.add(v);
					shared.getProblem().setType((String) comboBox.getSelectedItem());
					textArea.setText(textAreaStringBinary);
				} else if (variablesArray.size() > 0 && TFValue.getText().length() != shared.getBinaryVariableSize()) {
					JOptionPane.showMessageDialog(null, "The value length must be: " + shared.getBinaryVariableSize());
				}

				// is a bitstring!
			} else {
				b = false;
				JOptionPane.showMessageDialog(null, "The variable interval must be Binary.");
				// not an bitstring !
			}
		}
		if (b == true) {
			comboBox.setEnabled(false);
		}
	}

	public boolean validateName(String name) {
		Boolean b = true;
		for (int i = 0; i < variablesArray.size(); i++) {
			if (variablesArray.get(i).getName().equals(name)) {
				b = false;
				JOptionPane.showMessageDialog(null, "There is already a variable with that name.");
			}
		}
		return b;
	}

	public void checkSelectedComboBox() {
		if (comboBox.getSelectedItem().equals("Integer") || comboBox.getSelectedItem().equals("Double")) {
			TFMax.setVisible(true);
			TFMin.setVisible(true);
			LabelValue.setVisible(false);
			TFValue.setVisible(false);
			TFValue.setText("");
			LabelInterval.setVisible(true);
			textArea.setText(textAreaStringIntDouble);
		} else if (comboBox.getSelectedItem().equals("Binary")) {
			TFMax.setVisible(false);
			TFMin.setVisible(false);
			LabelInterval.setVisible(false);
			TFValue.setVisible(true);
			LabelValue.setVisible(true);
			textArea.setText(textAreaStringBinary);

		} else if (comboBox.getSelectedItem().equals(" ")) {
			TFMax.setVisible(false);
			TFMin.setVisible(false);
			TFMin.setText("");
			TFMax.setText("");
			LabelInterval.setVisible(false);
			TFValue.setVisible(false);
			LabelValue.setVisible(false);
			textArea.setText("");
		}
	}

	public void setDefaultTAText() {
		if (comboBox.getSelectedItem().equals("")) {
			textArea.setText("");
		} else if (comboBox.getSelectedItem().equals("Integer") || comboBox.getSelectedItem().equals("Double")) {
			textArea.setText(textAreaStringIntDouble);
		} else if (comboBox.getSelectedItem().equals("Binary")) {
			textArea.setText(textAreaStringBinary);
		}
	}

	public ArrayList<Variable> getVariablesArray() {
		return variablesArray;
	}

	public void setVariablesArray(ArrayList<Variable> variablesArray) {
		this.variablesArray = variablesArray;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public String getVariableMin() {
		return variableMin;
	}

	public void setVariableMin(String variableMin) {
		this.variableMin = variableMin;
	}

	public String getVariableMax() {
		return variableMax;
	}

	public void setVariableMax(String variableMax) {
		this.variableMax = variableMax;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextAreaText(String s) {
		textArea.setText(s);
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBoxValue(String valueCombo) {
		comboBox.setSelectedItem(valueCombo);
	}

	public String getTextAreaStringIntDouble() {
		return textAreaStringIntDouble;
	}
	public String getTextAreaStringBinary() {
		return textAreaStringBinary;
	}
	public void setTextAreaStringBinary(String toadd) {
		textAreaStringBinary = toadd;
	}

	public void setTextAreaStringIntDouble(String toadd) {
		textAreaStringIntDouble = toadd;
	}
}
