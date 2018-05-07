package graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Scale extends JPanel{

	private ArrayList<String> objectivos;
	private ArrayList<Integer> values;
	private ArrayList<Graph> graphs = new ArrayList<Graph>();

	private Color bottomColor;

	/**
	 * so para testes
	 */
	public Scale() {
		objectivos = new ArrayList<String>();
		objectivos.add("Var1");
		objectivos.add("Var1");
		objectivos.add("Var1");
		objectivos.add("Var1");
		contruct();
	}

	/**
	 * Objectivos e ArrayList<String> com o nome de cada objectivo.<br>
	 * barra1 e barra2 sao as duas core para alternar na representaçao.<br>
	 * valores para objectivos ArrayList<Integer> values.<br>
	 * bottom e a cor da base dos graficos.<p>
	 * 
	 * values sao os valores para a respectiva variavel.<br>
	 * se algum destes valores for dado como null cria o grafico com valores aleatorios e/ou cores default.<br>
	 * 
	 * @param objectivos
	 * @param values
	 * @param barra1
	 * @param barra2
	 * @param bottomColor
	 */
	public Scale(ArrayList<String> objectivos, ArrayList<Integer> values) {
		// e possivel ser nessesario uma calsse variavel para inserir para os graficos ou os values vao ser os mesmos para toods os values
		this.objectivos = objectivos;
		this.values = values;
		contruct();
	}

	private void contruct() {
		this.setLayout(new BorderLayout());

		JPanel objective = new JPanel();
		objective.setLayout(new BorderLayout());

		JPanel fillW = new JPanel();
		objective.add(fillW, BorderLayout.WEST);

		for (int i = 0; i < objectivos.size(); i++) {
			Graph grafico = new Graph(values);
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
