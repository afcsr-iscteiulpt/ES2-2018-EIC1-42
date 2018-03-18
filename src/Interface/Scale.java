package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scale extends JPanel{
	
	private ArrayList<String> objectivos;
	private ArrayList<Integer> values;
	
	private Color barra1;
	private Color barra2;
	private Color bottom;

	/**
	 * so para testes
	 */
	public Scale() {
		objectivos = new ArrayList<String>();
		objectivos.add("Var1");
		objectivos.add("Var2");
		objectivos.add("Var3");
		objectivos.add("Var4");
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
	 * @param bottom
	 */
	public Scale(ArrayList<String> objectivos, ArrayList<Integer> values, Color barra1, Color barra2, Color bottom) {
		// e possivel ser nessesario uma calsse variavel para inserir para os graficos ou os values vao ser os mesmos para toods os values
		this.objectivos = objectivos;
		this.values = values;
		this.barra1 = barra1;
		this.barra2 = barra2;
		this.bottom = bottom;
		contruct();
	}

	private void contruct() {
		this.setLayout(new GridLayout(1, 0));
		for (int i = 0; i < objectivos.size(); i++) {
			JPanel X = new JPanel();
			X.setLayout(new BorderLayout());
			this.add(X);
			
			JPanel fillW = new JPanel();
			X.add(fillW, BorderLayout.WEST);
			
			Graph grafico = new Graph(values, barra1, barra2);
			X.add(grafico, BorderLayout.CENTER);
			
			JPanel fillE = new JPanel();
			X.add(fillE, BorderLayout.EAST);
			
			JPanel southpanel = new JPanel();
			southpanel.setLayout(new BorderLayout());
			
			JLabel problem = new JLabel(objectivos.get(i));
			problem.setHorizontalAlignment(JLabel.CENTER);
			southpanel.add(problem, BorderLayout.CENTER);
			
			JPanel collumHolder = new JPanel();
			if (bottom == null) {
				collumHolder.setBackground(new Color(220,220,220));
			}else {
				collumHolder.setBackground(bottom);
			}
			southpanel.add(collumHolder, BorderLayout.NORTH);
			
			X.add(southpanel, BorderLayout.SOUTH);
		}
	}

}
