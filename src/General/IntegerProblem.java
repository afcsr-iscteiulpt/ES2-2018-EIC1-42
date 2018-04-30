package General;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;

public class IntegerProblem extends AbstractIntegerProblem{
	private Problem problema;
	

	public IntegerProblem(Problem prob) {
		this(prob.getVariablesArray().size());
		problema = prob;
	}
	
	public IntegerProblem(int num_Var) {
		setNumberOfVariables(num_Var);
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
	public void evaluate(IntegerSolution Sol) {
		// TODO Auto-generated method stub
		// Ir buscar e correr o jar com uma função de evaluate aqui ...
		//sol.setObjective(arg0, arg1);
	}
}
