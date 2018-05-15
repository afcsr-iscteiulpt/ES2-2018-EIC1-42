package General;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Variable {

	private String name;
	private String type;
	private int minInt;
	private int maxInt;
	private double minDoub;
	private double maxDoub;
	private String value;
	private Object MIN;
	private Object MAX;
	private ArrayList<Integer> restrictionsInt;
	private ArrayList<Double> restrictionsDouble;
	private boolean objective;
	
	public Variable(String name, String type, int min, int max, ArrayList<Integer> restrictionsInt, boolean objective){
		this.name=name;
		this.type=type;
		this.minInt=min;
		this.maxInt=max;
		this.MIN = min;
		this.MAX = max;
		this.restrictionsInt = restrictionsInt;
		this.objective = objective;
	}
	public Variable(String name, String type, double min, double max, ArrayList<Double> restrictionsDouble, boolean objective){
		this.name=name;
		this.type=type;
		this.minDoub=min;
		this.maxDoub=max;
		this.MIN = min;
		this.MAX = max;
		this.restrictionsDouble = restrictionsDouble;
		this.objective = objective;
	}
	public Variable(String name, String type, String value, boolean objective){
		this.name=name;
		this.type=type;
		this.value=value;
		this.objective = objective;
	}
	

	public String toStringVariable(){
		String s="";
		if(type.equals("Integer") || type.equals("Double")){
			s = name +"		"+type+"		"+MIN+" : "+MAX;
		}
		else if(type.equals("Binary")){
			s = name +"		"+type+"		"+value;
		}
		return s;
	}
	
	public String getName() {
		return name;
	}
	
    @XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
    
	public String getType() {
		return type;
	}
	
    @XmlAttribute
	public void setType(String type) {
		this.type = type;
	}
    
	// SETTERS -----------
    @XmlAttribute
	public void setMinI(int min) {
		this.minInt = min;
	}
    @XmlAttribute
   	public void setMax(int max) {
   		this.maxInt = max;
   	}
  
    
    @XmlAttribute
    public void setMinD(double min) {
		this.minDoub = min;
	}
    @XmlAttribute
   	public void setMaxD(double max) {
   		this.maxDoub = max;
   	}
  
  
    @XmlAttribute
    public void setBinValue(String newvalue){
    	value=newvalue;
    }
    //---------------------
    
    // GETTERS ------------
	public Object getMIN(){
		return MIN;
	}
    public int getMinI() {
    	return minInt;
    }
	public int getMaxI() {
		return maxInt;
	}
	public double getMinD() {
		return minDoub;
	}
	
	public Object getMAX(){
		return MAX;
	}
	public double getMaxD() {
		return maxDoub;
	}
	public String getValue(){
		return value;
	}
    //---------------------

	public String convertAllToString(Object obj){
		return String.valueOf(obj);
	}
	public ArrayList<Integer> getRestrictionsInt() {
		return restrictionsInt;
	}
	public void setRestrictionsInt(ArrayList<Integer> restrictionsInt) {
		this.restrictionsInt = restrictionsInt;
	}
	public ArrayList<Double> getRestrictionsDouble() {
		return restrictionsDouble;
	}
	public void setRestrictionsDouble(ArrayList<Double> restrictionsDouble) {
		this.restrictionsDouble = restrictionsDouble;
	}
	public boolean isObjective() {
		return objective;
	}
	public void setObjective(boolean objective) {
		this.objective = objective;
	}
}