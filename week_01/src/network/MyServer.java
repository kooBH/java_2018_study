package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class MyServer extends MyFrame implements ActionListener {

	Server t;
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
					s = ss.accept();
					labelStatus.setText("Accepted");
					printText(s.getInetAddress() + " is Connected");
					
					initBuffer(s);
				} catch (IOException e1) {e1.printStackTrace();}
				
				
				while(alive) {
					 try {
					    input = br.readLine();
						printText(input);
						input = input + "\n"; 
						bw.write(input);
						bw.flush();
					} 
					 catch (IOException e) {e.printStackTrace();}
				}
				
				try {
					ss.close();
					s.close();
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
					t.start();
					break;
				case 1:
				try {
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
