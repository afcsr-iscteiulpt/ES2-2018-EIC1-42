package General;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class DoubleProblem extends AbstractDoubleProblem {
	private Problem problema;

	/**
	 * 
	 * DoubleProblem constructor
	 * 
	 * @param problema
	 */

	public DoubleProblem(Problem problema) {
		this(problema.getVariablesArray().size());
		createDoubleProblem(problema);
	}

	/**
	 * 
	 * DoubleProblem constructor
	 * 
	 * @param num_Var
	 */

	public DoubleProblem(int num_Var) {
		this.setNumberOfVariables(num_Var);
	}

	/**
	 * 
	 * DoubleProblem constructor
	 * 
	 * @param problema
	 */

	public void createDoubleProblem(Problem problema) {
		this.problema = problema;
		this.setNumberOfObjectives(problema.getObjNumber());

		this.setName(problema.getName());

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(problema.getVariablesArray().get(i).getMinD());
			upperLimit.add(problema.getVariablesArray().get(i).getMaxD());
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	@Override
	public void evaluate(DoubleSolution solution) {
		String solutionString = "";
		String evaluationResultString = "";
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			solutionString = solutionString + " " + solution.getVariableValue(i);
		}
		try {
			String line;

			Process p = Runtime.getRuntime().exec("java -jar " + problema.getPath() + " " + solutionString);
			BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = brinput.readLine()) != null) {
				evaluationResultString += line;
			}
			brinput.close();
			p.waitFor();
		} catch (Exception err) {
			err.printStackTrace();
		}
		String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
		for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
			solution.setObjective(i, Double.parseDouble(individualEvaluationCriteria[i]));
		}
	}
}
