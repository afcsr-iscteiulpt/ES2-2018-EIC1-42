package General;

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
		
		// TODO Auto-generated method stub
		// Ir buscar e correr o jar com uma função de evaluate aqui ...
		//sol.setObjective(arg0, arg1);
	}
}
