package Interface;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import General.SharedClass;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Color;

public class GUIFinal extends JFrame{
	
	private SharedClass shared;
	private JPanel contentPane;
	private JTextField TFName;
	private JTextField TFDays;
	private JTextArea TADescription;
	private JTextArea TAVariables;
	private JTextArea TAAlgorit;
	
	public GUIFinal(SharedClass shared) {
		this.shared=shared;
		//teste

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(100);
		contentPane.add(progressBar);
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
		
		JLabel LabelProblemReview = new JLabel("Problem Review");
		LabelProblemReview.setForeground(new Color(255, 255, 255));
		LabelProblemReview.setBounds(32, 0, 265, 28);
		LabelProblemReview.setFont(new Font("Avenir Next", Font.BOLD, 21));
		contentPane.add(LabelProblemReview);
		
		JLabel LabelName = new JLabel("Name:");
		LabelName.setForeground(new Color(255, 255, 255));
		LabelName.setFont(new Font("Avenir Next", Font.BOLD, 17));
		LabelName.setBounds(34, 46, 58, 20);
		contentPane.add(LabelName);
		
		TFName = new JTextField();
		TFName.setBounds(34, 66, 183, 28);
		contentPane.add(TFName);
		TFName.setColumns(10);
		TFName.setEnabled(false);
		TFName.setText(shared.getProblem().getName());
		
		JLabel LabelDescription = new JLabel("Description:");
		LabelDescription.setForeground(new Color(255, 255, 255));
		LabelDescription.setFont(new Font("Avenir Next", Font.BOLD, 17));
		LabelDescription.setBounds(34, 106, 116, 20);
		contentPane.add(LabelDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 127, 633, 69);
		contentPane.add(scrollPane);
		
		TADescription = new JTextArea();
		TADescription.setEnabled(false);
		scrollPane.setViewportView(TADescription);
		TADescription.setText(shared.getProblem().getDescription());
		
		JLabel LabelVariables = new JLabel("Variables:");
		LabelVariables.setForeground(new Color(255, 255, 255));
		LabelVariables.setFont(new Font("Avenir Next", Font.BOLD, 17));
		LabelVariables.setBounds(34, 209, 90, 20);
		contentPane.add(LabelVariables);
		
		JLabel LabelAlgori = new JLabel("Algorithms chosen:");
		LabelAlgori.setForeground(new Color(255, 255, 255));
		LabelAlgori.setFont(new Font("Avenir Next", Font.BOLD, 17));
		LabelAlgori.setBounds(380, 209, 212, 20);
		contentPane.add(LabelAlgori);
		
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(47, 79, 79));
		separator.setBounds(34, 28, 633, 12);
		contentPane.add(separator);
		
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
		
		
		TFDays = new JTextField();
		TFDays.setColumns(10);
		TFDays.setBounds(380, 67, 58, 28);
		TFDays.setEnabled(false);
		contentPane.add(TFDays);
		
		JLabel LabelNeedsToBeSolved = new JLabel("Needs to be solved in:");
		LabelNeedsToBeSolved.setForeground(new Color(255, 255, 255));
		LabelNeedsToBeSolved.setFont(new Font("Avenir Next", Font.BOLD, 17));
		LabelNeedsToBeSolved.setBounds(380, 48, 212, 20);
		contentPane.add(LabelNeedsToBeSolved);
				
		JLabel LabelDays = new JLabel("days");
		LabelDays.setForeground(new Color(255, 255, 255));
		LabelDays.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelDays.setBounds(441, 72, 58, 20);
		contentPane.add(LabelDays);
				
		JButton ButtonSave = new JButton("Save & Send");
		ButtonSave.setForeground(new Color(0, 128, 128));
		ButtonSave.setFont(new Font("Avenir Next", Font.BOLD, 20));
		ButtonSave.setBounds(501, 384, 166, 52);
		contentPane.add(ButtonSave);
		ButtonSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.writeXmlFile(shared.getProblem());
				
			}
		});
		
		JScrollPane SPVariables = new JScrollPane();
		SPVariables.setBounds(34, 227, 263, 111);
		contentPane.add(SPVariables);
		
		TAVariables = new JTextArea();
		TAVariables.setEnabled(false);
		SPVariables.setViewportView(TAVariables);
			
			JScrollPane SPAlgori = new JScrollPane();
			SPAlgori.setBounds(380, 227, 263, 111);
			contentPane.add(SPAlgori);
			
			TAAlgorit = new JTextArea();
			TAAlgorit.setEnabled(false);
			SPAlgori.setViewportView(TAAlgorit);
			
				JLabel LabelLogo = new JLabel();
				LabelLogo.setBounds(0, 0, 700, 478);
				LabelLogo.setIcon(imageIcon);
				contentPane.add(LabelLogo);
	}
	
	public JTextField getTFName() {
		return TFName;
	}

	public void setTFName(JTextField tFName) {
		TFName = tFName;
	}

	public JTextField getTFDays() {
		return TFDays;
	}

	public void setTFDays(JTextField tFDays) {
		TFDays = tFDays;
	}

	public JTextArea getTADescription() {
		return TADescription;
	}

	public void setTADescription(JTextArea tADescription) {
		TADescription = tADescription;
	}

	public JTextArea getTAVariables() {
		return TAVariables;
	}

	public void setTAVariables(JTextArea tAVariables) {
		TAVariables = tAVariables;
	}

	public JTextArea getTAAlgorit() {
		return TAAlgorit;
	}

	public void setTAAlgorit(JTextArea tAAlgorit) {
		TAAlgorit = tAAlgorit;
	}

	public JPanel getContentPane(){
		return contentPane;
	}
}
