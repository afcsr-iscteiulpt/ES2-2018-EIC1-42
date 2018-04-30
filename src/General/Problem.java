package General;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Problem {

	private String name;
	private String description;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private ArrayList<Variable> variablesArray;

	public Problem() {

	}

	public Problem(String name, String description, String email, ArrayList<Variable> variablesArray) {
		this.name = name;
		this.description = description;
		this.email = email;
		this.variablesArray = variablesArray;
	}


	@XmlElement
	public String getName() {
		return name;
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