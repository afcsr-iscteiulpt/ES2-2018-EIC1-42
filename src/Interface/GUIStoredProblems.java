package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import General.*;

public class GUIStoredProblems {

	private JPanel contentPane;
	private SharedClass sharedClass;
	private JTextArea textArea;
	private String textAreaString = "Name: 			Description:";


	public GUIStoredProblems(SharedClass sharedClass) {
		this.sharedClass=sharedClass;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel LabelStoredProblems = new JLabel("Stored Problems");
		LabelStoredProblems.setForeground(new Color(0, 128, 128));
		LabelStoredProblems.setFont(new Font("Avenir Next", Font.BOLD, 18));
		LabelStoredProblems.setBounds(27, 6, 194, 22);
		contentPane.add(LabelStoredProblems);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 58, 651, 331);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Avenir Next", Font.BOLD, 14));
		textArea.setForeground(new Color(47, 79, 79));
		textArea.setText(textAreaString);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton BotaoBack = new JButton("◀");
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.setBounds(37, 419, 53, 35);
		BotaoBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sharedClass.setExistingPanel(sharedClass.getArrayOfPanels(), 0);
			}
		});
		contentPane.add(BotaoBack);
		
		JLabel LabelInfo = new JLabel("You can load any of this on the \"New Problem\" section.");
		LabelInfo.setForeground(new Color(47, 79, 79));
		LabelInfo.setBounds(27, 30, 481, 16);
		contentPane.add(LabelInfo);
		
		JLabel LabelLogo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
	}
	
	public void addProblem(Problem p){
		String add = p.stringConvert();
		textAreaString += "\n" +add;
		textArea.setText(textAreaString);
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	
}
