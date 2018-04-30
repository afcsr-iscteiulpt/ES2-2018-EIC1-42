package General;

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
		this.setNumberOfObjectives(2);//ver objetivos (perguntar ao utilizador) !!conferir com a fun��o evaluate!! default:2
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
	public void evaluate(DoubleSolution sol) {
		// TODO Auto-generated method stub
		// Ir buscar e correr o jar com uma fun��o de evaluate aqui ...
		//sol.setObjective(arg0, arg1);
	    double[] fx = new double[getNumberOfObjectives()];
	    double[] x = new double[getNumberOfVariables()];
	    for (int i = 0; i < sol.getNumberOfVariables(); i++) {
	      x[i] = sol.getVariableValue(i) ;
	    }

	    fx[0] = 0.0;
	    for (int var = 0; var < sol.getNumberOfVariables() - 1; var++) {
		  fx[0] += Math.abs(x[0]); // Example for testing
	    }
	    
	    fx[1] = 0.0;
	    for (int var = 0; var < sol.getNumberOfVariables(); var++) {
	    	fx[1] += Math.abs(x[1]); // Example for testing
	    }

	    sol.setObjective(0, fx[0]);
	    sol.setObjective(1, fx[1]);
	}
}
