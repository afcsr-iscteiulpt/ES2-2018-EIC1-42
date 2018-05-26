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
	 * Construtor da classe FileViewer
	 * 
	 * @param sharedClass
	 */
	public FileViewer(SharedClass sharedClass) {
		this.sharedClass = sharedClass;
	}

	/**
	 * 
	 * Esta função é utilizada para criar o ficheiro LateX em formato .pdf
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
	 * Esta função é utilizada para visuzalizar o ficheiro LateX em formato .pdf
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
	 * Esta função é usada para criar o ficheiro R em formato .eps
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
	 * Esta função é utilizada para visualizar o ficheiro R em formato .eps
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
	 * Esta é a função que irá ser chamada pela SharedClass de modo a criar os
	 * ficheiros LateX em formato .pdf, criar o ficheiro R em formato .eps e
	 * visuzaliar os mesmos
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