package General;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class DoubleProblem extends AbstractDoubleProblem{
	private Problem problema;
	
	public DoubleProblem(Problem problema) {
		this(problema.getVariablesArray().size());
		createDoubleProblem(problema);
	}
	
	public DoubleProblem(int num_Var) {
		this.setNumberOfVariables(num_Var);
	}
	
	public void createDoubleProblem(Problem problema) {
		this.problema = problema;
		this.setNumberOfObjectives(problema.getObjNumber());//ver objetivos (perguntar ao utilizador) !!conferir com a fun��o evaluate!! default:2
		this.setName(problema.getName());
		
		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());
		
		for(int i = 0; i< getNumberOfVariables(); i++) {
			lowerLimit.add(problema.getVariablesArray().get(i).getMinD());
			upperLimit.add(problema.getVariablesArray().get(i).getMaxD());
		}
		
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	@Override
	public void evaluate(DoubleSolution solution) {
		// TODO Auto-generated method stub
		// Ir buscar e correr o jar com uma fun��o de evaluate aqui ...
		//sol.setObjective(arg0, arg1);
		if (problema.getPath() != "") { //mudar para a parte do jar
			evaluateJar(solution);
		}else { 
			double[] fx = new double[getNumberOfObjectives()];
			double[] x = new double[getNumberOfVariables()];
			for (int i = 0; i < solution.getNumberOfVariables(); i++) {
				x[i] = solution.getVariableValue(i) ;
			}
			
			for(int i = 0 ; i < solution.getNumberOfObjectives();i++) {
				fx[i] = 0;
				for (int var = 0; var < solution.getNumberOfObjectives(); var++) {
					fx[i] += Math.abs(x[i]+Math.random()*10); // Example for testing
				}
				solution.setObjective(i, fx[i]);
			}	
		}
	}
	
	public void evaluateJar(DoubleSolution solution){
		String solutionString ="";
		String evaluationResultString ="";
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			solutionString = solutionString + " " + solution.getVariableValue(i);  
		}
		try {
			String line;
			Process p = Runtime.getRuntime().exec("java -jar " + problema.getPath()  + " " + solutionString);
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
}
