package General;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileViewer {

	private SharedClass sharedClass;
	
	public FileViewer(SharedClass sharedClass) {
		this.sharedClass=sharedClass;
	}
	
	
	public static void createLateXPDF() throws IOException {
		String latexpath = "C:/Users/bruno/Desktop/RunningAutomaticConfigurationTest/latex/";
		
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe","/c", "cd " + latexpath + " && pdflatex RunningAutomaticConfigurationTest.tex");
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
	
	public static void viewLateXPDF() throws IOException {
		String latexpath = "C:/Users/bruno/Desktop/RunningAutomaticConfigurationTest/latex/";
		
		ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe","/c", "cd " + latexpath + " && start RunningAutomaticConfigurationTest.pdf");
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
	
	public static void createRPDF() {
		
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		viewLateXPDF();
	}

}
