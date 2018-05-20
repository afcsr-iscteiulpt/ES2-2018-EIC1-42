package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import General.SharedClass;
import graphs.FinalFileReader;
import graphs.Scale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

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

		File[] files = new File(shared.getAdministrador().getProblemsDir()).listFiles((dir, name) -> {//path para onde estão os ficheiros
			return name.toLowerCase().startsWith(shared.getProblem().getName());//nome no problema
		});
		try {
			ArrayList<ArrayList<Double>> totalValues = new ArrayList<ArrayList<Double>>();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().endsWith(".rf")) {
					new FinalFileReader(totalValues).values(files[i]);
				}
			}
			Scale PANELGraphs = new Scale(totalValues);
		} catch (Exception e) {
		}
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
