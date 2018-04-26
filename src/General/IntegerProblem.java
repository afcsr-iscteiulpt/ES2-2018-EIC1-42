package General;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;

public class IntegerProblem extends AbstractIntegerProblem{
	
	public IntegerProblem(int num_Var) {
		setNumberOfVariables(num_Var);
		setNumberOfObjectives(2);//ver objetivos (perguntar ao utilizador)default:2
		setName("getProblemName");//apartir da GUI
		
		List<Integer> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Integer> upperLimit = new ArrayList<>(getNumberOfVariables());
		
		for(int i = 0; i< getNumberOfVariables(); i++) {
			lowerLimit.add(/*get(i).lowerlimit*/-1);//ir buscar todos os limites inferiores postos na GUI
			upperLimit.add(/*get(i).lowerlimit*/1);//ir buscar todos os limites superiores postos na GUI
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
