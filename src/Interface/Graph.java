package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Graph extends JPanel {

	private ArrayList<Integer> values;
	private Random r = new Random();

	private Color barra1;
	private Color barra2;

	private double mediator;
	
	/**
	 * values sao os valores para a respectiva variavel.<br>
	 * barra1 e barra2 sao as duas core para alternar na representaçao.<br>
	 * se algum destes valores for dado como null cria o grafico com valores aleatorios e/ou cores default.<br>
	 * 
	 * @param values
	 * @param barra1
	 * @param barra2
	 */
	public Graph(ArrayList<Integer> values, Color barra1, Color barra2) {
		this.barra1 = barra1;
		this.barra2 = barra2;
		if(values == null) {
			graphWithoutValues();
		}else {
			this.values = values;
			createMediator();
		}
	}

	private void createMediator() {
		int j = 0;
		for (int i = 0; i < values.size(); i++) {
			if(values.get(i) > values.get(j))
				j = i;
		}
		mediator = values.get(j) * 2.1;
	}

	@Override
	protected void paintComponent(Graphics currentGraphics) {
		super.paintComponent(currentGraphics);

		Graphics2D mainGraphics = (Graphics2D)currentGraphics;

		int j = 0;
		for (int i = 0; i < values.size(); i++) {
			int heigth = (int) (this.getHeight() - (values.get(i)/mediator * this.getHeight()));
			int yPosition = heigth;
			int width = this.getWidth()/values.size();
			int xPosition = width * i;
			if(j == 1) {
				if (barra1 == null)
					mainGraphics.setColor(new Color(135, 206, 235));
				else
					mainGraphics.setColor(barra1);
				j = 0;
			}else {
				if (barra2 == null)
					mainGraphics.setColor(new Color(250, 128, 114));
				else
					mainGraphics.setColor(barra2);
				j = 1;
			}
			mainGraphics.fill(new Rectangle(xPosition, yPosition, width, heigth));
		}
	}
	
	//TODO so para testes nao utilizar
		/**
		 * so para testes
		 */
		private void createValues() {
			values = new ArrayList<Integer>(); 
			int max = 2; 
			for (int i = 0; i < max; i++) {
				values.add(r.nextInt(12));//
			}
		}
		
	//TODO so para testes
		/**
		 * so para testes
		 */
		private void graphWithoutValues() {
			createValues();
			createMediator();
		}

}
