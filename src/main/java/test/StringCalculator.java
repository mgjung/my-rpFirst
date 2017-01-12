package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public int add(String numbers) {
		
		if(isNull(numbers)){
			return 0;
		}
	
		return addLoop(reserved(numbers));
		
	}
	
	public boolean isNull(String numbers){
		return numbers == null || numbers.equals("");
	}
	
	public int addReal(int i, int j){
		if(j<0){
			throw new RuntimeException("음수값 전");
		}
		return i+j;
	}
	
	public int addLoop(String[] nums){
		int result = 0;
		for(int i=0;i<nums.length;i++){
			
			result = addReal(result, Integer.parseInt(nums[i]));
		}
		return result;
	}
	
	public String[] reserved(String numbers){
		
		String[] tokens = numbers.split(",|:");;
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(numbers);
		if(m.find()){
			String customDelimeter = m.group(1);
			tokens= m.group(2).split(customDelimeter);
		}
		
		
		return tokens;
		
	}

}
