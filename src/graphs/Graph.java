package graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel{
	
	private ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();
	private String problemTag;
	
	private Color bottomColor;


	/**
	 *Dado um array de arrays em que cada um destes � um resultado da optimiza��o este faz a representa��o do mesmo
	 * 
	 * @param values
	 */
	public Graph(ArrayList<ArrayList<Double>> values) {
		this.values = values;
		construct();
	}
	
	public Graph(ArrayList<ArrayList<Double>> values, String problem) {
		this.values = values;
		this.problemTag = problem;
		construct();
	}

	@SuppressWarnings("unused")
	public void construct() {
		this.setLayout(new BorderLayout());

		JPanel objective = new JPanel();
		objective.setLayout(new BorderLayout());

		JPanel fillW = new JPanel();
		objective.add(fillW, BorderLayout.WEST);
		
		PanelGraph graph = new PanelGraph(values);
		objective.add(graph, BorderLayout.CENTER);
		
		JPanel fillE = new JPanel();
		objective.add(fillE, BorderLayout.EAST);

		JPanel southpanel = new JPanel();
		southpanel.setLayout(new BorderLayout());
		
		JLabel problem = new JLabel("Problem");
		
		if(problem == null)
			problem.setText("Problem");
		else
			problem.setText(problemTag);
		Font font = new Font("Dialog", Font.BOLD, 20);
		problem.setFont(font);
		problem.setHorizontalAlignment(JLabel.CENTER);
		southpanel.add(problem, BorderLayout.CENTER);

		JPanel collumHolder = new JPanel();
		if (bottomColor == null) {
			collumHolder.setBackground(new Color(220,220,220));
		}else {
			collumHolder.setBackground(bottomColor);
		}

		southpanel.add(collumHolder, BorderLayout.NORTH);

		objective.add(southpanel, BorderLayout.SOUTH);
		this.add(objective);
	}
	
}
