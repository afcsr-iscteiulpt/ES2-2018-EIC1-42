import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI_1 {

	private JPanel contentPane;
	private JFrame frame;
	private SharedClass shared;
	//.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI_1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI_1() {
		shared = new SharedClass();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		
		JLabel LabelLogo = new JLabel();
		LabelLogo.setForeground(new Color(0, 204, 204));
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("Geral.png").getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT));
		
		JButton ButtonFAQ = new JButton("FAQ");
		ButtonFAQ.setForeground(new Color(0, 128, 128));
		ButtonFAQ.setFont(new Font("Krungthep", Font.PLAIN, 12));
		ButtonFAQ.setBounds(45, 397, 133, 48);
		contentPane.add(ButtonFAQ);
		
		JButton ButtonStoredProblems = new JButton("Stored Problems");
		ButtonStoredProblems.setForeground(new Color(0, 128, 128));
		ButtonStoredProblems.setFont(new Font("Krungthep", Font.PLAIN, 12));
		ButtonStoredProblems.setBounds(45, 325, 133, 48);
		contentPane.add(ButtonStoredProblems);
		
		JButton ButtonNewProblem = new JButton("New Problem");
		ButtonNewProblem.setForeground(new Color(0, 128, 128));
		ButtonNewProblem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIDescricaoProblema_2 problem = new GUIDescricaoProblema_2(shared);
			}
		});
		ButtonNewProblem.setFont(new Font("Krungthep", Font.PLAIN, 12));
		ButtonNewProblem.setBounds(45, 255, 133, 48);
		contentPane.add(ButtonNewProblem);
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
		
		shared.setPanelDisplayed(contentPane);

	}

	public JFrame getFrame(){
		return frame;
	}

	
}
