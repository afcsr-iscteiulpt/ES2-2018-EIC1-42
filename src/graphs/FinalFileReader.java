package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FinalFileReader {

	ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();

	/**
	 * 
	 * Construtor da classe FinalFileReader
	 * 
	 * @param totalValues
	 */

	public FinalFileReader(ArrayList<ArrayList<Double>> totalValues) {
		this.values = totalValues;
	}

	/**
	 * 
	 * Esta função lê o ficheir File e retira os valores otimizados do mesmo
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */

	public void values(File file) throws FileNotFoundException {
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
