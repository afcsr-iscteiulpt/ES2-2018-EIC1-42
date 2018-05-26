package General;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.impl.AbstractGenericSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoSetAndFrontFromDoubleSolutions;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class Configuration {
	private static Problem problemToRun;
	private static String problemType;
	private static String alg;

	/**
	 * 
	 * Construtor de uma configuração (classe mãe das restantes configurações
	 * 
	 * @param path
	 * @param ToRun
	 */

	public Configuration(String path, Problem ToRun) {
		problemToRun = ToRun;
		problemType = ToRun.getType();
		alg = ToRun.getAlgorithms().toString();
	}

	/**
	 * Função executada em caso de erro
	 * 
	 * @throws IOException
	 */
	public void Run() throws IOException {
		System.out.println("Configuration:: use a Override especific configuration**.run");
	}

	public String getConfString() {
		return ("" + problemToRun.stringConvert() + " algorithm: " + alg + " ... type : " + problemType);
	}

}

// TODO built a configuration for integer/double/binary problems
