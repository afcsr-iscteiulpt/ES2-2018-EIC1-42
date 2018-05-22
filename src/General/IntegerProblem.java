package General;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;

public class IntegerProblem extends AbstractIntegerProblem{
	private Problem problema;


	public IntegerProblem(Problem problema) {
		this(problema.getVariablesArray().size());
		createIntegerProblem(problema);
	}

	public IntegerProblem(int num_Var) {
		setNumberOfVariables(num_Var);
	}

	public void createIntegerProblem(Problem problema) {
		this.problema = problema;
		setNumberOfObjectives(/*problema.getOjetivos()*/2);//ver objetivos (perguntar ao utilizador)default:2
		setName(problema.getName());

		List<Integer> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Integer> upperLimit = new ArrayList<>(getNumberOfVariables());

		for(int i = 0; i< getNumberOfVariables(); i++) {
			lowerLimit.add(problema.getVariablesArray().get(i).getMinI());
			upperLimit.add(problema.getVariablesArray().get(i).getMaxI());
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	@Override
	public void evaluate(IntegerSolution solution) {

//		if (this.problema.getAlgorithms()!= null) { //mudar para a parte do jar
//			evaluateJar(solution);
//		}else {
			double[] fx = new double[getNumberOfObjectives()];
			int[] x = new int[getNumberOfVariables()];
			for (int i = 0; i < solution.getNumberOfVariables(); i++) {
				x[i] = solution.getVariableValue(i) ;
			}

			fx[0] = 0;
			for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
				fx[0] += Math.abs(x[0]+Math.random()*10); // Example for testing
			}

			fx[1] = 0;
			for (int var = 0; var < solution.getNumberOfVariables(); var++) {
				fx[1] += Math.abs(x[1]+Math.random()*10); // Example for testing
			}

			solution.setObjective(0, fx[0]);
			solution.setObjective(1, fx[1]);
		//}
		// TODO Auto-generated method stub
		// Ir buscar e correr o jar com uma fun��o de evaluate aqui ...
		//sol.setObjective(arg0, arg1);
	}

	public void evaluateJar(IntegerSolution solution){
		String solutionString ="";
		String evaluationResultString ="";
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			solutionString = solutionString + " " + solution.getVariableValue(i);  
		}
		try {
			String line;
			Process p = Runtime.getRuntime().exec("java -jar c:\\NMMin.jar" + " " + solutionString);
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
			solution.setObjective(i, Integer.parseInt(individualEvaluationCriteria[i]));    
		}	    
	}	  
}
