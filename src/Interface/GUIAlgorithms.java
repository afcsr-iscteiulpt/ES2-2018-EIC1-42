package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import General.Configuration;
import General.ConfigurationBinaryP;
import General.ConfigurationDoubleP;
import General.ConfigurationIntegerP;
import General.DoubleProblem;
import General.SharedClass;
import General.Variable;

import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.abyss.ABYSS;
import org.uma.jmetal.algorithm.multiobjective.abyss.ABYSSBuilder;
import org.uma.jmetal.algorithm.multiobjective.cellde.CellDE45;
import org.uma.jmetal.algorithm.multiobjective.dmopso.DMOPSO;
import org.uma.jmetal.algorithm.multiobjective.dmopso.DMOPSOBuilder;
import org.uma.jmetal.algorithm.multiobjective.gde3.GDE3;
import org.uma.jmetal.algorithm.multiobjective.gde3.GDE3Builder;
import org.uma.jmetal.algorithm.multiobjective.gwasfga.GWASFGA;
import org.uma.jmetal.algorithm.multiobjective.ibea.IBEA;
import org.uma.jmetal.algorithm.multiobjective.ibea.IBEABuilder;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCell;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHC;
import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHCBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.AbstractMOEAD;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEAD;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder.Variant;
import org.uma.jmetal.algorithm.multiobjective.mombi.MOMBI;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIII;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.omopso.OMOPSO;
import org.uma.jmetal.algorithm.multiobjective.omopso.OMOPSOBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAES;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2Builder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearch;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAII;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.smpso.SMPSO;
import org.uma.jmetal.algorithm.multiobjective.smpso.SMPSOBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOA;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.algorithm.multiobjective.wasfga.WASFGA;
import org.uma.jmetal.algorithm.singleobjective.coralreefsoptimization.CoralReefsOptimization;
import org.uma.jmetal.algorithm.singleobjective.coralreefsoptimization.CoralReefsOptimizationBuilder;
import org.uma.jmetal.algorithm.singleobjective.differentialevolution.DifferentialEvolution;
import org.uma.jmetal.algorithm.singleobjective.differentialevolution.DifferentialEvolutionBuilder;
import org.uma.jmetal.algorithm.singleobjective.evolutionstrategy.ElitistEvolutionStrategy;
import org.uma.jmetal.algorithm.singleobjective.evolutionstrategy.EvolutionStrategyBuilder;
import org.uma.jmetal.algorithm.singleobjective.evolutionstrategy.EvolutionStrategyBuilder.EvolutionStrategyVariant;
import org.uma.jmetal.algorithm.singleobjective.evolutionstrategy.NonElitistEvolutionStrategy;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GenerationalGeneticAlgorithm;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GeneticAlgorithmBuilder;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.SteadyStateGeneticAlgorithm;
import org.uma.jmetal.algorithm.multiobjective.dmopso.DMOPSO.FunctionType;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Checkbox;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class GUIAlgorithms extends JFrame {

	private JPanel contentPane;
	private SharedClass shared;
	private JLabel LabelAuto = new JLabel("Your algorithms will be choosen automatically");
	private JComboBox CBMulti = new JComboBox();
	private JTextArea TAManu = new JTextArea();
	private JTextArea TAMixed = new JTextArea();
	private JButton ButtonAddMulti = new JButton("Add");
	private JButton ButtonAddMixed = new JButton("Add");

	private String TAText = "Algorithms chosen: " + "\n";
	private String TAMixedText = "Algorithms chosen: " + "\n";
	private JTextField Time;
	private JButton ButtonClearAll;
	private JButton ButtonManu;
	private JComboBox comboBoxMixed = new JComboBox();
	private JButton ButtonClearAllMixed;
	private int numberOfDays;

	private ArrayList<String> SELECTEDAlgorithmsArray = new ArrayList<>();
	private static ArrayList<String> multiAlgorithmsArray = new ArrayList<>();

	public GUIAlgorithms(SharedClass shared) {

		addMultiAlgorithmsToArray();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LabelTime = new JLabel("How long are you willing to wait?");
		LabelTime.setForeground(Color.WHITE);
		LabelTime.setFont(new Font("Avenir Next", Font.BOLD, 15));
		LabelTime.setBounds(20, 17, 290, 16);
		contentPane.add(LabelTime);

		JLabel LabelTime2 = new JLabel(
				"Type bellow the amount of days you are willing to wait for an optimal solution to your problem.");
		LabelTime2.setForeground(new Color(47, 79, 79));
		LabelTime2.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		LabelTime2.setBounds(20, 34, 630, 16);
		contentPane.add(LabelTime2);

		Time = new JTextField();
		Time.setBounds(20, 51, 77, 26);
		contentPane.add(Time);
		Time.setColumns(10);

		JLabel LabelTime3 = new JLabel("Days");
		LabelTime3.setForeground(new Color(47, 79, 79));
		LabelTime3.setBounds(99, 56, 61, 16);
		contentPane.add(LabelTime3);

		JButton ButtonTime = new JButton("Set");
		ButtonTime.setForeground(new Color(47, 79, 79));
		ButtonTime.setBounds(133, 51, 61, 29);
		contentPane.add(ButtonTime);
		ButtonTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int numberOfDaysTEMP = Integer.parseInt(Time.getText());
					numberOfDays = numberOfDaysTEMP;
					shared.getProblem().setNumberOfDays(numberOfDays);
					// is an integer!

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "The number of days must be Integer.");
					// not an integer!
				}

			}
		});

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 139, 139));
		separator.setBounds(0, 84, 700, 12);
		contentPane.add(separator);

		CBMulti.setBounds(20, 195, 208, 27);
		CBMulti.setEnabled(false);
		contentPane.add(CBMulti);

		JLabel LabelMOA = new JLabel("Multi-objective algoritms");
		LabelMOA.setForeground(new Color(47, 79, 79));
		LabelMOA.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		LabelMOA.setBounds(30, 178, 206, 16);
		contentPane.add(LabelMOA);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 234, 306, 122);
		contentPane.add(scrollPane);
		TAManu.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		scrollPane.setViewportView(TAManu);

		TAManu.setText(TAText);
		TAManu.setEnabled(false);

		ButtonAddMulti.setForeground(new Color(47, 79, 79));
		ButtonAddMulti.setBounds(227, 194, 99, 29);
		ButtonAddMulti.setEnabled(false);
		ButtonAddMulti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setVerifyLoad(false);
				String toAdd = (String) CBMulti.getSelectedItem();
				if (!toAdd.equals("") && checkIfThisStringExists(toAdd, "Manually") == false) {
					SELECTEDAlgorithmsArray.add(toAdd);
					addAlgorithm(toAdd, "Manually");
				}
			}
		});
		contentPane.add(ButtonAddMulti);

		ButtonManu = new JButton("Manually");
		ButtonManu.setBackground(Color.WHITE);
		ButtonManu.setForeground(new Color(47, 79, 79));
		ButtonManu.setBounds(20, 145, 290, 29);
		ButtonManu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disableOrEnable("Manually");
			}
		});
		contentPane.add(ButtonManu);

		JButton ButtonAuto = new JButton("Automatically");
		ButtonAuto.setForeground(new Color(47, 79, 79));
		ButtonAuto.setBounds(377, 145, 290, 29);
		ButtonAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.getProblem().getAlgorithms().clear();
				disableOrEnable("Automatically");
			}
		});
		contentPane.add(ButtonAuto);
		LabelAuto.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		LabelAuto.setForeground(new Color(47, 79, 79));

		LabelAuto.setBounds(377, 171, 300, 16);
		contentPane.add(LabelAuto);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(338, 145, 11, 237);
		contentPane.add(separator_1);

		JLabel LabelInfo = new JLabel("How will your algorithms be chosen?");
		LabelInfo.setForeground(new Color(255, 255, 255));
		LabelInfo.setFont(new Font("Avenir Next", Font.BOLD, 16));
		LabelInfo.setBounds(196, 97, 423, 16);
		contentPane.add(LabelInfo);

		JLabel LabelInfo2 = new JLabel("Press one of the buttons to choose");
		LabelInfo2.setForeground(new Color(47, 79, 79));
		LabelInfo2.setBounds(227, 117, 223, 16);
		contentPane.add(LabelInfo2);

		JButton BotaoBack = new JButton("◀");
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.setBounds(34, 401, 53, 35);
		BotaoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setExistingPanel(shared.getNPArray(), 2);
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
				if (shared.getVerifyLoad() == false) {
					shared.getProblem().setNumberOfDays(numberOfDays);
					shared.getProblem().setAlgorithms(SELECTEDAlgorithmsArray);
				}
				shared.setReviewProblem();
				shared.setExistingPanel(shared.getNPArray(), 4);
			}
		});
		contentPane.add(BotaoNext);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(80);
		contentPane.add(progressBar);
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));

		ButtonClearAll = new JButton("Clear All");
		ButtonClearAll.setForeground(new Color(47, 79, 79));
		ButtonClearAll.setBounds(227, 356, 99, 29);
		contentPane.add(ButtonClearAll);
		ButtonClearAll.setEnabled(false);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(342, 196, 336, 12);
		contentPane.add(separator_2);

		JButton ButtonMixed = new JButton("Mixed");
		ButtonMixed.setForeground(new Color(47, 79, 79));
		ButtonMixed.setBackground(Color.WHITE);
		ButtonMixed.setBounds(377, 209, 290, 29);
		ButtonMixed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				disableOrEnable("Mixed");
				
			}
		});
		contentPane.add(ButtonMixed);

		JLabel LabelMixed = new JLabel(
				"<html>Your algorithms will be choosen both </br> automatically and manually </html>");
		LabelMixed.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		LabelMixed.setForeground(new Color(47, 79, 79));
		LabelMixed.setBounds(361, 234, 339, 16);
		contentPane.add(LabelMixed);

		JLabel LabelMixedChose = new JLabel("Multi-objective algoritms");
		LabelMixedChose.setForeground(new Color(47, 79, 79));
		LabelMixedChose.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		LabelMixedChose.setBounds(371, 255, 206, 16);
		contentPane.add(LabelMixedChose);

		comboBoxMixed.setEnabled(false);
		comboBoxMixed.setBounds(361, 272, 208, 27);
		contentPane.add(comboBoxMixed);

		ButtonAddMixed.setForeground(new Color(47, 79, 79));
		ButtonAddMixed.setEnabled(false);
		ButtonAddMixed.setBounds(568, 271, 99, 29);
		ButtonAddMixed.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setVerifyLoad(false);
				String toAdd = (String) comboBoxMixed.getSelectedItem();
				if (!toAdd.equals("") && checkIfThisStringExists(toAdd, "Mixed") == false) {
					SELECTEDAlgorithmsArray.add(toAdd);
					addAlgorithm(toAdd, "Mixed");
				}				
			}
		});
		contentPane.add(ButtonAddMixed);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(365, 300, 302, 56);
		contentPane.add(scrollPane_1);

		TAMixed.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		scrollPane_1.setViewportView(TAMixed);
		TAMixed.setText("Algorithms chosen: \n");
		TAMixed.setEnabled(false);

		ButtonClearAllMixed = new JButton("Clear All");
		ButtonClearAllMixed.setForeground(new Color(47, 79, 79));
		ButtonClearAllMixed.setEnabled(false);
		ButtonClearAllMixed.setBounds(361, 356, 99, 29);
		ButtonClearAllMixed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SELECTEDAlgorithmsArray.clear();
				shared.getProblem().getAlgorithms().clear();
				TAMixedText = "Algorithms chosen: " + "\n";
				TAMixed.setText(TAMixedText);
			}
		});
		contentPane.add(ButtonClearAllMixed);

		JLabel LabelLogo = new JLabel();
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
		ButtonClearAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SELECTEDAlgorithmsArray.clear();
				shared.getProblem().getAlgorithms().clear();
				TAText = "Algorithms chosen: " + "\n";
				TAManu.setText(TAText);
			}
		});

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void addMultiAlgorithmsToArray() {
		multiAlgorithmsArray.add("ABYSS");
		multiAlgorithmsArray.add("CellDE45");
		multiAlgorithmsArray.add("DMOPSO");
		multiAlgorithmsArray.add("GDE3");
		multiAlgorithmsArray.add("GWASFGA");
		multiAlgorithmsArray.add("IBEA");
		multiAlgorithmsArray.add("MOCell");
		multiAlgorithmsArray.add("MOCH");
		multiAlgorithmsArray.add("MOEAD");
		multiAlgorithmsArray.add("MOMBI");
		multiAlgorithmsArray.add("NSGAII");
		multiAlgorithmsArray.add("NSGAIII");
		multiAlgorithmsArray.add("OMOPSO");
		multiAlgorithmsArray.add("PAES");
		multiAlgorithmsArray.add("PESA2");
		multiAlgorithmsArray.add("RandomSearch");
		multiAlgorithmsArray.add("RNSGAII");
		multiAlgorithmsArray.add("SMPSO");
		multiAlgorithmsArray.add("SMSEMOA");
		multiAlgorithmsArray.add("SPEA2");
		multiAlgorithmsArray.add("WASFGA");
		CBMulti.addItem(new String(""));
		comboBoxMixed.addItem(new String(""));
		for (int i = 0; i < multiAlgorithmsArray.size(); i++) {
			CBMulti.addItem(multiAlgorithmsArray.get(i));
			comboBoxMixed.addItem(multiAlgorithmsArray.get(i));
		}
	}

	public void enableAutomatically() {
		LabelAuto.setForeground(new Color(58, 153, 58));
	}

	public void disableAuto(){
		LabelAuto.setForeground(Color.RED);
	}
	

	public void addAlgorithm(String toAdd, String mixedOrManu) {
		if(mixedOrManu.equals("Manually")){
			TAManu.setText(TAText + toAdd + "\n");
			TAText = TAText + toAdd + "\n";
		}
		else if(mixedOrManu.equals("Mixed")){
			TAMixed.setText(TAMixedText + toAdd + "\n");
			TAMixedText = TAMixedText + toAdd + "\n";
		}
	}

	public void disableManually() {
		CBMulti.setEnabled(false);
		CBMulti.setSelectedItem("");
		ButtonClearAll.setEnabled(false);
		ButtonAddMulti.setEnabled(false);
		TAText = "Algorithms chosen: " + "\n";
		TAManu.setText(TAText);
	}

	public void disableMixed(){
		comboBoxMixed.setEnabled(false);
		comboBoxMixed.setSelectedItem("");
		ButtonClearAllMixed.setEnabled(false);
		ButtonAddMixed.setEnabled(false);
		TAMixedText = "Algorithms chosen: " + "\n";
		TAMixed.setText(TAMixedText);
	}

	public void enableMixed(){
		comboBoxMixed.setEnabled(true);
		ButtonClearAllMixed.setEnabled(true);
		ButtonAddMixed.setEnabled(true);
	}
	
	public void enableManually() {
		CBMulti.setEnabled(true);
		ButtonAddMulti.setEnabled(true);
		ButtonClearAll.setEnabled(true);
	}

	public void disableOrEnable(String button) {
		if (button.equals("Automatically")) {
			enableAutomatically();
			disableManually();
			disableMixed();
		} else if (button.equals("Manually")) {
			enableManually();
			disableAuto();
			disableMixed();
		} else if (button.equals("Mixed")) {
			enableMixed();
			disableManually();
			disableAuto();
		}
		
	}

	public boolean checkIfThisStringExists(String s, String mixedOrmanu) {
		boolean b = false;
		if(mixedOrmanu.equals("Manually")){
			if (TAText.toLowerCase().contains(s.toLowerCase())) {
				b = true;
				JOptionPane.showMessageDialog(null, "Algorithm already added.");
			}
		}
		else if(mixedOrmanu.equals("Mixed")){
			if (TAMixedText.toLowerCase().contains(s.toLowerCase())) {
				b = true;
				JOptionPane.showMessageDialog(null, "Algorithm already added.");
			}
		}	
		return b;
	}

	public ArrayList<String> getAlgorithmsSelected() {
		return SELECTEDAlgorithmsArray;
	}

	public JTextArea getTAManu() {
		return TAManu;
	}

	public JTextField getTFTime() {
		return Time;
	}

	public void setDays(int days) {
		this.numberOfDays = days;
	}

	public void setAlgorithms(ArrayList<String> algorithms) {
		SELECTEDAlgorithmsArray.clear();
		SELECTEDAlgorithmsArray = algorithms;
	}
}