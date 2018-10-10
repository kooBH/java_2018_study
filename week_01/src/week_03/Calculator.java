package week_03;
import java.text.DecimalFormat;
public class Calculator {
	// 현재 입력중인 숫자
	double input;
	double output;
	// 현재 수행 예정인 연산자
	// 값이 0이면 연산자 입력을 아직 받지 않는 상태
	char op;
	// 계산을 하는 연산자들, 입력 확인용 
	char[] listOp = {'+','-','*','/'};
	/* 가장 최근 입력의 열거형 - C의 열거형과 유사
	 enum 은 개발자의 편의를 돕기위해 각각의 정수에 매핑되는 이름을 부여한것
	 0부터 1씩 증가하며 OP느 0, NUM은 1의 값을 가지지만
	 State.OP, State.NUM이런식으로 직관적인 사용이 가능하다
	*/
	enum State {OP, NUM, DOT}
	State state;
	//UI에 표시하기 위해 보낼 출력 스트링
	String print;
	//출력 포맷
	DecimalFormat formatter;
	
	Calculator(){
		/*
		 * 일반적으로 실수형을 표현할때 길이가 어느정도 길어지면 a.bxc * 10E
		 * 꼴로 표현된다. 계산기는 그렇게 표현하지 않을 것이기 때문에
		 * 문자열의 표현방식인 포맷을 변경해서 출력한다
		 * 숫자 3개 당 , 표시를 하고 소숫점 
		 * */
		state = State.NUM;
		formatter = new DecimalFormat("#,###.##########");
		output = 0;
		input = 0;
		print = "0";
		op = 0;
	}
	/* 계산기 입력을 받았을 때의 처리 
	 * 입력의 종류는 3가지
	 * 1. 계산하는 연산자(*,/,+,/)
	 * 2. 기타 연산자(Backspace, clear, = )
	 * 3. 숫자입력
	 * */
	public void input(String v) {
		int i;
		//현재 Op
		char curOp=0;
		boolean isCalOp=false;
		System.out.println("v : " + v + "\ninput : " + input +
				"\noutput : " + output + "\nop : " + op);
		
		// 1. 계산하는 입력
		// 경우의 확인
		for(i=0;i<listOp.length;i++)
			if(v.equals(String.valueOf(listOp[i])) ){
				isCalOp=true;
				curOp = listOp[i];
				break;
			}
		if(isCalOp) {
			/* 연산자를 지속해서 입력한 경우를 예외로 처리 */
			switch(state) {
			case OP:op = curOp;
				break;
			/* 일반적인 경우 */
			default:
				//이전에 입력받은 연산자가 없다면
				if(op == 0) {
					op = curOp;
					output=input;
					input = 0;
				}
				else {
					
					
				}
				break;
			}
		}
		else if(v.equals("=")) {
			// 포맷적용
			if(op != 0)
				performOp(op);
			else
				output = input;
			state = State.OP;
			input = 0;
			print = formatter.format(output);
			return;
		}
		// 2. 기타 연산자
		// Backspace
		else if(v.equals("←")) {
			
			}
		else if(v.equals("clear")){
			input=0;
			output=0;			
		}
		// 소수 입력의 시작
		else if(v.equals(".")) {}
		/* 3. 숫자입력
		 * 소수입력을 고려해야한다
		 */
		else {
			state = State.NUM;
		//최대 자릿수는 E12
		  if(input > 1000000000000f )return;
		  
		  if(input == 0)input = Integer.valueOf(v);
		  else	input = input*10 + Integer.valueOf(v);
		}
		print = formatter.format(input);
	}
	public String getPrint() {
		return print;
	}
	
	// 계산하는 연산자를 수행하는 메서드
	void performOp(char op) {
		switch(op) {
		case '+': output = output + input;
			break;
		case '-': output = output - input;
			break;
		case '*': output = output * input;
			break;
		case '/': output = output / input;
			break;
		}
		
	}
}
