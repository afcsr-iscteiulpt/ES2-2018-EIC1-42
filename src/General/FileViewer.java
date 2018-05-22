package General;

import java.io.BufferedReader;
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
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }	
	}
	
	public static void viewLateXPDF(String latexpath, String filename) throws IOException {
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", "cd " + latexpath + " && start " + filename + ".pdf");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }
	}
	
	public void create_view_LateX() throws IOException {
		
		String latexpath =  sharedClass.getAdministrador().getProblemsDir();
		String latexfilename = null;
		
		if(sharedClass.getProblem().getType().equals("Double")) {
			latexpath = latexpath + "ExperimentsDouble/latex";
			latexfilename = "ExperimentsDouble";
		} else if(sharedClass.getProblem().getType().equals("Integer")) {
			latexpath = latexpath + "ExperimentsInteger/latex";
			latexfilename = "ExperimentsInteger";
		}
		
		createLateXPDF(latexpath, latexfilename);
		viewLateXPDF(latexpath, latexfilename);
	}
}
