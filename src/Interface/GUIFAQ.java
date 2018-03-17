package Interface;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.xml.sax.SAXException;

import General.SharedClass;
import email.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class GUIFAQ extends JFrame {

	private JPanel contentPane;
	private JTextField TFSubject;
	private JTextField TFFrom;
	private JTextArea TAEmailText;

	public GUIFAQ(SharedClass sharedClass) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LabelQuestion1 = new JLabel("WHAT IS THE PURPOSE OF THIS PROGRAM?");
		LabelQuestion1.setForeground(new Color(0, 139, 139));
		LabelQuestion1.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelQuestion1.setBounds(37, 24, 508, 20);
		contentPane.add(LabelQuestion1);

		JLabel LabelAnswer1 = new JLabel("This programs main objective is to optimize a general existing problem.");
		LabelAnswer1.setForeground(new Color(255, 255, 255));
		LabelAnswer1.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		LabelAnswer1.setBounds(37, 37, 620, 20);
		contentPane.add(LabelAnswer1);

		JLabel LabelQuestion2 = new JLabel("HOW TO USE THE PROGRAM?");
		LabelQuestion2.setForeground(new Color(0, 139, 139));
		LabelQuestion2.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelQuestion2.setBounds(37, 68, 508, 20);
		contentPane.add(LabelQuestion2);

		JLabel LabelAnswer2 = new JLabel(
				"<html> 1. If you want to add a new problem, click on 'New Problem' on the initial page and follow step by step. <br/> 2. If you want to check previous solved problems, click on 'Stored Problems'. </html>");
		LabelAnswer2.setForeground(new Color(255, 255, 255));
		LabelAnswer2.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		LabelAnswer2.setBounds(37, 80, 620, 45);
		contentPane.add(LabelAnswer2);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(6, 127, 688, 12);
		contentPane.add(separator);

		JLabel LabelMailAdmin = new JLabel("To:             ood1.0chief@gmail.com");
		LabelMailAdmin.setForeground(new Color(255, 255, 255));
		LabelMailAdmin.setFont(new Font("Avenir Next", Font.BOLD, 13));
		LabelMailAdmin.setBounds(37, 151, 267, 16);
		contentPane.add(LabelMailAdmin);

		TFSubject = new JTextField();
		TFSubject.setForeground(new Color(0, 128, 128));
		TFSubject.setBounds(90, 211, 256, 20);
		contentPane.add(TFSubject);
		TFSubject.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 243, 620, 154);
		contentPane.add(scrollPane);

		TAEmailText = new JTextArea();
		TAEmailText.setForeground(new Color(0, 128, 128));
		scrollPane.setViewportView(TAEmailText);

		JLabel LabelSubject = new JLabel("Subject:");
		LabelSubject.setForeground(new Color(255, 255, 255));
		LabelSubject.setFont(new Font("Avenir Next", Font.BOLD, 13));
		LabelSubject.setBounds(37, 211, 61, 16);
		contentPane.add(LabelSubject);

		JLabel lblFrom = new JLabel("From:");
		lblFrom.setForeground(new Color(255, 255, 255));
		lblFrom.setFont(new Font("Avenir Next", Font.BOLD, 13));
		lblFrom.setBounds(37, 179, 61, 16);
		contentPane.add(lblFrom);

		TFFrom = new JTextField();
		TFFrom.setForeground(new Color(0, 128, 128));
		TFFrom.setColumns(10);
		TFFrom.setBounds(90, 179, 256, 20);
		contentPane.add(TFFrom);

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

		JButton ButtonSend = new JButton("Send");
		ButtonSend.setForeground(new Color(0, 128, 128));
		ButtonSend.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		ButtonSend.setBounds(582, 417, 75, 36);
		ButtonSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendEmail();
			}
		});
		contentPane.add(ButtonSend);

		JLabel LabelLogo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
	}

	public JPanel getContentPane() {
		return contentPane;
	}
	
	public void sendEmail() {
			if(TFFrom.getText().equals("") && TFSubject.getText().equals("") && TAEmailText.getText().equals("")){
				JOptionPane.showMessageDialog(null, "You must fill all the mandatory text fields.");
			} 
			else if(TFFrom.getText().equals("")){
				JOptionPane.showMessageDialog(null, "You must write your email address.");
			}
			else if(TFSubject.getText().equals("")){
				JOptionPane.showMessageDialog(null, "You must write a subject.");
			}
			else if(TAEmailText.getText().equals("")){
				JOptionPane.showMessageDialog(null, "You must write something.");
			}
			else {
				new EmailFAQ(TFFrom.getText(), TFSubject.getText(), TAEmailText.getText());
			}
	}



}
