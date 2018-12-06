package network;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;


public class MyClient extends MyFrame implements ActionListener{
	
	int state;
	Client t;
	String input;
	Socket s; // 0이면 미접속, 1이면 접속중
	String serverIP;
	Robot robot;
	MyClient(String _title) throws UnknownHostException, AWTException{
		super(_title);
		button.addActionListener(this);
		button.setText("Connect");
		state = 0;
		serverIP = myIP; //기본값은 자기자신의 IP
		t = new Client();
		robot = new Robot();
	}
	
	// 클라이언트 쓰레드
		class Client extends Thread{
			//임의로 설정한 포트  넘버, 서버와 같아야한다
			int port = 1234; 	
			boolean alive;
			Client(){
				alive = true;
			}
			public void run(){
				labelStatus.setText("Connecting");
				try {
					//Myclient의 Socket s
					s = new Socket(serverIP,port);
					//버퍼 초기화
					initBuffer(s);
				} catch (IOException e1) { e1.printStackTrace();}
				labelStatus.setText("Connected");
				while(alive) {
					try {
						//소켓으로 들어오는 입력을 받는다
						input = br.readLine();
						robot.keyPress(KeyEvent.VK_ALT);
						robot.keyPress(KeyEvent.VK_F4);
						robot.keyRelease(KeyEvent.VK_ALT);
						robot.keyRelease(KeyEvent.VK_F4);
								printText(input);
					} catch (IOException e) {e.printStackTrace();}
					
				}
				//종료
				try {
					s.close();
					freeBuffer();
	                state = 0;
	                button.setText("connect");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				labelStatus.setText("Closed");
			}
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			switch(state) {
			case 0:
				input = this.fieldInput.getText();
				serverIP = input;
				//클라이언트 쓰레드 시작
				t.start();
				state = 1;
				button.setText("Send");
				break;
			case 1: 
				//텍스트 필드에 있는 문자열을 전송
				input = this.fieldInput.getText();
				 try {
			        input = myIP + " : "+ input + "\n";
			        //소켓에 연결된 출력 스트림을 통해 문자열 전송
			        bw.write(input);
			        System.out.println("write " + input);
			        //출력 스트림 비워줌
			        bw.flush();
				 } catch (IOException e) {e.printStackTrace();}
				break;
			
			}
		}
	

	
	
}
