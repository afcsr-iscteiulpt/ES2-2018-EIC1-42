package General;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;

public class IntegerProblem extends AbstractIntegerProblem {
	private Problem problema;

	/**
	 * 
	 * IntegerProblem constructor
	 *
	 * @param problema
	 */

	public IntegerProblem(Problem problema) {
		this(problema.getVariablesArray().size());
		createIntegerProblem(problema);
	}

	/**
	 * 
	 * Construtor da classe IntegerProblem
	 * 
	 * @param num_Var
	 */

	public IntegerProblem(int num_Var) {
		setNumberOfVariables(num_Var);
	}

	/**
	 * 
	 * Cria��o do problema Integer
	 * 
	 * @param problema
	 */

	public void createIntegerProblem(Problem problema) {
		this.problema = problema;
		setNumberOfObjectives(problema.getObjNumber());
		setName(problema.getName());

		List<Integer> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Integer> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(problema.getVariablesArray().get(i).getMinI());
			upperLimit.add(problema.getVariablesArray().get(i).getMaxI());
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	@Override
	public void evaluate(IntegerSolution solution) {
		String solutionString = "";
		String evaluationResultString = "";
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			solutionString = solutionString + " " + solution.getVariableValue(i);
		}
		try {
			String line;

			Process p = Runtime.getRuntime().exec("java -jar " + problema.getPath() + " "  + solutionString);
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
		// It is assumed that all evaluated criteria are returned in the same result
		// string
		for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
			solution.setObjective(i, Integer.parseInt(individualEvaluationCriteria[i]));
		}
	}
}
