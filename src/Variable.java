
public class Variable {

	private String name;
	private String type;
	private int min;
	private int max;
	
	public Variable(String name, String type, int min, int max){
		this.name=name;
		this.type=type;
		this.min=min;
		this.max=max;	
	}
	
	public Variable(String name, String type){
		this.name=name;
		this.type=type;	
	}

	
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

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	
}
