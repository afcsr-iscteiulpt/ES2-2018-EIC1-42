package graphs;

 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelGraph extends JPanel{

	ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();
	private double max;
	private static final int RADIUS = 10;
	private static final int DIVISIONS = 7;
	
	/**
	 * 
	 * Creates a panel with values represented
	 * 
	 * @param values
	 */
	public PanelGraph(ArrayList<ArrayList<Double>> values) {
		this.values = values;
		calcMaxValue();
		this.setBackground(Color.WHITE);
	}
	
	private void calcMaxValue() {
		for (int i = 0; i < values.size(); i++) {
			for (int j = 0; j < values.get(i).size(); j++) {
				if (max < values.get(i).get(j)) {
					max = values.get(i).get(j);
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics currentGraphics) {
		super.paintComponent(currentGraphics);
		
		Graphics2D mainGraphics = (Graphics2D)currentGraphics;
		
		int yIncrements = Math.max(1, (int)(this.getHeight()/(DIVISIONS)) - 20);
		
		int k = -1;
		for (int i = this.getHeight(), c = 0; i > 0 ; i -= yIncrements, c++) {
			mainGraphics.drawLine(0, i, this.getWidth(), i);
			mainGraphics.drawString(String.format("%.2f", (c)*max/(this.getHeight()/yIncrements)), 0, i);
			k++;
		}
		
		Random r = new Random();
		for (int i = 0; i < values.size(); i++) {
			
			mainGraphics.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			int xDistance = (this.getWidth()/values.get(i).size())/2;
			int lastX = -1, lastY = -1;
			
			for (int j = 0, x = xDistance; j < values.get(i).size(); j++, x +=2*xDistance) {
				int y = (int)((this.getHeight() - (yIncrements*k*(values.get(i).get(j)/max))));
				
				mainGraphics.fillOval(x - RADIUS/2, y- RADIUS/2, RADIUS, RADIUS);
				mainGraphics.drawString(String.format("%.2f", values.get(i).get(j)), x + RADIUS, y);
				if(lastX != -1 || lastY != -1) {
					mainGraphics.drawLine(lastX + RADIUS/2, lastY + RADIUS/2, x, y);					
				}		
				lastX = x - RADIUS/2;
				lastY = y - RADIUS/2;
			}
		}
	}
	
}