package week_03;

public class Calculator {
	double input;
	double output;
	char operator;
	String print;
	
	Calculator(){
		output = 0;
		print = "0";
	}
	
	/* 0~9 까지의 숫자 입력 */
	public void input(String v) {
		System.out.println("Calculator input : " + v);
		if(v.equals("+")) {}
		else if(v.equals("+")) {}
		else if(v.equals("-")) {}
		else if(v.equals("*")) {}
		else if(v.equals("/")) {}
		else if(v.equals("DEL")) {}
		else if(v.equals("CE")) {}
		/* Number */
		else {
			
		}
	}
	
	public String getPrint() {
		return print;
	}
}
