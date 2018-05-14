package General;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.gde3.GDE3Builder;
import org.uma.jmetal.algorithm.multiobjective.ibea.IBEABuilder;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder.Variant;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
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


public class ConfigurationDoubleP extends Configuration{
	private static Problem problemToRun;
	private static String problemType;
	private static ArrayList<String> alg;

	public ConfigurationDoubleP(Problem ToRun) {
		super(ToRun);
		problemToRun=ToRun;
		problemType=ToRun.getType();
		alg=ToRun.getAlgorithms();
	}


	//automatic configuration of algorithms for the problem..(Only NSGAII for now)  review "Double"Solution and AlgorithmBuilder chosen
	static List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(
			List<ExperimentProblem<DoubleSolution>> problemList) {

		List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();

		for (int i = 0; i < problemList.size(); i++) {
			switch(alg.get(i)) {
				case "NSGAII":
					Algorithm<List<DoubleSolution>> algorithmnsgaii = new NSGAIIBuilder<>(
							problemList.get(i).getProblem(),
							new SBXCrossover(1.0, 5),
							new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
							.setMaxEvaluations(500)
							.setPopulationSize(100)
							.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmnsgaii, "NSGAII" , problemList.get(i).getTag()));
					break;
					
				case "SMSEMOA":
					Algorithm<List<DoubleSolution>> algorithmsmsemoa = new SMSEMOABuilder<>(
							problemList.get(i).getProblem(),
							new SBXCrossover(1.0, 5),
							new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
							.setMaxEvaluations(500)
							.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmsmsemoa, "SMSEMOA" , problemList.get(i).getTag()));
					break;
					
				case "GDE3":
					Algorithm<List<DoubleSolution>> algorithmgde3 = new GDE3Builder( 
							(org.uma.jmetal.problem.DoubleProblem) problemList.get(i).getProblem())
							.setMaxEvaluations(500)
							.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmgde3, "GDE3" , problemList.get(i).getTag()));
					break;
					
				case "IBEA":
					Algorithm<List<DoubleSolution>> algorithmibea = new IBEABuilder(
							problemList.get(i).getProblem())
							.setMaxEvaluations(500)
							.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmibea, "IBEA" , problemList.get(i).getTag()));
					break;
					
				case "MOCell":
					Algorithm<List<DoubleSolution>> algorithmmocell = new MOCellBuilder<>(
							problemList.get(i).getProblem(),
							new SBXCrossover(1.0, 5),
							new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
							.setMaxEvaluations(500)
							.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmmocell, "MOCell" , problemList.get(i).getTag()));
					break;
					
				case "MOEAD":
					Algorithm<List<DoubleSolution>> algorithmmoead = new MOEADBuilder(
							problemList.get(i).getProblem(), Variant.MOEAD) 
							.setMaxEvaluations(500)
							.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmmoead, "MOEAD" , problemList.get(i).getTag()));
					break; 
					
					// cuidar dos warnings embaixo
				case "PAES":
					Algorithm<List<DoubleSolution>> algorithmpaes = new PAESBuilder(
							problemList.get(i).getProblem())
							.setMaxEvaluations(500)
							.setArchiveSize(100)
							.setBiSections(2)
							.setMutationOperator(new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
							.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmpaes, "PAES" , problemList.get(i).getTag()));
					break;
					
				case "RandomSearch":
					Algorithm<List<DoubleSolution>> algorithmrandomsearch = new RandomSearchBuilder(
							problemList.get(i).getProblem())
							.setMaxEvaluations(500)
							.build();
					algorithms.add(new ExperimentAlgorithm<>(algorithmrandomsearch, "RandomSearch" , problemList.get(i).getTag()));
					break;
					
				default:
					System.out.println("Algorithm not found/adjusted to problem type");
					break;
			}
		}

		return algorithms;
	}

	@Override 
	public void Run() throws IOException {
		String experimentBaseDirectory = "/Users/albertoramos/Desktop";

		System.out.println(problemToRun.getName());
		List<ExperimentProblem<DoubleSolution>> problemList = new ArrayList<>();
		problemList.add(new ExperimentProblem<>(new DoubleProblem(problemToRun)));

		List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithmList =
				configureAlgorithmList(problemList);

		Experiment<DoubleSolution, List<DoubleSolution>> experiment =
				new ExperimentBuilder<DoubleSolution, List<DoubleSolution>>("RunningAutomaticConfigurationTest")
				.setAlgorithmList(algorithmList)
				.setProblemList(problemList)
				.setExperimentBaseDirectory(experimentBaseDirectory)
				.setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setReferenceFrontDirectory(experimentBaseDirectory+"/referenceFronts")
				.setIndicatorList(Arrays.asList(new PISAHypervolume<DoubleSolution>()))
				.setIndependentRuns(5)//default for project
				.setNumberOfCores(8)
				.build();

		new ExecuteAlgorithms<>(experiment).run();
		new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
		new ComputeQualityIndicators<>(experiment).run() ;
		new GenerateLatexTablesWithStatistics(experiment).run() ;
		new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run() ;

	}
}
