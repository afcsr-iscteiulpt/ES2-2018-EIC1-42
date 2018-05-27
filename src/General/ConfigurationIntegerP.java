package General;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoFront;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class ConfigurationIntegerP extends Configuration {
	private static Problem problemToRun;
	private static String problemType;
	private static ArrayList<String> alg;
	private static String path;
	private static final int INDEPENDENT_RUNS_NUMBER = 5;
	private static final int MAX_EVALUATIONS = 5;

	/**
	 * 
	 * Creates the configuration for a Binary Problem
	 * 
	 * @param path
	 * @param ToRun
	 */

	public ConfigurationIntegerP(String path, Problem ToRun) {
		super(path, ToRun);
		this.path = path;
		problemToRun = ToRun;
		problemType = ToRun.getType();
		alg = ToRun.getAlgorithms();
	}

	static List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> configureAlgorithmList(
			List<ExperimentProblem<IntegerSolution>> problemList) {

		List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> algorithms = new ArrayList<>();

		for (int i = 0; i < problemList.size(); i++) {
			for (int j = 0; j < alg.size(); j++) {
				switch (alg.get(j)) {
				case "NSGAII":
					Algorithm<List<IntegerSolution>> algorithmnsgaii = new NSGAIIBuilder<>(
							problemList.get(i).getProblem(), new IntegerSBXCrossover(0.9, 20.0),
							new IntegerPolynomialMutation(1 / problemList.get(i).getProblem().getNumberOfVariables(),
									20.0)).setMaxEvaluations(MAX_EVALUATIONS).setPopulationSize(100).build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmnsgaii, "NSGAII", problemList.get(i).getTag()));
					break;

				case "SMSEMOA":
					Algorithm<List<IntegerSolution>> algorithmsmsemoa = new SMSEMOABuilder<>(
							problemList.get(i).getProblem(), new IntegerSBXCrossover(0.9, 20.0),
							new IntegerPolynomialMutation(1 / problemList.get(i).getProblem().getNumberOfVariables(),
									20.0)).setMaxEvaluations(MAX_EVALUATIONS).build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmsmsemoa, "SMSEMOA", problemList.get(i).getTag()));
					break;

				case "MOCell":
					Algorithm<List<IntegerSolution>> algorithmmocell = new MOCellBuilder<>(
							problemList.get(i).getProblem(), new IntegerSBXCrossover(0.9, 20.0),
							new IntegerPolynomialMutation(1 / problemList.get(i).getProblem().getNumberOfVariables(),
									20.0)).setMaxEvaluations(MAX_EVALUATIONS).build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmmocell, "MOCell", problemList.get(i).getTag()));
					break;

				case "PAES":
					Algorithm<List<IntegerSolution>> algorithmpaes = new PAESBuilder<>(problemList.get(i).getProblem())
					.setMaxEvaluations(MAX_EVALUATIONS).setArchiveSize(100).setBiSections(2)
					.setMutationOperator(new IntegerPolynomialMutation(
							1 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
					.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmpaes, "PAES", problemList.get(i).getTag()));
					break;

				case "RandomSearch":
					Algorithm<List<IntegerSolution>> algorithmrandomsearch = new RandomSearchBuilder<>(
							problemList.get(i).getProblem()).setMaxEvaluations(MAX_EVALUATIONS).build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmrandomsearch, "RandomSearch",
							problemList.get(i).getTag()));
					break;

				default:
					System.out.println("Algorithm not found/adjusted to problem type");
					break;
				}
			}
		}

		return algorithms;
	}

	@Override
	public void Run() throws IOException {
		String experimentBaseDirectory = path;

		List<ExperimentProblem<IntegerSolution>> problemList = new ArrayList<>();
		problemList.add(new ExperimentProblem<>(new IntegerProblem(problemToRun)));

		List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> algorithmList = configureAlgorithmList(
				problemList);

		Experiment<IntegerSolution, List<IntegerSolution>> experiment = new ExperimentBuilder<IntegerSolution, List<IntegerSolution>>(
				"ExperimentsInteger").setAlgorithmList(algorithmList).setProblemList(problemList)
				.setExperimentBaseDirectory(experimentBaseDirectory).setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setReferenceFrontDirectory(experimentBaseDirectory + "/referenceFronts")
				.setIndicatorList(Arrays.asList(new PISAHypervolume<IntegerSolution>()))
				.setIndependentRuns(INDEPENDENT_RUNS_NUMBER).setNumberOfCores(8).build();

		new ExecuteAlgorithms<>(experiment).run();
		new GenerateReferenceParetoFront(experiment).run();
		new ComputeQualityIndicators<>(experiment).run();
		new GenerateLatexTablesWithStatistics(experiment).run();
		new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run();
	};
}
