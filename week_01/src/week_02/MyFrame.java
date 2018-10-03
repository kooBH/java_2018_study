package week_02;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
public class MyFrame extends JFrame  implements KeyListener{

	JLabel t1,t2;  
	JPanel p1,p2,p3;
	JButton[] b;
	
	int NumButton = 16;
	
	MyFrame(String title){
		super(title);
		int i;
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
		add(t1);
		
		b = new JButton[NumButton];
		for(i=0;i<NumButton;i++) {
			b[i] = new JButton(String.valueOf(i));
			b[i].addActionListener(new ButtonListener());
			if(i<(NumButton/2)) {p2.add(b[i]);System.out.println("p1 : "+i);}
			else {p3.add(b[i]);System.out.println("p2 : "+i);}
		}
		add(p2);
		add(p3);
		
		this.addKeyListener(this);
		setFocusable(true);
        
		/* ¸¶Áö¸·¿¡ visible ÇØÁà¾ßÇÔ ¾ÈÇÏ¸é ÀÌ½´¹ß»ý - È®·üÀû - */
		setVisible(true);
	}
	
	
	
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String t = e.getActionCommand();
			System.out.println("Click : " + t);		
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(
		  "keyCode : "+e.getKeyCode() 
		+ "\nkeyChar : " + e.getKeyChar() 
		+ "\nkeyText : "+e.getKeyText(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// ¶ª ‹š
		//System.out.println("2");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Áß°£
		//System.out.println("3");
		
	}
	
}

