import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SharedClass {

	private JFrame frame  = new JFrame("Optimization on Demand 1.0");
	private ArrayList<JPanel> ArrayOfPanels = new ArrayList<JPanel>();
	private ArrayList<JPanel> ArrayNewProblem = new ArrayList<JPanel>();
	private ArrayList<JPanel> ArrayStoredProblems = new ArrayList<JPanel>();
	private ArrayList<JPanel> ArrayFAQ = new ArrayList<JPanel>();


	
	private GUI gui;
	private GUIDescricaoProblema guidescricaoproblema;
	private GUIDefinicaoVariaveis guiDefinicaoVariaveis;
	private GUIFAQ guifaq;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("o");
					new SharedClass();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SharedClass(){
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 700, 500);
		launch();
	}
	
	public void launch(){
		//0
		gui = new GUI(this);
		ArrayOfPanels.add(gui.getContentPane());
		gui.setPosition(0);
		setExistingPanel(ArrayOfPanels,0);

	}
	
	public void setFrame(JFrame frameX){
		this.frame = frameX;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public void setExistingPanel(ArrayList<JPanel> panels, int i){
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panels.get(i));
		frame.revalidate();
		frame.repaint();
	}
	//-------------------NP RELATED---------------------
	public void createNPArray(){
		//0 in ArrayNewProblem
		ArrayNewProblem.clear();
		guidescricaoproblema = new GUIDescricaoProblema(this);
		ArrayNewProblem.add(guidescricaoproblema.getContentPane());
		guidescricaoproblema.setPosition(1);
		//1 in ArrayNewProblem
		guiDefinicaoVariaveis = new GUIDefinicaoVariaveis(this);
		ArrayNewProblem.add(guiDefinicaoVariaveis.getContentPane());
		guiDefinicaoVariaveis.setPosition(2);
	}
	public ArrayList<JPanel> getNPArray(){
		return ArrayNewProblem;
	}

	//-------------------SP RELATED---------------------

	public void createSPArray(){
		//0 in ArraySP
	
	}
	public ArrayList<JPanel> getSPArray(){
		return ArrayStoredProblems;
	}
	//-------------------FAQ RELATED---------------------
	public void createFAQArray(){
		//0 in ArrayFAQ
		ArrayFAQ.clear();
		guifaq = new GUIFAQ(this);
		ArrayFAQ.add(guifaq.getContentPane());
		guifaq.setPosition(3);
	}
	public ArrayList<JPanel> getFAQArray(){
		return ArrayFAQ;
	}

	
	public void setGUI(GUI gui){
		this.gui=gui;
	}
	
	public ArrayList<JPanel> getArrayOfPanels(){
		return ArrayOfPanels;
	}
	
	
	
	
	
	
}
