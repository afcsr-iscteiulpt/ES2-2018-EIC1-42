package General;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Problem {

	private String name;
	private String description;
	private String email;
	private ArrayList<Variable> variablesArray;
	private ArrayList<String> algorithms;
	private int numberOfDays;
	private String type;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Problem() {

	}

	public Problem(String name, String description, String email, ArrayList<Variable> variablesArray, ArrayList<String> algorithms, int numberOfDays) {
		this.name = name;
		this.description = description;
		this.email = email;
		this.variablesArray = variablesArray;
		this.algorithms = algorithms;
		this.numberOfDays = numberOfDays;
		
		
		this.type = variablesArray.get(0).getType() ;
	}


	public ArrayList<String> getAlgorithms() {
		return algorithms;
	}

	public void setAlgorithms(ArrayList<String> algorithms) {
		this.algorithms = algorithms;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	@XmlElement
	public String getName() {
		return name;
	}
	
	@XmlElement
	public String getType() {
		return type;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "vari")
	public ArrayList<Variable> getVariablesArray() {
		return variablesArray;
	}

	@XmlElement
	public void setVariablesArray(ArrayList<Variable> variablesArray) {
		this.variablesArray = variablesArray;
	}
	
	public String stringConvert(){
		String s = name + " 			"+description;
		return s;
	}

}