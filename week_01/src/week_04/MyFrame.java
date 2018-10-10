package week_04;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame  implements ActionListener{
	
	//상단 패널과 하단 패널
	JPanel p1,p2;
	//하단 패널에 달 버튼
	JButton b1;
	//안쓰는것
	Image img;
	BufferedImage buf;
	//화면에 그리는 그래피 객체
	Graphics2D gBuf;
	MyFrame(String title){
		super(title);
		this.setSize(1280, 640);
		//패널과 버튼의 배치
		this.setLayout(new BorderLayout());
		p1 = new JPanel();
		b1 = new JButton("POP");
		p2 = new JPanel();
		//그리는 부분을 센터에
		add(p1,BorderLayout.CENTER);
		//버튼에 리스너 달아주고
		b1.addActionListener(this);
		p2.add(b1);
		//아래에 버튼 
		add(p2,BorderLayout.PAGE_END);
		
		//안쓴것
		buf = new BufferedImage(this.getSize().width,this.getSize().height, BufferedImage.TYPE_INT_ARGB);
		//gBuf = buf.createGraphics();
		//gBuf = (Graphics2D) p1.getGraphics();
		
		
		this.setVisible(true);	
	}
	
	//쓰레드 서브 클래스
	class MyThread extends Thread{
		int x;
		MyThread(){
			x=0;
		}
		//Thread.start()시 돌아가게 되는 메서드
		public void run() {
			popInit();
			while(!Thread.currentThread().isInterrupted()) {
				popMove();
				
				//Thread.slepp()의 경우 try,catch 로 감싸서 예외처리를 
				//반드시 받게 되어있다
				try {
					//1초(1000ms) 대기
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//처음에 그릴때
		public void popInit() {
			gBuf = (Graphics2D) p1.getGraphics();
			gBuf.setColor(Color.black);
			gBuf.fillRect(x, 0, 100, 100); 
		}
		//이동하면서 그릴때
		void popMove() {
			gBuf = (Graphics2D) p1.getGraphics();
			
			//XOR모드시 그림이 겹치는 경우 XOR에 지정된 색으로 겹치는 부분을 메꾸게 된다
			//gBuf.setXORMode(p1.getBackground());
			
			//원래 있던 그림을 덮어씌운다
			gBuf.setColor(p1.getBackground());
			gBuf.fillRect(x, 0, 100, 100); 
			
			gBuf.setColor(Color.black);
			gBuf.fillRect(x+=50, 0, 100, 100);
		}
	}
	
	//버튼을 누르면 쓰레드를 만들어서 실행
	@Override
	public void actionPerformed(ActionEvent arg0) {
		MyThread n1 = new MyThread();
		n1.start();
	}
}

