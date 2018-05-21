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

	// --
	public GUIGraphs(SharedClass shared) {
		this.shared = shared;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

	}

	//

	public JPanel getGraphPanel() {
		System.out.println("teste");
		return PANELGraphs;
	}

	public void resolve() {
		File[] files = listThisPlease();
		System.out.println("Files length: " + files.length);
		System.out.println(shared.getAdministrador().getProblemsDir() + "referenceFronts/");

		ArrayList<ArrayList<Double>> totalValues = new ArrayList<ArrayList<Double>>();
		try {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(shared.getProblem().getName() + ".rf")) {
					new FinalFileReader(totalValues).values(files[i]);
				}
			}
			PANELGraphs = new Scale(totalValues);
			PANELGraphs.setBounds(6, 6, 688, 466);
			PANELGraphs.setBackground(Color.WHITE);
			contentPane.add(PANELGraphs);
			contentPane.setVisible(true);
			contentPane.revalidate();
		} catch (Exception e) {
		}
		listThisPlease();
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public File[] listThisPlease() {
		File folder = new File(shared.getAdministrador().getProblemsDir() + "referenceFronts/");
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;

	}

}
