package week_04;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame  implements ActionListener{
	
	//상단 패널과 하단 패널
	JPanel p1,p2;
	//하단 패널에 달 버튼
	JButton b1,b2;
	//화면에 그리는 그래픽 객체
	Graphics2D gBuf;
	
	//쓰레드를 담아둘 리스트
	volatile ArrayList<Ball> Ovals;
	
	//안쓴것
	Image img;
	BufferedImage buf;
	
	boolean crt;
	
	int y;
	MyFrame(String title){
		super(title);
		
		//각 공의 정보를 받을 ArrayList를 생성
		Ovals = new ArrayList<Ball>();
		
		crt = false;
		//화면 크기
		this.setSize(1280, 640);
		//패널과 버튼의 배치
		this.setLayout(new BorderLayout());
		p1 = new JPanel();
		b1 = new JButton("POP");
		b2 = new JButton("Kill");
		p2 = new JPanel();
		//그리는 부분을 센터에
		add(p1,BorderLayout.CENTER);
				
		//버튼에 리스너 달아주고
		b1.addActionListener(this);
		b2.addActionListener(new killer());
		p2.setLayout(new FlowLayout());
		p2.add(b1);
		p2.add(b2); 
		//아래에 버튼 
		add(p2,BorderLayout.PAGE_END);
		
		//안쓴것
		//this.getSize()는 프레임의 크기를 받는다. 각각 가로와 세로 길이를 가진다
		buf = new BufferedImage(this.getSize().width,this.getSize().height, BufferedImage.TYPE_INT_ARGB);
		//gBuf = buf.createGraphics();
		//gBuf = (Graphics2D) p1.getGraphics();
		
		y=0;
		this.setVisible(true);	
		
		//공을 그리는 쓰레드
		MyPainter p = new MyPainter();
		p.start();
	}
	
	//공을 그리는 쓰레드
	class MyPainter extends Thread{
		
		//패널의 그래픽 성분을 받아온다
		public MyPainter(){
			gBuf = (Graphics2D) p1.getGraphics();
		}
		
		public void run(){
			//계속 돌아감
			while(true) {
				gBuf.setColor(Color.white);
				gBuf.fillRect(0, 0, p1.getWidth(), p1.getHeight());
				
				
				
				
				for(Ball t: Ovals) {
					gBuf.setColor(t.myColor);
					gBuf.fillOval(t.x, t.y, 50, 50);
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//MyFrame 서브 클래스인 Ball
	//한개의 공을 표현한다
	class Ball extends Thread{
		//공의 좌표
		int x;
		int y;
		//공이 살아있는지 체크하는 플래그
		boolean alive;
		//공의 색
		Color myColor;
		//공 쓰레드의 생성자
		Ball(){
			x=p1.getWidth()/2;
			y=p1.getHeight()/2;
			alive = true;
			myColor = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
		}
		//쓰레드를 종료시키는 메서드
		public void die() {
					alive = false;
					
				}
		//공의 움직임
		void Move() {
			
			while(alive) {
				x+=(int)(Math.random()*21)-10;
				y+=(int)(Math.random()*21)-10;
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		//Thread.start()시 돌아가게 되는 메서드
		public void run() {
			//이 쓰레드가 alive 한 동안
			while(alive) {
				Move();
				//Thread.slepp()의 경우 try,catch 로 감싸서 예외처리를 
				//반드시 받게 되어있다
				try {
					//1초(1000ms) 대기
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
			}
			System.out.println("Thread Finished");
		}
		
	}
	
	//버튼을 누르면 쓰레드를 만들어서 실행
	// 이 메서드는 MyFrame에 달려있는 메서드
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//쓰레드 생성
		Ball n1 = new Ball();
		//쓰레드 시작
		n1.start();
		//쓰레드 ArrayList에 추가함		
		Ovals.add(n1);
	}
	
	// 별도의 버튼 리스너를 만들기위해 서브클래스를 추가
	class killer implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			crt = true;
			//ArrayList Ovals의 모든 원소 t에 대하여 수행
			for(Ball t: Ovals) {				
			    t.die();
			   
			}
			crt = false;
		}
		
	}
}

