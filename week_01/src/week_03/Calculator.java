package week_03;

import java.text.DecimalFormat;

public class Calculator {
	double input;
	double output;
	double mid;
	char operator;
	String print;
	DecimalFormat formatter;
	Calculator(){
		  formatter = new DecimalFormat("#,###.##########");
		output = 0;
		print = "0";
	}
	
	/* 계산기 입력을 받았을 때의 처리 */
	public void input(String v) {
		System.out.println("Calculator input : " + v);
		if(v.equals("+")) {}
		else if(v.equals("+")) {operator = '+';}
		else if(v.equals("-")) {operator = '-';}
		else if(v.equals("*")) {operator = '*';}
		else if(v.equals("/")) {operator = '/';}
		else if(v.equals("=")) {
			output = mid;
			print = formatter.format(output);
			return;
		}
		else if(v.equals("←")) {input = input/10;}
		else if(v.equals("clear")) {}
		/* Number */
		else {
			if(input == 0)input = Integer.valueOf(v);
			else
				input = input*10 + Integer.valueOf(v);
		}
	}
	
	public String getPrint() {
		
		return print;
	}
}
