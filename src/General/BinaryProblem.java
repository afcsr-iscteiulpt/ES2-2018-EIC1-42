package General;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

public class BinaryProblem extends AbstractBinaryProblem {

	private Problem problema;
	private int bits;

	private int upper;
	private int lower;

	/**
	 * 
	 * BinaryProblem Constructor
	 * 
	 * @param problema
	 * @param num_var
	 */

	public BinaryProblem(Problem problema, int num_var) {
		this(problema.getVariablesArray().size());
		this.bits = problema.getVariablesArray().get(0).getValue().length();
		createBinaryProblem(problema);
	}

	/**
	 * 
	 * BinaryProblem Constructor
	 * 
	 * @param num_Var
	 */

	public BinaryProblem(int num_Var) {
		setNumberOfVariables(num_Var);

	}

	/**
	 * 
	 * BinaryProblem Constructor
	 * 
	 * @param problema
	 */

	public void createBinaryProblem(Problem problema) {
		this.problema = problema;

		setNumberOfObjectives(problema.getObjNumber());

		setName(problema.getName());
	}

	private boolean withinBounds(String value) {
		int testValue = binaryToInt(value);
		if (testValue < upper && testValue > lower)
			return true;
		return false;
	}

	private int binaryToInt(String value) {
		char[] s = value.toCharArray();
		int intValue = 0;
		for (int i = 0; i < s.length; i++) {
			intValue += Integer.parseInt("s[i]") * (s.length - 1 - i);
		}
		return intValue;
	}

	private int lengthOfBitString(String upper) {
		char[] s = upper.toCharArray();
		int i = 0;
		while (i < s.length) {
			if (s[i] == '1')
				break;
			i++;
		}
		return s.length - i;
	}

	@Override
	public void evaluate(BinarySolution solution) {
		String solutionString = "";
		String evaluationResultString = "";
		BitSet bitset = solution.getVariableValue(0);
		solutionString = bitset.toString();
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

	@Override
	protected int getBitsPerVariable(int variable) {
		return bits;
	}

}
