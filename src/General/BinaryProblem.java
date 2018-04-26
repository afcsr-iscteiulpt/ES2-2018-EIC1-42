package General;


import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

public class BinaryProblem  extends AbstractBinaryProblem {
	private Problem problema;
	
	public BinaryProblem(Problem prob,int num_var) {
		this(prob.getVariablesArray().size());
		problema = prob;
	}
	
	public BinaryProblem(int num_Var) {
		setNumberOfVariables(num_Var);
		setNumberOfObjectives(2);//ver objetivos (perguntar ao utilizador) !!conferir com a função evaluate!! default:2
		setName(problema.getName());
	}

	@Override
	public void evaluate(BinarySolution sol) {
		// TODO Auto-generated method stub
		// Ir buscar e correr o jar com uma função de evaluate aqui ...
		//sol.setObjective(arg0, arg1);
	}

	@Override
	protected int getBitsPerVariable(int variable) {
		// TODO Auto-generated method stub
		return 0;//getfromGUIvariablenum().bitT_or_F();
	}

}
