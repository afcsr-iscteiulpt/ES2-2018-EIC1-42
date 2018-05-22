package General;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

public class BinaryProblem  extends AbstractBinaryProblem {

	private Problem problema;

	private int upper;
	private int lower;

	public BinaryProblem(Problem problema,int num_var) {
		this(problema.getVariablesArray().size());
		createBinaryProblem(problema);
	}

	public BinaryProblem(int num_Var) {
		setNumberOfVariables(num_Var);

	}

	public void createBinaryProblem(Problem problema) {
		this.problema = problema;
		setNumberOfObjectives(problema.getObjNumber());//ver objetivos (perguntar ao utilizador) !!conferir com a função evaluate!! default:2

		setName(problema.getName());
	}

	//--------<changed>------------
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
			intValue += Integer.parseInt("s[i]")*(s.length - 1 - i);
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
	//--------<changed>------------
	@Override
	public void evaluate(BinarySolution solution) {

//		if (this.problema.getAlgorithms()!= null) { //mudar para a parte do jar
//			evaluateJar(solution);
//		}else {
			int counterOnes=0;
			int counterZeroes=0;
			
			BitSet bitset = solution.getVariableValue(0) ;
			for (int i = 0; i < bitset.length(); i++) {
				if (bitset.get(i)) {
					counterOnes++;
				} else {
					counterZeroes++;
				}
			}
			// OneZeroMax is a maximization problem: multiply by -1 to minimize
			solution.setObjective(0, -1.0 * counterOnes);
			solution.setObjective(1, -1.0 * counterZeroes);		  
//		}
		// TODO Auto-generated method stub
		// Ir buscar e correr o jar com uma função de evaluate aqui ...
		//sol.setObjective(arg0, arg1);
	}

	public void evaluateJar(BinarySolution solution){
		String solutionString ="";
		String evaluationResultString ="";
		BitSet bitset = solution.getVariableValue(0) ;
		solutionString = bitset.toString();
		try {
			String line;
			Process p = Runtime.getRuntime().exec("java -jar c:\\OneZeroMax.jar" + " " + solutionString);
			BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = brinput.readLine()) != null) {
				evaluationResultString+=line;
			}
			brinput.close();
			p.waitFor();
		}
		catch (Exception err) {
			err.printStackTrace();
		}

		String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
		// It is assumed that all evaluated criteria are returned in the same result string
		for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
			solution.setObjective(i, Double.parseDouble(individualEvaluationCriteria[i]));
		}
	}

	@Override
	protected int getBitsPerVariable(int variable) {
		// TODO Auto-generated method stub
		return 0;//getfromGUIvariablenum().bitT_or_F();
	}

}
