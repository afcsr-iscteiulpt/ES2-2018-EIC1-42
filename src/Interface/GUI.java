package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import General.SharedClass;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {
	private JPanel contentPane;
	private JFrame frame;

	public GUI(SharedClass shared) {

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JLabel LabelLogo = new JLabel();
		LabelLogo.setForeground(new Color(0, 204, 204));
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("Geral.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));

		JButton ButtonFAQ = new JButton("FAQ");
		ButtonFAQ.setForeground(new Color(0, 128, 128));
		ButtonFAQ.setFont(new Font("Krungthep", Font.PLAIN, 12));
		ButtonFAQ.setBounds(45, 397, 133, 48);
		ButtonFAQ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shared.createFAQArray();
				shared.setExistingPanel(shared.getFAQArray(), 0);
			}
		});
		contentPane.add(ButtonFAQ);

		JButton ButtonStoredProblems = new JButton("Stored Problems");
		ButtonStoredProblems.setForeground(new Color(0, 128, 128));
		ButtonStoredProblems.setFont(new Font("Krungthep", Font.PLAIN, 12));
		ButtonStoredProblems.setBounds(45, 325, 133, 48);
		ButtonStoredProblems.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.acessStoredProblems();
			}
		});
		contentPane.add(ButtonStoredProblems);

		JButton ButtonNewProblem = new JButton("New Problem");
		ButtonNewProblem.setForeground(new Color(0, 128, 128));
		ButtonNewProblem.setFont(new Font("Krungthep", Font.PLAIN, 12));
		ButtonNewProblem.setBounds(45, 255, 133, 48);
		ButtonNewProblem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shared.createNPArray();
				shared.setExistingPanel(shared.getNPArray(), 0);
			}
		});
		contentPane.add(ButtonNewProblem);
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getContentPane() {
		return contentPane;
	}


}
