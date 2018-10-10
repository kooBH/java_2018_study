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
	ArrayList<MyThread> Ovals;
	
	//안쓴것
	Image img;
	BufferedImage buf;
	
	int y;
	MyFrame(String title){
		super(title);
		//리스트 생성
		Ovals = new ArrayList<MyThread>();
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
		
		//안쓴것 this.getSize()는 프레임의 크기를 받는다. 각각 가로와 세로 길이를 가진다
		buf = new BufferedImage(this.getSize().width,this.getSize().height, BufferedImage.TYPE_INT_ARGB);
		//gBuf = buf.createGraphics();
		//gBuf = (Graphics2D) p1.getGraphics();
		
		y=0;
		this.setVisible(true);	
		
	}
	
	
	//쓰레드 서브 클래스
	class MyThread extends Thread{
		int x;
		int y;
		boolean flag;
		MyThread(){
			x=0;
			y=0;
			flag= true;
		}
		//Thread.start()시 돌아가게 되는 메서드
		public void run() {
			popInit();
			while(flag) {
				popMove();
				
				//Thread.slepp()의 경우 try,catch 로 감싸서 예외처리를 
				//반드시 받게 되어있다
				try {
					//1초(1000ms) 대기
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//인터럽트를 받으면 그만한다
					break;
				}
			}
			System.out.println("Thread Finished");
		}
		//종료
		public void die() {flag=false;}
		//처음에 그릴때
		public void popInit() {
			//그래픽을 받아옴
			gBuf = (Graphics2D) p1.getGraphics();
			gBuf.setColor(Color.black);
			//대부분의 
			gBuf.fillOval(x, y, 100, 100); 
		}
		//이동하면서 그릴때
		void popMove() {
			
			gBuf.setColor(Color.black);
			gBuf.fillOval(x, y,100, 100);
		}
	}
	
	//버튼을 누르면 쓰레드를 만들어서 실행
	@Override
	public void actionPerformed(ActionEvent arg0) {
		MyThread n1 = new MyThread();
		n1.start();
		//쓰레드 ArrayList에 추가함
		Ovals.add(n1);
		
	}
	
	class killer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//ArrayList Ovals의 모든 원소 t에 대하여 수행
			for(MyThread t: Ovals) {				
			    t.die();
			    //모든 쓰레드에 인터럽트를 걸어준다
				  //  t.interrupt();
			}
		}
		
	}
}

