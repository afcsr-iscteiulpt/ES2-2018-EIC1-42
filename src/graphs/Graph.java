package graphs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Graph extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> values;
	private final int RADIUS = 10;


	/**
	 * values sao os valores para a respectiva variavel.<br>
	 * barra1 e barra2 sao as duas core para alternar na representaçao.<br>
	 * se algum destes valores for dado como null cria o grafico com valores aleatorios e/ou cores default.<br>
	 * 
	 * @param values
	 * @param barra1
	 * @param barra2
	 */
	public Graph(ArrayList<Integer> values) {
		this.setLayout(new GridLayout(1, 0));
		if(values == null) {
			createValues();
		}else {
			this.values = values;
		}
	}

	@Override
	protected void paintComponent(Graphics currentGraphics) {
		super.paintComponent(currentGraphics);

		Graphics2D mainGraphics = (Graphics2D)currentGraphics;

		int xDistance = this.getWidth()/values.size()/2;


		int max = 0;
		for (int i = 0; i < values.size(); i++) {
			if (values.get(max) < values.get(i))
				max = i;
		}
		int yDistance = this.getHeight() / (values.get(max) + 1);

		mainGraphics.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

		int lastX = -1, lastY = -1;
		for (int i = 0; i < values.size(); i++) {

			mainGraphics.fillOval(xDistance , (int)(yDistance * ((values.get(max)-values.get(i)) + 0.5)), RADIUS, RADIUS);
			mainGraphics.drawString(Integer.toString(values.get(i)), xDistance + RADIUS, (int)(yDistance * ((values.get(max)-values.get(i)) + 0.5)) + RADIUS);

			if(lastX != -1 || lastY != -1){
				mainGraphics.drawLine(lastX + RADIUS/2, lastY + RADIUS/2, xDistance + RADIUS/2, (int)(yDistance * ((values.get(max)-values.get(i)) + 0.5)) + RADIUS/2);
			}
			lastX = xDistance;
			lastY = (int)(yDistance * ((values.get(max)-values.get(i)) + 0.5));

			xDistance += this.getWidth()/values.size();
		}
	}

	//TODO So para testes
	private Random r = new Random();
	private void createValues() {
		values = new ArrayList<Integer>(); 
		int max = 30; 
		for (int i = 0; i < max; i++) {
			values.add(r.nextInt(20));
		}
	}

}
