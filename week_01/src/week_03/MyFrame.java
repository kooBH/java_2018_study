package week_03;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class MyFrame extends JFrame  implements KeyListener,ActionListener{

	JLabel t1;  
	JPanel p1,p2,p3;
	
	String key[] = {
			"0","1","2","3","4","5","6","7","8","9",
			"NumPad-0","NumPad-1","NumPad-2","NumPad-3","NumPad-4",
			"NumPad-5","NumPad-6","NumPad-7","NumPad-8","NumPad-9",
			"NumPad .","NumPad *","NumPad /","NumPad +","NumPad -",
			"+","-","/","*","=","Backspace","Enter","Period"};
	int NumButton = 16;
	Calculator cal;
	MyFrame(String title){
		
		super(title);
		int i;
		cal = new Calculator();
		
		
		setSize(640, 380);
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		/*
		 * JFrame을
		 * ----
		 * |t1|
		 * ----
		 * |p2|
		 * ----
		 * |p3|
		 * ----
		 * 
		 * 3등분한다
		 * 
		 * 각각의 p2,p3 (p1은 쓰지 않는다, t1만 사용하기 때문에 차이가 없다)
		 * 는 2x5로 10개의 버튼을 가진다
		 * 
		 * */
		setLayout(new GridLayout(3,1));
		p2.setLayout(new GridLayout(2,5));
		p3.setLayout(new GridLayout(2,5));
		
		
		t1 = new JLabel();
		/* Font, Font trait, Font size*/
		Font f = new Font("TimesRoman", Font.PLAIN, 72);
		t1.setFont(f);
		t1.setHorizontalAlignment(SwingConstants.RIGHT);
		t1.setText("Text field");
		
		AddButton("7",p2);
		AddButton("8",p2);
		AddButton("9",p2);
		AddButton("+",p2);
		AddButton("←",p2);
		
		
		AddButton("4",p2);
		AddButton("5",p2);
		AddButton("6",p2);
		AddButton("-",p2);
		AddButton("=",p2);
		
		AddButton("1",p3);
		AddButton("2",p3);
		AddButton("3",p3);
		AddButton("*",p3);
		AddButton("√",p3);
		
		AddButton("clear",p3);		
		AddButton("0",p3);
		AddButton(".",p3);
		AddButton("/",p3);
		AddButton("±",p3);
		
		add(t1);
		add(p2);
		add(p3);
		
		t1.setText(cal.getPrint());
		
		this.addKeyListener(this);
		/* focus를 받아야 키 입력을 할 수 있다 */
		setFocusable(true);
		setVisible(true);
		
	}
	
	void AddButton(String text,JPanel p) {
		JButton b;
		b = new JButton(text);
		b.addActionListener(this);
		p.add(b);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String t = e.getActionCommand();
		System.out.println("Click : " + t);
		cal.input(t);
		t1.setText(cal.getPrint());
		/*
		버튼을 클릭하면 focus가 버튼으로 가게된다
		키 입력을 계속 받기 위해선
		프레임에 focus를 돌려줘야한다
		 */
		this.requestFocus();
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		//입력받은 keyEvent의 keyCode를 그에 대응하는 keyText로 변환
		String keyInput = e.getKeyText(e.getKeyCode());
		System.out.println("keyText : "+e.getKeyText(e.getKeyCode()));
		
		boolean inCal=false;
		/* 사용하지 않는 키 입력을 걸러준다*/
		for(int i=0;i<key.length;i++) {
			if(keyInput.equals(key[i]))inCal = true;
		}
		if(inCal) {
		/* 키 입력을 대응하는 버튼 클릭으로 전환해준다 */
		if(keyInput.equals("Backspace"))
			keyInput = "←";
		else if(keyInput.equals("Period"))
			keyInput = ".";
		else if(keyInput.equals("Enter"))
			keyInput = "=";
		// 키패드 숫자입력의 구분
		if(keyInput.length()>2)
			keyInput = keyInput.substring(7);
		cal.input(keyInput);
		t1.setText(cal.getPrint());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}

