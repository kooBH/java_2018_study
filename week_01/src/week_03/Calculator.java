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
		/*
		 * 일반적으로 실수형을 표현할때 길이가 어느정도 길어지면 a.bxc * 10E
		 * 꼴로 표현된다. 계산기는 그렇게 표현하지 않을 것이기 때문에
		 * 문자열의 표현방식인 포맷을 변경해서 출력한다
		 * */
		formatter = new DecimalFormat("#,###.##########");
		output = 0;
		print = "0";
	}
	/* 계산기 입력을 받았을 때의 처리 
	 * 
	 * 1. 처음 숫자입력
	 * 2. =이 아닌 연산자입력
	 * 3. =의 입력
	 * 4. 연산자 이후 숫자입력
	 * etc..
	 * */
	public void input(String v) {
		System.out.println("Calculator input : " + v);
		if(v.equals("+")) {}
		else if(v.equals("+")) {operator = '+';}
		else if(v.equals("-")) {operator = '-';}
		else if(v.equals("*")) {operator = '*';}
		else if(v.equals("/")) {operator = '/';}
		else if(v.equals("=")) {
			output = mid;
			// 포맷적용
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
		print = formatter.format(input);
	}
	public String getPrint() {
		return print;
	}
}
