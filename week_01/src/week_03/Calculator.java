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
	
	/* 계산기 입력을 받았을 때의 처리 */
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
