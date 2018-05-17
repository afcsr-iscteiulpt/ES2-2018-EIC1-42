package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import General.SharedClass;
import graphs.Scale;

import java.awt.Color;

public class GUIGraphs extends JFrame {

	private JPanel contentPane;
	private SharedClass shared;
	private Scale PANELGraphs;

	//--
	public GUIGraphs(SharedClass shared) {
		this.shared = shared;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PANELGraphs = new Scale();
		PANELGraphs.setBounds(6, 6, 688, 466);
		PANELGraphs.setBackground(Color.WHITE);
		contentPane.add(PANELGraphs);
	}

	//
	public JPanel getContentPane(){
		return contentPane;
	}
	public JPanel getGraphPanel(){
		System.out.println("teste");
		return PANELGraphs;
	}
	//--


}
