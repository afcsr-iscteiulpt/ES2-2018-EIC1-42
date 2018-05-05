package Interface;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import General.SharedClass;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Color;

public class GUIProblem {

	private JTextField TFNomeProblema;
	private JLabel LabelDescricaoProblema;
	private JLabel LabelEmail;
	private JTextField TFEmail;
	private JLabel LabelNaoObrigatorio;
	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JButton ButtonLoad;
	private JTextArea TADescricaoProblema;
	private SharedClass shared;

	public GUIProblem(SharedClass shared) {
		this.shared=shared;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		TFNomeProblema = new JTextField();
		TFNomeProblema.setForeground(new Color(0, 128, 128));
		TFNomeProblema.setBounds(34, 32, 334, 35);
		contentPane.add(TFNomeProblema);
		TFNomeProblema.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 96, 633, 173);
		contentPane.add(scrollPane);

		TADescricaoProblema = new JTextArea();
		TADescricaoProblema.setForeground(new Color(0, 128, 128));
		scrollPane.setViewportView(TADescricaoProblema);
		TADescricaoProblema.setLineWrap(true);

		LabelDescricaoProblema = new JLabel("Problem Description:");
		LabelDescricaoProblema.setForeground(Color.WHITE);
		LabelDescricaoProblema.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelDescricaoProblema.setBounds(34, 79, 167, 16);
		contentPane.add(LabelDescricaoProblema);

		JLabel LabelTituloProblema = new JLabel("Name the problem:");
		LabelTituloProblema.setForeground(Color.WHITE);
		LabelTituloProblema.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelTituloProblema.setBounds(38, 16, 245, 16);
		contentPane.add(LabelTituloProblema);

		LabelEmail = new JLabel("Email Adress (*):");
		LabelEmail.setForeground(Color.WHITE);
		LabelEmail.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelEmail.setBounds(34, 295, 267, 16);
		contentPane.add(LabelEmail);

		TFEmail = new JTextField();
		TFEmail.setForeground(new Color(0, 128, 128));
		TFEmail.setBounds(34, 317, 334, 35);
		contentPane.add(TFEmail);
		TFEmail.setColumns(10);

		LabelNaoObrigatorio = new JLabel("(*) Not mandatory, only if you want to receive informations");
		LabelNaoObrigatorio.setForeground(Color.WHITE);
		LabelNaoObrigatorio.setFont(new Font("Avenir Next", Font.BOLD, 10));
		LabelNaoObrigatorio.setBounds(34, 351, 334, 16);
		contentPane.add(LabelNaoObrigatorio);

		JButton BotaoBack = new JButton("◀");
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.setBounds(34, 401, 53, 35);
		BotaoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setExistingPanel(shared.getArrayOfPanels(), 0);
			}
		});
		contentPane.add(BotaoBack);

		JButton BotaoNext = new JButton("▶");
		BotaoNext.setForeground(new Color(0, 128, 128));
		BotaoNext.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoNext.setBounds(614, 401, 53, 35);
		BotaoNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validate(BotaoNext);
			}
		});
		contentPane.add(BotaoNext);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(25);
		contentPane.add(progressBar);

		JLabel LabelLogo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));

		ButtonLoad = new JButton("Load Problem");
		ButtonLoad.setForeground(new Color(0, 128, 128));
		ButtonLoad.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		ButtonLoad.setBounds(412, 32, 117, 33);
		ButtonLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.readXMLFile();
			}
		});
		contentPane.add(ButtonLoad);
		
		LabelLogo.setIcon(imageIcon);
		LabelLogo.setBounds(0, 0, 700, 478);
		contentPane.add(LabelLogo);
	}

	public void validate(JButton button){
		if(TFNomeProblema.getText().equals("") && TADescricaoProblema.getText().equals("")){
			JOptionPane.showMessageDialog(null, "You must fill the Problems Name and Description.");
		}
		else if(TFNomeProblema.getText().equals("") && !TADescricaoProblema.getText().equals("")){
			JOptionPane.showMessageDialog(null, "You must fill the Problems Name.");
		}
		else if(!TFNomeProblema.getText().equals("") && TADescricaoProblema.getText().equals("")){
			JOptionPane.showMessageDialog(null, "You must fill the Problems Description.");
		}
		else if(!TFEmail.getText().equals("") && !isValidEmailAddress(TFEmail.getText())){
			JOptionPane.showMessageDialog(null, "You must type a valid email.");
		}
		else{			
			shared.getProblem().setName(TFNomeProblema.getText());
			shared.getProblem().setDescription(TADescricaoProblema.getText());
			shared.getProblem().setEmail(TFEmail.getText());
			shared.setExistingPanel(shared.getNPArray(), 1);
		}
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	
	public String getName(){
		String s = TFNomeProblema.getText();
		System.out.println(s);
		return s;
	}
	public void setName(String s){
		TFNomeProblema.setText(s);
	}
	
	public String getDescription(){
		String s = TADescricaoProblema.getText();
		return s;
	}
	public void setDescription(String s){
		TADescricaoProblema.setText(s);
	}
	
	public String getEmail(){
		String s = TFEmail.getText();
		return s;
	}
	public void setEmail(String s){
		TFEmail.setText(s);
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	

}
