package week_03;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class MyFrame extends JFrame  implements KeyListener,ActionListener{

	JLabel t1,t2;  
	JPanel p1,p2,p3;
	
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
		
		setLayout(new GridLayout(3,1));
		p2.setLayout(new GridLayout(2,4));
		p3.setLayout(new GridLayout(2,4));
		
		
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
		
		AddButton("4",p2);
		AddButton("5",p2);
		AddButton("6",p2);
		AddButton("-",p2);
		
		AddButton("1",p3);
		AddButton("2",p3);
		AddButton("3",p3);
		AddButton("*",p3);
		
		AddButton("CE",p3);		
		AddButton("0",p3);
		AddButton("DEL",p3);
		AddButton("/",p3);
		
		add(t1);
		add(p2);
		add(p3);
		
		this.addKeyListener(this);
		setFocusable(true);
		/* �������� visible ������� ���ϸ� �̽��߻� - Ȯ���� - */
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
		/*
		한글 입력
		 */
		this.requestFocus();
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(
		  "keyCode : "+e.getKeyCode() 
		+ "\nkeyChar : " + e.getKeyChar() 
		+ "\nkeyText : "+e.getKeyText(e.getKeyCode()));
		cal.input(String.valueOf(e.getKeyChar()));

	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}

