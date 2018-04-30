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
	private static String alg;
	
	public Configuration(Problem ToRun,String Algorithm) {
		problemToRun=ToRun;
		alg=Algorithm;
	}
	
	//automatic configuration of algorithms for the problem..(Only NSGAII for now)  review "Double"Solution and AlgorithmBuilder chosen
  static List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(
          List<ExperimentProblem<DoubleSolution>> problemList) {
	  
    List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();

    for (int i = 0; i < problemList.size(); i++) {
      Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<>(
              problemList.get(i).getProblem(),
              new SBXCrossover(1.0, 5),
              new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
              .setMaxEvaluations(500)
              .setPopulationSize(100)
              .build();
      algorithms.add(new ExperimentAlgorithm<>(algorithm, alg /*"NSGAII"*/, problemList.get(i).getTag()));
    }
   
    return algorithms;
  }
  
  public void RunADoule() throws IOException {
	    String experimentBaseDirectory = "experimentBaseDirectory";

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
	
	//TODO built a configuration for integer/double/binary problems
	
