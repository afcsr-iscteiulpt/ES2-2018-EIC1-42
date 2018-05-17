package validationMethods;

import java.util.ArrayList;

public class IntrevalExclusion {

	public boolean isValid(String exclusions) {
		char[] chars = exclusions.toCharArray();
		if (chars[0] == '{' && chars[chars.length-1] == '}') {
			if(checkform(exclusions)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkform(String exclusions) {
		exclusions = exclusions.substring(1, exclusions.length()-1);
		String[] numbers = exclusions.split(",");
		for (int i = 0; i < numbers.length; i++) {
			try {
				Double.parseDouble(numbers[i]);				
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Double> getNumbers(String exclusions){
		if (isValid(exclusions)) {
			ArrayList<Double> numbers = new ArrayList<>();
			exclusions = exclusions.substring(1, exclusions.length()-1);
			String[] number = exclusions.split(",");
			for (int i = 0; i < number.length; i++) {
				numbers.add(Double.parseDouble(number[i]));
			}
			return numbers;
		}
		return null;
	}
	
}
