package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import General.Configuration;
import General.ConfigurationDoubleP;
import General.DoubleProblem;
import General.SharedClass;
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
	private JButton ButtonAddMulti = new JButton("Add");
	private String TAText = "Algorithms chosen: "+"\n";
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
		
		JLabel LabelTime2 = new JLabel("Type bellow the amount of days you are willing to wait for an optimal solution to your problem.");
		LabelTime2.setForeground(new Color(47, 79, 79));
		LabelTime2.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		LabelTime2.setBounds(20, 34, 630, 16);
		contentPane.add(LabelTime2);
		
		JTextField Time = new JTextField();
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
				numberOfDays = Integer.parseInt(Time.getText());
				shared.getProblem().setNumberOfDays(numberOfDays);
				
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 139, 139));
		separator.setBounds(0, 84, 700, 12);
		contentPane.add(separator);	
		
		CBMulti.setBounds(20, 206, 208, 27);
		contentPane.add(CBMulti);
		
		JLabel LabelMOA = new JLabel("Multi-objective algoritms");
		LabelMOA.setForeground(new Color(47, 79, 79));
		LabelMOA.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		LabelMOA.setBounds(30, 186, 206, 16);
		contentPane.add(LabelMOA);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 270, 306, 119);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(TAManu);
		
		TAManu.setText(TAText);
		TAManu.setEnabled(false);
		ButtonAddMulti.setForeground(new Color(47, 79, 79));
		
		ButtonAddMulti.setBounds(227, 205, 99, 29);
		ButtonAddMulti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String toAdd = (String)CBMulti.getSelectedItem();
				if(!toAdd.equals("") && checkIfThisStringExists(toAdd) == false ){
					SELECTEDAlgorithmsArray.add(toAdd);
					TAManu.setText(TAText + toAdd +"\n");
					TAText = TAText + toAdd +"\n";
				}
			}
		});
		contentPane.add(ButtonAddMulti);
		
		JButton ButtonManu = new JButton("Manually");
		ButtonManu.setForeground(new Color(47, 79, 79));
		ButtonManu.setBounds(20, 145, 290, 29);
		ButtonManu.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manuallySelected();
			}
		});
		contentPane.add(ButtonManu);
		
		JButton ButtonAuto = new JButton("Automatically");
		ButtonAuto.setForeground(new Color(47, 79, 79));
		ButtonAuto.setBounds(375, 145, 290, 29);
		ButtonAuto.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				automaticallySelected();
				
			}
		});
		contentPane.add(ButtonAuto);
		LabelAuto.setForeground(new Color(47, 79, 79));
		
		LabelAuto.setBounds(375, 185, 300, 16);
		contentPane.add(LabelAuto);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(338, 145, 11, 258);
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
				shared.setExistingPanel(shared.getNPArray(), 1);
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
				shared.getProblem().setNumberOfDays(numberOfDays);
				shared.getProblem().setAlgorithms(SELECTEDAlgorithmsArray);
				
				shared.setReviewProblem();
				shared.setExistingPanel(shared.getNPArray(), 3);
				
				Configuration conf = null;
				switch(shared.getProblem().getType()) {
					case "Double" :
						conf = new ConfigurationDoubleP(shared.getProblem());
						break;
					case "Integer" :
						break;
					case "Binary" :
						break;
					default:
						conf = new Configuration(shared.getProblem());
						System.out.println("GUIAlgorithm:: ProblemType Not found");
						break;
				}
				try {
					conf.Run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(BotaoNext);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(75);
		contentPane.add(progressBar);
		
		JLabel LabelLogo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
		
		
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	public void addMultiAlgorithmsToArray(){
		multiAlgorithmsArray.add("ABYSS"); multiAlgorithmsArray.add("CellDE45");	multiAlgorithmsArray.add("DMOPSO");	multiAlgorithmsArray.add("GDE3");	
		multiAlgorithmsArray.add("GWASFGA");	multiAlgorithmsArray.add("IBEA");	multiAlgorithmsArray.add("MOCell");	multiAlgorithmsArray.add("MOCHC");	
		multiAlgorithmsArray.add("MOEAD");	multiAlgorithmsArray.add("MOMBI");	multiAlgorithmsArray.add("NSGAII");	multiAlgorithmsArray.add("NSGAIII");	
		multiAlgorithmsArray.add("OMOPSO");	multiAlgorithmsArray.add("PAES");	multiAlgorithmsArray.add("PESA2");	multiAlgorithmsArray.add("Random Search");	
		multiAlgorithmsArray.add("RNSGAII");	multiAlgorithmsArray.add("SMPSO");	multiAlgorithmsArray.add("SMSEMOA");	multiAlgorithmsArray.add("SPEA2");	
		multiAlgorithmsArray.add("WASFGA");	
		CBMulti.addItem(new String(""));
		for(int i = 0 ; i<multiAlgorithmsArray.size(); i++){
			CBMulti.addItem(multiAlgorithmsArray.get(i));
		}
	
	}

	public void automaticallySelected(){
		LabelAuto.setForeground(new Color(58,153,58));
		disableManually();
	}
	public void manuallySelected(){
		LabelAuto.setForeground(Color.RED);
		enableManually();
	}
	
	public void disableManually(){
		CBMulti.setEnabled(false);
		ButtonAddMulti.setEnabled(false);
	}
	public void enableManually(){
		CBMulti.setEnabled(true);
		ButtonAddMulti.setEnabled(true);
	}
	
	public boolean checkIfThisStringExists(String s){
		boolean b = false;
		if(TAText.toLowerCase().contains(s.toLowerCase())){
			b = true;
			JOptionPane.showMessageDialog(null, "Algorithm already added.");
		}
		return b;
	}
	
	public ArrayList<String> getAlgorithmsSelected(){
		return SELECTEDAlgorithmsArray;
	}
	
	
}