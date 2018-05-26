package General;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileViewer {

	private SharedClass sharedClass;

	
	public FileViewer(SharedClass sharedClass) {
		this.sharedClass=sharedClass;
	}
	
	public static void createLateXPDF(String latexpath, String filename) throws IOException {
	
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", "cd " + latexpath + " && pdflatex " + filename + ".tex");
            builder.redirectErrorStream(true);
            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            while (true) {
//                line = r.readLine();
//                if (line == null) { break; }
//                System.out.println(line);
//            }	
	}
	
	public static void viewLateXPDF(String latexpath, String filename) throws IOException {
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", "cd " + latexpath + " && start " + filename + ".pdf");
            builder.redirectErrorStream(true);
            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            while (true) {
//                line = r.readLine();
//                if (line == null) { break; }
//                System.out.println(line);
//            }
	}
	 
	public static void viewR(String latexpath, String filename) {
		try {
		Process process = new ProcessBuilder("C:/Program Files/R/R-3.4.3/bin/RScript.exe", "HV.Boxplot.R")
				.directory(new File("C:/Users/bruno/Desktop/ExperimentsDouble/R")).start();
		
//		Process process2 = new ProcessBuilder("C:/Program Files/MiKTeX 2.9/miktex/bin/x64/miktex-pdflatex.exe", filename + ".tex")
//							.directory(new File("C:/Users/bruno/Desktop/ExperimentsDouble/latex")).start();
		
		
		} catch (IOException e) {}
	}
	
	public void create_view_LateX() throws IOException {
		
		String latexpath =  sharedClass.getAdministrador().getProblemsDir();
		String latexfilename = null;
		
		switch(sharedClass.getProblem().getType()) {
		case "Double":
			latexpath = latexpath + "ExperimentsDouble/latex";
			latexfilename = "ExperimentsDouble";
			break;
		case "Integer":
			latexpath = latexpath + "ExperimentsInteger/latex";
			latexfilename = "ExperimentsInteger";
			break;
		case "Binary":
			latexpath = latexpath + "ExperimentsBinary/latex";
			latexfilename = "ExperimentsBinary";
			break;
		default:
			System.out.println("Latex case not found");
			break;
		}
		
//		createLateXPDF(latexpath, latexfilename);
//		viewLateXPDF(latexpath, latexfilename);
		viewR(latexpath, latexfilename);
	}


	public static void main(String[] args) throws IOException {
//		createLateXPDF("C:/Users/bruno/Desktop/ExperimentsDouble/latex","ExperimentsDouble");
		viewR("C:/Users/bruno/Desktop/ExperimentsDouble/latex","ExperimentsDouble");
	}


}