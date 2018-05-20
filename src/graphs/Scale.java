package graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Scale extends JPanel{
	
	ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();
	private ArrayList<Graph> graphs = new ArrayList<Graph>();
	private double max;

	private Color bottomColor;

	/**
	 *Dado um array de arrays em que cada um destes é um resultado da optimização este faz a representação do mesmo
	 * 
	 * @param values
	 */
	public Scale(ArrayList<ArrayList<Double>> values) {
		this.values = values;
		calcMaxValue();
		contruct();
	}

	private void calcMaxValue() {
		for (int i = 0; i < values.size(); i++) {
			for (int j = 0; j < values.get(i).get(j); j++) {
				if (max < values.get(i).get(j)) {
					max = values.get(i).get(j);
				}
			}
		}
	}

	private void contruct() {
		this.setLayout(new BorderLayout());

		JPanel objective = new JPanel();
		objective.setLayout(new BorderLayout());

		JPanel fillW = new JPanel();
		objective.add(fillW, BorderLayout.WEST);

		for (int i = 0; i < values.size(); i++) {
			Graph grafico = new Graph(values.get(i), max);
			grafico.setLayout(new GridLayout(1, 1));
			grafico.setOpaque(false);
			graphs.add(grafico);
			if (i > 0) {
				graphs.get(i - 1).add(grafico);
			}
		}
		
		objective.add(graphs.get(0), BorderLayout.CENTER);

		JPanel fillE = new JPanel();
		objective.add(fillE, BorderLayout.EAST);

		JPanel southpanel = new JPanel();
		southpanel.setLayout(new BorderLayout());

		JLabel problem = new JLabel("var1");
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
