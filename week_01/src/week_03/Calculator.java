package week_03;

import java.text.DecimalFormat;

public class Calculator {
	double input;
	double output;
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
		else if(v.equals("+")) {}
		else if(v.equals("-")) {}
		else if(v.equals("*")) {}
		else if(v.equals("/")) {}
		else if(v.equals("=")) {}
		else if(v.equals("←")) {}
		else if(v.equals("clear")) {}
		/* Number */
		else {
			if(output == 0)output = Integer.valueOf(v);
			else
				output = output*10 + Integer.valueOf(v);
		}
	}
	
	public String getPrint() {
		print = formatter.format(output);
		return print;
	}
}
