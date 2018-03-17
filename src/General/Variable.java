package General;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Variable {

	private String name;
	private String type;
	private int min;
	private int max;
    
	public String toStringVariable(){
		String s="";
		if(min == max){
			s = name +"		"+type;
		}
		s = name +"		"+type+"		"+min+" : "+max;
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
    
	public int getMin() {
		return min;
	}
	
    @XmlAttribute
	public void setMin(int min) {
		this.min = min;
	}
    
	public int getMax() {
		return max;
	}
	
    @XmlAttribute
	public void setMax(int max) {
		this.max = max;
	}	
}
