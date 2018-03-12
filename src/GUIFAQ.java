import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class GUIFAQ extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFAQ frame = new GUIFAQ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIFAQ() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelQuestion1 = new JLabel("WHAT IS THE PURPOSE OF THIS PROGRAM?");
		LabelQuestion1.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelQuestion1.setBounds(37, 24, 508, 20);
		contentPane.add(LabelQuestion1);
		
		JLabel LabelAnswer1 = new JLabel("This programs main objective is to optimize a general existing problem.");
		LabelAnswer1.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		LabelAnswer1.setBounds(37, 37, 620, 20);
		contentPane.add(LabelAnswer1);
	}
}
