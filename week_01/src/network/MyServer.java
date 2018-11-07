package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class MyServer extends MyFrame implements ActionListener {

	Server t;
	//0이면 닫힌 상태, 1이면 서버가 열린 상탠
	int state;
	String input;
	
	
	MyServer() throws IOException{
		super("Server");
		state = 0;
		button.addActionListener(this);
		button.setText("RunServer");
		t = new Server();
	}
	

	//서버 쓰레드
		class Server extends Thread{
			private int port = 1234;
			ServerSocket ss;
			Socket s;
			boolean alive;
			
			
			Server() throws IOException{
				ss= new ServerSocket(port);
				alive = true;
				
			}
			
			public void run(){
				try {
				
					labelStatus.setText("Accepting");
					//서버 소켓을 통해 소케을 활성화 한다
					// Note) 서버 소켓 != 소켓
					// 서버 소켓은 더 큰 개념
					s = ss.accept();
					labelStatus.setText("Accepted");
					printText(s.getInetAddress() + " is Connected");
					//연결된 소켓 s 에대한 버퍼 초기화
					initBuffer(s);
				} catch (IOException e1) {e1.printStackTrace();}
				
				
				while(alive) {
					 try {
						//입력 스트림을 통해 문자열을 받아들인다.
					    input = br.readLine();
						printText(input);
						input = input + "\n"; 
						//문자열을 보낸 클라이언트에게 다시 보내서 
						//클라이언트에서도 문자열을 출력하게함
						bw.write(input);
						//출력 스트림 초기화
						bw.flush();
					} 
					 catch (IOException e) {e.printStackTrace();}
				}
				//종료
				try {
					//서버소켓 종료
					ss.close();
					//소켓 종료
					s.close();
					//버퍼 할당 해제
					freeBuffer();
	                state = 0;
	                button.setText("connect");
				} catch (IOException e) {e.printStackTrace();}
			}
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Click : " + String.valueOf(state));
			switch(state){
				case 0:					
					state = 1;
					button.setText("Send");
					//서버 시작
					t.start();
					break;
				case 1:
				try {
					//서버-> 클라이언트
					input = this.fieldInput.getText();
					input = "Server : " + input + "\n";
					bw.write(input);
					printText(input);
					System.out.println("write " + input);
				    bw.flush();
				} catch (IOException e1) {e1.printStackTrace();	}
				     
					break;
				
			}
		}
		
	
}
