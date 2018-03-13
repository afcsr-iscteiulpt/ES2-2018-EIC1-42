import java.util.ArrayList;

public class Problem {

	private String name;
	private String description;
	private ArrayList<Variable> variablesArray;
	
	public Problem(String name, String description, ArrayList<Variable> variablesArray){
		this.name=name;
		this.description=description;
		this.variablesArray=variablesArray;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Variable> getVariablesArray() {
		return variablesArray;
	}

	public void setVariablesArray(ArrayList<Variable> variablesArray) {
		this.variablesArray = variablesArray;
	}
	
	
	
	
}
