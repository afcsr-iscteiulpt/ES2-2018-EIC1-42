package General;
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
	private String minBit;
	private String maxBit;
	private Object MIN;
	private Object MAX;
	
	public Variable(String name, String type, int min, int max){
		this.name=name;
		this.type=type;
		this.minInt=min;
		this.maxInt=max;
		this.MIN = min;
		this.MAX = max;
	}
	public Variable(String name, String type, double min, double max){
		this.name=name;
		this.type=type;
		this.minDoub=min;
		this.maxDoub=max;
		this.MIN = min;
		this.MAX = max;
	}
	public Variable(String name, String type, String min, String max){
		this.name=name;
		this.type=type;
		this.minBit=min;
		this.maxBit=max;
		this.MIN = min;
		this.MAX = max;
	}
	
	public String toStringVariable(){
		String s="";
		s = name +"		"+type+"		"+MIN+" : "+MAX;
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
    public void setMinB( String min) {
		this.minBit = min;
	}
	public void setMaxB(String max) {
   		this.maxBit = max;
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
	public String getMinB(){
		return minBit;
	}
	public String getMaxB(){
		return maxBit;
	}
    //---------------------

	public String convertAllToString(Object obj){
		return String.valueOf(obj);
	}
   	
}