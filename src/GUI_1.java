import java.awt.BorderLayout;
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

public class GUI_1 extends JFrame {

	private JProgressBar progressBar = new JProgressBar();

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
		
		progressBar.setBounds(34, 448, 633, 20);
		contentPane.add(progressBar);
		
		JLabel LabelLogo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT));
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(26, 6, 649, 398);
		contentPane.add(LabelLogo);

		
		JButton BotaoNext = new JButton("▶");
		BotaoNext.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoNext.setBounds(614, 401, 53, 35);
		contentPane.add(BotaoNext);
	}

	public JProgressBar getJProgressBar(){
		return progressBar;
	}
}
