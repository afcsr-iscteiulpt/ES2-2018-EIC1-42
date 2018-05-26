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
	
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", "cd " + latexpath + "latex" + " && pdflatex " + filename + ".tex");
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
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", "cd " + latexpath +"latex" + " && start " + filename + ".pdf");
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
	 
	public static void createR(String Rpath, String filename) {
		try {
		Process process = new ProcessBuilder("C:/Program Files/R/R-3.4.3/bin/RScript.exe", "HV.Boxplot.R")
				.directory(new File(Rpath + "R")).start();
		
//		Process process2 = new ProcessBuilder("C:/Program Files/MiKTeX 2.9/miktex/bin/x64/miktex-pdflatex.exe", filename + ".tex")
//							.directory(new File("C:/Users/bruno/Desktop/ExperimentsDouble/latex")).start();
		
		
		} catch (IOException e) {}
	}
	
	public static void viewR(String Rpath, String filename) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", "cd " + Rpath +"R" + " && start " + "HV.Boxplot.eps");
        builder.redirectErrorStream(true);
        Process p = builder.start();
	}
	
	public void create_view_LateX() throws IOException {
		
		String path =  sharedClass.getAdministrador().getProblemsDir();
		String filename = null;
		
		switch(sharedClass.getProblem().getType()) {
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
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		viewLateXPDF(path, filename);
		viewR(path, filename);
	}


	public static void main(String[] args) throws IOException {
//		createLateXPDF("C:/Users/bruno/Desktop/ExperimentsDouble/latex","ExperimentsDouble");
//		createR("C:/Users/bruno/Desktop/ExperimentsDouble/latex","ExperimentsDouble");
	}


}