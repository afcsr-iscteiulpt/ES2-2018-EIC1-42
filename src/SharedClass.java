import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SharedClass {

	private JFrame frame;
	private ArrayList<JPanel> ArrayOfPanels = new ArrayList<JPanel>();
	
	
	public SharedClass(){
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 700, 500);
	}
	
	public void setFrame(JFrame frameX){
		this.frame = frameX;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public void setPanelDisplayed(JPanel panel){
		ArrayOfPanels.add(panel);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panel);
		frame.revalidate();
		frame.repaint();	
	}
	
	public void setExistingPanel(int i){
		frame.getContentPane().removeAll();
		frame.getContentPane().add(ArrayOfPanels.get(i));
		frame.revalidate();
		frame.repaint();
	}
	
	
	public ArrayList<JPanel> getArrayOfPanels(){
		return ArrayOfPanels;
	}
	
	
	
	
	
	
}
