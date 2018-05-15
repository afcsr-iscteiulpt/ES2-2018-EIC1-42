package General;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHCBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.impl.crossover.HUXCrossover;
import org.uma.jmetal.operator.impl.crossover.SinglePointCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.selection.RandomSelection;
import org.uma.jmetal.operator.impl.selection.RankingAndCrowdingSelection;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoFront;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class ConfigurationBinaryP extends Configuration{
	private static Problem problemToRun;
	private static String problemType;
	private static ArrayList<String> alg;
	private static String path;
	
	public ConfigurationBinaryP(String path, Problem ToRun) {
		super(path, ToRun);
		this.path=path;
		problemToRun=ToRun;
		problemType=ToRun.getType();
		alg=ToRun.getAlgorithms();
	}
	
	static List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> configureAlgorithmList(
	          List<ExperimentProblem<BinarySolution>> problemList) {
	    List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> algorithms = new ArrayList<>();

	    for (int i = 0; i < problemList.size(); i++) {
	    	switch(alg.get(i)) {
	    	case "NSGAII":
	    		Algorithm<List<BinarySolution>> algorithmnsgaii = new NSGAIIBuilder<>(
	    				problemList.get(i).getProblem(),
	    				new SinglePointCrossover(1.0),
	    				new BitFlipMutation(1.0 / ((BinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0)))
	    		.setMaxEvaluations(500)
	    		.setPopulationSize(100)
	    		.build();
	    		algorithms.add(new ExperimentAlgorithm<>(algorithmnsgaii, "NSGAII", problemList.get(i).getTag()));
	    		break;
	    		
	    	case "SMSEMOA":
	    		Algorithm<List<BinarySolution>> algorithmsmsemoa = new SMSEMOABuilder<>(
	    				problemList.get(i).getProblem(),
	    				new SinglePointCrossover(1.0),
	    				new BitFlipMutation(1.0 / ((BinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0)))
	    		.setMaxEvaluations(500)
	    		.build();      
	    	    algorithms.add(new ExperimentAlgorithm<>(algorithmsmsemoa, "SMSEMOA", problemList.get(i).getTag()));
	    	    break;
	    	    
	    	case "MOCell":
	    		Algorithm<List<BinarySolution>> algorithmmocell = new MOCellBuilder<>(
	    				problemList.get(i).getProblem(),
	    				new SinglePointCrossover(1.0), new BitFlipMutation(1.0 / ((BinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0)))
	    		.setMaxEvaluations(500)
	    		.build();
	    	    algorithms.add(new ExperimentAlgorithm<>(algorithmmocell, "MOCell", problemList.get(i).getTag()));
	    	    break;
	    	    
	    	case "MOCH":
	    		Algorithm<List<BinarySolution>> algorithmmochc = new MOCHCBuilder(
	    				(BinaryProblem) problemList.get(i).getProblem())
	    		.setMaxEvaluations(500)
	    		.setCrossover(new HUXCrossover(1.0))
	    		.setNewGenerationSelection(new RankingAndCrowdingSelection<BinarySolution>(100))
	    		.setCataclysmicMutation(new BitFlipMutation(0.35))
	    		.setParentSelection(new RandomSelection<BinarySolution>())
	    		.setEvaluator(new SequentialSolutionListEvaluator<BinarySolution>())
	    		.build();
	    		algorithms.add(new ExperimentAlgorithm<>(algorithmmochc, "MOCH", problemList.get(i).getTag()));
	    		break;
	    		
	    	case "PAES":
	    		Algorithm<List<BinarySolution>> algorithmpaes = new PAESBuilder<>(
	    				problemList.get(i).getProblem())
	    		.setMaxEvaluations(500)
	    		.setArchiveSize(100)
	    		.setBiSections(2)
	    		.setMutationOperator(new BitFlipMutation(1.0 / ((BinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0)))
	    		.build();
	    	    algorithms.add(new ExperimentAlgorithm<>(algorithmpaes, "PAES", problemList.get(i).getTag()));
	    	    break;
	    	    
	    	case "RandomSearch":
	    		Algorithm<List<BinarySolution>> algorithmrandomsearch = new RandomSearchBuilder<>(
	    				problemList.get(i).getProblem())
	    		.setMaxEvaluations(500)
	    		.build();
	    		algorithms.add(new ExperimentAlgorithm<>(algorithmrandomsearch, "RandomSearch", problemList.get(i).getTag()));
	    		break;
	    		
	    	case "SPEA2":
	    		Algorithm<List<BinarySolution>> algorithmspea2 = new SPEA2Builder<>(
	    				problemList.get(i).getProblem(),
	    				new SinglePointCrossover(1.0),
	    				new BitFlipMutation(1.0 / ((BinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0)))
	    		.setMaxIterations(500)
	    		.build();
	    		algorithms.add(new ExperimentAlgorithm<>(algorithmspea2, "SPEA2", problemList.get(i).getTag()));

	    	default:
	    		System.out.println("Algorithm not found/adjusted to problem type");
	    		break;
	    	}
	    }
	    return algorithms;
	  }
	
	@Override
	public void Run() throws IOException {
//		String experimentBaseDirectory = "experimentBaseDirectory";
		String experimentBaseDirectory = path;
		
	    List<ExperimentProblem<BinarySolution>> problemList = new ArrayList<>();
	    problemList.add(new ExperimentProblem<>(new BinaryProblem(problemToRun,problemToRun.getVariablesArray().size()/*Confirm pls*/)));

	    List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> algorithmList =
	            configureAlgorithmList(problemList);

	    Experiment<BinarySolution, List<BinarySolution>> experiment =
	        new ExperimentBuilder<BinarySolution, List<BinarySolution>>("ExperimentsBinary")
	            .setAlgorithmList(algorithmList)
	            .setProblemList(problemList)
	            .setExperimentBaseDirectory(experimentBaseDirectory)
	            .setOutputParetoFrontFileName("FUN")
	            .setOutputParetoSetFileName("VAR")
	            .setReferenceFrontDirectory(experimentBaseDirectory+"/referenceFronts")
	            .setIndicatorList(Arrays.asList(new PISAHypervolume<BinarySolution>()))
	            .setIndependentRuns(5)
	            .setNumberOfCores(8)
	            .build();

	    new ExecuteAlgorithms<>(experiment).run();
	    new GenerateReferenceParetoFront(experiment).run();
	    new ComputeQualityIndicators<>(experiment).run() ;
	    new GenerateLatexTablesWithStatistics(experiment).run() ;
	    new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run() ;
	};
}
