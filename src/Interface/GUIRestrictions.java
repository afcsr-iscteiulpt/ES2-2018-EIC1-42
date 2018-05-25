package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import General.SharedClass;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUIRestrictions extends JFrame {

	private SharedClass shared;
	private JPanel contentPane;
	private JTextField TFName;
	private JTextField TFRestrictions;
	private DefaultListModel<String> model;
	private JList<String> list;
	private JLabel LabelName;
	private JLabel LabelInterval;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private JLabel LabelObjective;
	private JTextField TFObjective;
	private JButton ButtonAddObj;
	private JTextArea TAObjectives;
	private String TAObjectivesText = "Objective name:";
	private JScrollPane scrollPane_1;
	private ArrayList<String> objectivesArray = new ArrayList<String>();

	public GUIRestrictions(SharedClass shared) {
		this.shared = shared;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(60);
		contentPane.add(progressBar);

		JButton BotaoBack = new JButton("◀");
		BotaoBack.setBounds(34, 401, 53, 35);
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setExistingPanel(shared.getNPArray(), 1);
			}
		});
		contentPane.add(BotaoBack);

		JButton BotaoNext = new JButton("▶");
		BotaoNext.setBounds(614, 401, 53, 35);
		BotaoNext.setForeground(new Color(0, 128, 128));
		BotaoNext.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setExistingPanel(shared.getNPArray(), 3);

			}
		});
		contentPane.add(BotaoNext);

		TFName = new JTextField();
		TFName.setEnabled(false);
		TFName.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		TFName.setBounds(260, 73, 221, 35);
		contentPane.add(TFName);
		TFName.setColumns(10);

		LabelName = new JLabel("Variable selected:");
		LabelName.setForeground(new Color(47, 79, 79));
		LabelName.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		LabelName.setBounds(263, 55, 154, 16);
		contentPane.add(LabelName);

		LabelInterval = new JLabel("Remove values from interval:");
		LabelInterval.setForeground(new Color(47, 79, 79));
		LabelInterval.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		LabelInterval.setBounds(264, 112, 267, 16);
		contentPane.add(LabelInterval);

		TFRestrictions = new JTextField();
		TFRestrictions.setEnabled(false);
		TFRestrictions.setFont(new Font("Avenir Next", Font.PLAIN, 9));
		TFRestrictions.setColumns(10);
		TFRestrictions.setBounds(262, 132, 298, 35);
		contentPane.add(TFRestrictions);

		Border border = BorderFactory.createLineBorder(new Color(0, 128, 128));
		model = new DefaultListModel<>();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 55, 214, 322);
		contentPane.add(scrollPane);
		list = new JList<>(model);
		scrollPane.setViewportView(list);
		list.setForeground(new Color(47, 79, 79));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TFName.setText(list.getSelectedValue());
				convertIntervalToString(list.getSelectedValue());
			}
		});
		list.setBorder(border);

		JButton ButtonSet = new JButton("Set restriction");
		ButtonSet.setForeground(new Color(0, 128, 128));
		ButtonSet.setBounds(562, 132, 112, 35);
		ButtonSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				validateChanges();

			}
		});
		contentPane.add(ButtonSet);

		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));

		JLabel LabelRestrictions = new JLabel("Apply your restrictions");
		LabelRestrictions.setForeground(Color.WHITE);
		LabelRestrictions.setFont(new Font("Avenir Next", Font.BOLD, 29));
		LabelRestrictions.setBounds(34, 6, 445, 49);
		contentPane.add(LabelRestrictions);
		
		separator = new JSeparator();
		separator.setForeground(new Color(0, 128, 128));
		separator.setBounds(257, 176, 429, 12);
		contentPane.add(separator);
		
		LabelObjective = new JLabel("Objective name:");
		LabelObjective.setForeground(new Color(255, 255, 255));
		LabelObjective.setFont(new Font("Avenir Next", Font.BOLD, 16));
		LabelObjective.setBounds(264, 188, 133, 26);
		contentPane.add(LabelObjective);
		
		TFObjective = new JTextField();
		TFObjective.setBounds(262, 214, 178, 29);
		contentPane.add(TFObjective);
		TFObjective.setColumns(10);
		
		ButtonAddObj = new JButton("Add Objective");
		ButtonAddObj.setForeground(new Color(0, 128, 128));
		ButtonAddObj.setBounds(437, 213, 112, 32);
		ButtonAddObj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				validateObjectName();
				
			}
		});
		contentPane.add(ButtonAddObj);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(263, 255, 423, 122);
		contentPane.add(scrollPane_1);
		
		TAObjectives = new JTextArea();
		TAObjectives.setText(TAObjectivesText);
		scrollPane_1.setViewportView(TAObjectives);
		JLabel LabelLogo = new JLabel();
		LabelLogo.setBounds(0, 0, 700, 478);
		LabelLogo.setIcon(imageIcon);
		contentPane.add(LabelLogo);

	}

	public void convertIntervalToString(String var) {
		boolean b = false;
		String intervalString = "[ ";
		for (int i = 0; i < shared.getProblem().getVariablesArray().size(); i++) {
			if (shared.getProblem().getVariablesArray().get(i).getName().equals(var)) {
				if (shared.getProblem().getVariablesArray().get(i).getType().equals("Integer")) {
					int min = shared.getProblem().getVariablesArray().get(i).getMinI();
					int max = shared.getProblem().getVariablesArray().get(i).getMaxI();
					for (int j = min; j <= max; j++) {
						if (j != max) {
							intervalString += j + " , ";
						} else {
							intervalString += j + " ]";
						}
					}
				}
				if (shared.getProblem().getVariablesArray().get(i).getType().equals("Double")) {
					double min = shared.getProblem().getVariablesArray().get(i).getMinD();
					double max = shared.getProblem().getVariablesArray().get(i).getMaxD();
					for (double j = min; j <= max; j++) {
						if (j != max) {
							intervalString += j + " , ";
						} else {
							intervalString += j + " ]";
						}
					}
				}
			}
		}
		TFRestrictions.setText(intervalString);

	}

	public void validateChanges() {

	}
	
	public void validateObjectName(){
		if(!TFObjective.getText().equals("")){
			shared.getProblem().getObjectivesArray().add(TFObjective.getText());
			TAObjectivesText += "\n" + TFObjective.getText();
			TAObjectives.setText(TAObjectivesText);
		}
		else{
			JOptionPane.showMessageDialog(null, "Please give your objective a name.");
		}
	}

	public void it_is_a_Binary() {
		LabelName.setVisible(false);
		TFName.setVisible(false);
		LabelInterval.setVisible(false);
		TFRestrictions.setVisible(false);

	}
	
	public JTextArea getTAObjectives(){
		return TAObjectives;
	}
	
	public void setTAObjectivesText(String toAdd){
		TAObjectives.setText(toAdd);
	}

	public DefaultListModel<String> getModel() {
		return model;
	}

	public JPanel getContentPane() {
		return contentPane;
	}
}
