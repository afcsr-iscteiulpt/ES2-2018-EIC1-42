package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FinalFileReader {

	ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();

	public FinalFileReader(ArrayList<ArrayList<Double>> totalValues) {
		this.values = totalValues;
	}

	public void values(File file) throws FileNotFoundException{
		Scanner s = new Scanner(file);
		while (s.hasNextLine()) {
			ArrayList<Double> graph = new ArrayList<Double>();
			String st = s.nextLine();
			String[] numbers = st.split(" ");
			for (int i = 0; i < numbers.length; i++) {
				graph.add(Double.valueOf(numbers[i]));
			}
			values.add(graph);
		}
		s.close();
	}

}
