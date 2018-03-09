import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GUI_1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_1 frame = new GUI_1();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
			}
		});
		ButtonNewProblem.setFont(new Font("Krungthep", Font.PLAIN, 12));
		ButtonNewProblem.setBounds(45, 255, 133, 48);
		contentPane.add(ButtonNewProblem);
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
	
		
	}
}
