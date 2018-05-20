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
	private ArrayList<Double> values;
	private static final int RADIUS = 10;
	private static final double CONSTANT = 0.5;

	private double max;


	/**
	 * values sao os valores para a respectiva variavel.<br>
	 * barra1 e barra2 sao as duas core para alternar na representaçao.<br>
	 * se algum destes valores for dado como null cria o grafico com valores aleatorios e/ou cores default.<br>
	 * 
	 * @param values
	 * @param barra1
	 * @param barra2
	 */
	public Graph(ArrayList<Double> values, Double max) {
		this.setLayout(new GridLayout(1, 0));
		this.max = max;
		System.out.println(max);
		this.values = values;
	}

	@Override
	protected void paintComponent(Graphics currentGraphics) {
		super.paintComponent(currentGraphics);

		Graphics2D mainGraphics = (Graphics2D)currentGraphics;
		int xDistance = this.getWidth()/values.size()/2;
		
		int yDistance = (int) (this.getHeight() / (max + 1));
		
		Random r = new Random();
		mainGraphics.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

		int lastX = -1, lastY = -1;
		for (int i = 0; i < values.size(); i++) {

			mainGraphics.fillOval(xDistance , (int)(yDistance *((max-values.get(i)) + CONSTANT)), RADIUS, RADIUS);
			mainGraphics.drawString(Double.toString(values.get(i)), xDistance + RADIUS, (int)(yDistance * ((max-values.get(i)) + CONSTANT)) + RADIUS);

			if(lastX != -1 || lastY != -1){
				mainGraphics.drawLine(lastX + RADIUS/2, lastY + RADIUS/2, xDistance + RADIUS/2, (int)(yDistance * ((max-values.get(i)) + CONSTANT)) + RADIUS/2);
			}
			lastX = xDistance;
			lastY = (int)(yDistance * ((max-values.get(i)) + CONSTANT));

			xDistance += this.getWidth()/values.size();
		}
	}

}
