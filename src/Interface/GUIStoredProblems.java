package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import General.*;
import graphs.FinalFileReader;
import graphs.Scale;

public class GUIStoredProblems {

	private JPanel contentPane;
	private SharedClass shared;
	private DefaultListModel<String> model;
	private JList<String> list;
	JPanel PanelGraph;

	public GUIStoredProblems(SharedClass shared) {
		this.shared=shared;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel LabelStoredProblems = new JLabel("Stored Problems");
		LabelStoredProblems.setBounds(27, 6, 194, 22);
		LabelStoredProblems.setForeground(new Color(0, 128, 128));
		LabelStoredProblems.setFont(new Font("Avenir Next", Font.BOLD, 18));
		contentPane.add(LabelStoredProblems);
		
		JButton BotaoBack = new JButton("◀");
		BotaoBack.setBounds(37, 419, 53, 35);
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.clear();
				contentPane.remove(PanelGraph);
				shared.setExistingPanel(shared.getArrayOfPanels(), 0);
				
			}
		});
		contentPane.add(BotaoBack);
		
		JLabel LabelInfo = new JLabel("You can load any of this on the \"New Problem\" section.");
		LabelInfo.setBounds(27, 30, 481, 16);
		LabelInfo.setForeground(new Color(47, 79, 79));
		contentPane.add(LabelInfo);
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
		
		
				
		
		
		Border border = BorderFactory.createLineBorder(new Color(0, 128, 128));
		model = new DefaultListModel<>();
		list = new JList<>(model);
		list.setBounds(27, 55, 116, 333);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File[] files = listThisPlease();
				System.out.println("Files length: " + files.length);
				System.out.println(shared.getAdministrador().getProblemsDir() + "referenceFronts/");

				ArrayList<ArrayList<Double>> totalValues = new ArrayList<ArrayList<Double>>();
				for (int i = 0; i < files.length; i++) {
					if (files[i].getName().equals(list.getSelectedValue())) {
						try {
							new FinalFileReader(totalValues).values(files[i]);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				PanelGraph = new Scale(totalValues);
				PanelGraph.setBackground(Color.WHITE);
				PanelGraph.setBounds(176, 58, 367, 242);
				contentPane.add(PanelGraph);
				contentPane.setVisible(true);
				contentPane.revalidate();
			}
		});
		list.setBorder(border);
		contentPane.add(list);
		
		JLabel LabelLogo = new JLabel();
		LabelLogo.setBounds(0, 0, 700, 478);
		
		LabelLogo.setIcon(imageIcon);
		contentPane.add(LabelLogo);
		
	}
	
	public File[] listThisPlease() {
		File folder = new File(shared.getAdministrador().getProblemsDir()+"/referenceFronts/");
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}
	
	public void resolve() {
		File[] files = listThisPlease();
		System.out.println("Files length: " + files.length);
		System.out.println("/Users/albertoramos/Desktop/referenceFronts/");
		ArrayList<ArrayList<Double>> totalValues = new ArrayList<ArrayList<Double>>();
		try {
			for (int i = 0; i < files.length; i++) {
				if (countOccurencies(files[i].getName(), ".") == 1 && files[i].getName().endsWith(".rf")) {
					String[] s = files[i].getName().split(".",1);
					model.addElement(s[0]);
				
				}
			}
			PanelGraph = new Scale(totalValues);
			PanelGraph.setBounds(6, 6, 355, 238);
			PanelGraph.setBackground(Color.WHITE);
			contentPane.add(PanelGraph);
			
				
			contentPane.setVisible(true);
			contentPane.revalidate();
		} catch (Exception e) {
		}
		listThisPlease();
	}
	
	
	public int countOccurencies(String s, String find){
		int lastIndex = 0;
		int count = 0;
		while(lastIndex != -1){
		    lastIndex = s.indexOf(find,lastIndex);
		    if(lastIndex != -1){
		        count ++;
		        lastIndex += find.length();
		    }
		}
		System.out.println("COUNT: "+count);
		return count;
		}
	
	public JPanel getContentPane(){
		return contentPane;
	}
}
