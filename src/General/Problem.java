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
	private String path;
	private ArrayList<String> objectivesArray;

	/**
	 * 
	 * Construtor da classe Problem
	 * 
	 * @param name
	 * @param description
	 * @param email
	 * @param variablesArray
	 * @param algorithms
	 * @param numberOfDays
	 * @param path
	 * @param objectivesArray
	 */

	public Problem(String name, String description, String email, ArrayList<Variable> variablesArray,
			ArrayList<String> algorithms, int numberOfDays, String path, ArrayList<String> objectivesArray) {
		this.name = name;
		this.description = description;
		this.email = email;
		this.variablesArray = variablesArray;
		this.algorithms = algorithms;
		this.numberOfDays = numberOfDays;
		this.path = path;
		this.objectivesArray = objectivesArray;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public void setType(String type) {
		this.type = type;
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

	@XmlElement
	public void setPath(String path) {
		this.path = path;
	}

	@XmlElement
	public String getPath() {
		return path;
	}

	@XmlElement
	public ArrayList<String> getObjectivesArray() {
		return objectivesArray;
	}

	@XmlElement
	public void setObjectivesArray(ArrayList<String> newarray) {
		this.objectivesArray = newarray;
	}

	public int getObjNumber() {
		return objectivesArray.size();
	}

	public String stringConvert() {
		String s = name + " 			" + description;
		return s;
	}

}