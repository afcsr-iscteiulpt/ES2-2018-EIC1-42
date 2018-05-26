package Interface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import General.SharedClass;
import graphs.FinalFileReader;
import graphs.Graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class GUIGraphs extends JFrame {

	private JPanel contentPane;
	private SharedClass shared;
	private Graph PANELGraphs;

	// --
	public GUIGraphs(SharedClass shared) {
		this.shared = shared;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		

		JButton BotaoBack = new JButton("◀");
		BotaoBack.setBounds(37, 419, 53, 35);
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				contentPane.removeAll();
				shared.setExistingPanel(shared.getNPArray(), 4);
			}
		});
		contentPane.add(BotaoBack);
		
		JButton buttonFrontPage = new JButton("Main Menu");
		buttonFrontPage.setForeground(new Color(0, 128, 128));
		buttonFrontPage.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		buttonFrontPage.setBounds(391, 419, 104, 35);
		buttonFrontPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				shared.setExistingPanel(shared.getArrayOfPanels(), 0);

			}
		});
		contentPane.add(buttonFrontPage);
		
		JButton buttonSP = new JButton("Go to Stored Problems");
		buttonSP.setForeground(new Color(0, 128, 128));
		buttonSP.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		buttonSP.setBounds(507, 419, 167, 35);
		buttonSP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.acessStoredProblems();
			}
		});
		contentPane.add(buttonSP);

	}

	//

	public JPanel getGraphPanel() {
		return PANELGraphs;
	}

	public void resolve() {
		File[] files = listThisPlease();

		ArrayList<ArrayList<Double>> totalValues = new ArrayList<ArrayList<Double>>();
		try {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(shared.getProblem().getName() + ".rf")) {
					new FinalFileReader(totalValues).values(files[i]);
				}
			}
			PANELGraphs = new Graph(totalValues);
			PANELGraphs.setBounds(6, 6, 688, 400);
			PANELGraphs.setBackground(Color.WHITE);
			contentPane.add(PANELGraphs);
			contentPane.setVisible(true);
			contentPane.revalidate();
		} catch (Exception e) {
		}
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
