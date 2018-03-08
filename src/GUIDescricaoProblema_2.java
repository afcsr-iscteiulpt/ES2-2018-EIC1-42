import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Color;


public class GUIDescricaoProblema_2 extends JFrame {

	private GUI_1 guiGeral = new GUI_1();
	
	private JPanel contentPane;
	private JTextField TFNomeProblema;
	private JLabel LabelDescricaoProblema;
	private JLabel LabelEmail;
	private JTextField TFEmail;
	private JLabel LabelNaoObrigatorio;
	private JScrollPane scrollPane;

	public GUIDescricaoProblema_2(GUI_1 guiGeral){
		this.guiGeral=guiGeral;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDescricaoProblema_2 frame = new GUIDescricaoProblema_2();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUIDescricaoProblema_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TFNomeProblema = new JTextField();
		TFNomeProblema.setBounds(34, 32, 334, 35);
		contentPane.add(TFNomeProblema);
		TFNomeProblema.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 96, 633, 173);
		contentPane.add(scrollPane);
		
		JTextArea TADescricaoProblema = new JTextArea();
		scrollPane.setViewportView(TADescricaoProblema);
		TADescricaoProblema.setLineWrap(true);
		
		LabelDescricaoProblema = new JLabel("Descrição do problema:");
		LabelDescricaoProblema.setForeground(Color.WHITE);
		LabelDescricaoProblema.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelDescricaoProblema.setBounds(34, 79, 167, 16);
		contentPane.add(LabelDescricaoProblema);
		
		JLabel LabelTituloProblema = new JLabel("Nome do problema a resolver:");
		LabelTituloProblema.setForeground(Color.WHITE);
		LabelTituloProblema.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelTituloProblema.setBounds(38, 16, 245, 16);
		contentPane.add(LabelTituloProblema);
		
		LabelEmail = new JLabel("Endereço de Email (*):");
		LabelEmail.setForeground(Color.WHITE);
		LabelEmail.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelEmail.setBounds(34, 295, 165, 16);
		contentPane.add(LabelEmail);
		
		TFEmail = new JTextField();
		TFEmail.setBounds(34, 317, 334, 35);
		contentPane.add(TFEmail);
		TFEmail.setColumns(10);
		
		LabelNaoObrigatorio = new JLabel("(*) Campo não obrigatório");
		LabelNaoObrigatorio.setForeground(Color.WHITE);
		LabelNaoObrigatorio.setFont(new Font("Avenir Next", Font.BOLD, 10));
		LabelNaoObrigatorio.setBounds(34, 351, 184, 16);
		contentPane.add(LabelNaoObrigatorio);
		 
		JButton BotaoBack = new JButton("◀");
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.setBounds(34, 401, 53, 35);
		contentPane.add(BotaoBack);
		
		JButton BotaoNext = new JButton("▶");
		BotaoNext.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoNext.setBounds(614, 401, 53, 35);
		contentPane.add(BotaoNext);
		
//		JProgressBar progressBar = guiGeral.getJProgressBar();
//		progressBar.setBounds(34, 448, 633, 20);
//		progressBar.setValue(5); 
//		contentPane.add(progressBar);

		
		JLabel LabelLogo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("ESNormalPage.png").getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT));
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
		
		
		
	}
}
