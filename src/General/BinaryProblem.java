package General;


import java.util.BitSet;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

public class BinaryProblem  extends AbstractBinaryProblem {
	private Problem problema;
	
	public BinaryProblem(Problem problema,int num_var) {
		this(problema.getVariablesArray().size());
		createBinaryProblem(problema);
	}
	
	public BinaryProblem(int num_Var) {
		setNumberOfVariables(num_Var);

	}
	
	public void createBinaryProblem(Problem problema) {
		this.problema = problema;
		setNumberOfObjectives(2);//ver objetivos (perguntar ao utilizador) !!conferir com a função evaluate!! default:2
		setName(problema.getName());
	}

	@Override
	public void evaluate(BinarySolution solution) {
		
	    int counterOnes;
	    int counterZeroes;
	    counterOnes = 0;
	    counterZeroes = 0;

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
