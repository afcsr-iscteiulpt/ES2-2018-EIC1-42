package General;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileViewer {

	private SharedClass sharedClass;

	/**
	 * 
	 * Creates the FileViewer class that has methods to interpret Latex and R files
	 * in a .pdf file
	 * 
	 * @param sharedClass
	 */

	public FileViewer(SharedClass sharedClass) {
		this.sharedClass = sharedClass;
	}

	/**
	 * 
	 * Translates the Latex file to .pdf
	 * 
	 * @param latexpath
	 * @param filename
	 * @throws IOException
	 */
	public static void createLateXPDF(String latexpath, String filename) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"cd " + latexpath + "latex" + " && pdflatex " + filename + ".tex");
		builder.redirectErrorStream(true);
		Process p = builder.start();
	}

	/**
	 * 
	 * Shows the Latex file in .pdf
	 * 
	 * @param latexpath
	 * @param filename
	 * @throws IOException
	 */
	public static void viewLateXPDF(String latexpath, String filename) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"cd " + latexpath + "latex" + " && start " + filename + ".pdf");
		builder.redirectErrorStream(true);
		Process p = builder.start();
	}

	/**
	 * 
	 * Translates the R file to .eps
	 * 
	 * @param Rpath
	 * @param filename
	 */
	public static void createR(String Rpath, String filename) {
		try {
			Process process = new ProcessBuilder("C:/Program Files/R/R-3.4.3/bin/RScript.exe", "HV.Boxplot.R")
					.directory(new File(Rpath + "R")).start();
		} catch (IOException e) {
		}
	}

	/**
	 * 
	 * Reads the R file in .eps
	 * 
	 * @param Rpath
	 * @param filename
	 * @throws IOException
	 */
	public static void viewR(String Rpath, String filename) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"cd " + Rpath + "R" + " && start " + "HV.Boxplot.eps");
		builder.redirectErrorStream(true);
		Process p = builder.start();
	}

	/**
	 * 
	 * Creates the latex file
	 * 
	 * @throws IOException
	 */
	public void create_view_LateX() throws IOException {

		String path = sharedClass.getAdministrador().getProblemsDir();
		String filename = null;

		switch (sharedClass.getProblem().getType()) {
		case "Double":
			path = path + "ExperimentsDouble/";
			filename = "ExperimentsDouble";
			break;
		case "Integer":
			path = path + "ExperimentsInteger/";
			filename = "ExperimentsInteger";
			break;
		case "Binary":
			path = path + "ExperimentsBinary/";
			filename = "ExperimentsBinary";
			break;
		default:
			System.out.println("Latex case not found");
			break;
		}

		createLateXPDF(path, filename);
		createR(path, filename);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		viewLateXPDF(path, filename);
		viewR(path, filename);
	}

}