package General;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class DoubleProblem extends AbstractDoubleProblem{
	
	public DoubleProblem(int num_Var) {
		setNumberOfVariables(num_Var);
		setNumberOfObjectives(2);//ver objetivos (perguntar ao utilizador) !!conferir com a função evaluate!! default:2
		setName("getProblemName");//apartir da GUI
		
		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());
		
		for(int i = 0; i< getNumberOfVariables(); i++) {
			lowerLimit.add(/*get(i).lowerlimit*/-1.0);//ir buscar todos os limites inferiores postos na GUI
			upperLimit.add(/*get(i).lowerlimit*/1.0);//ir buscar todos os limites superiores postos na GUI
		}
		
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
		
	}

	@Override
	public void evaluate(DoubleSolution sol) {
		// TODO Auto-generated method stub
		// Ir buscar e correr o jar com uma função de evaluate aqui ...
		//sol.setObjective(arg0, arg1);
	}
}
