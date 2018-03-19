package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import General.SharedClass;

public class GUIGraphs extends JFrame{

	private Scale scale = new Scale();
	private JPanel panel;
	private SharedClass shared;
	private JPanel PanelGraph = scale.getPanel() ;
//	private Scale scale = new Scale();


	public GUIGraphs(SharedClass shared) {
		this.shared=shared;
		getContentPane().setLayout(null);
	
		PanelGraph.setBounds(6, 6, 438, 199);
		getContentPane().add(PanelGraph);
	
	}
	
	public JPanel getPanel(){
		return PanelGraph;
	}
}
