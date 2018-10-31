package network;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;


public class MyClient extends MyFrame implements ActionListener{
	
	int state;
	Client t;
	String input;
	Socket s;
	String serverIP;
	MyClient() throws UnknownHostException{
		super("Client");
		button.addActionListener(this);
		button.setText("Connect");
		state = 0;
		serverIP = myIP;
		t = new Client();
	}
	
	// 클라이언트 쓰레드
		class Client extends Thread{
			
			int port = 1234;
			
			boolean alive;
			
			Client(){
				alive = true;
			}
			
			public void run(){
				labelStatus.setText("Connecting");
				try {
					s = new Socket(serverIP,port);
					
					initBuffer(s);
				} catch (IOException e1) { e1.printStackTrace();}
				labelStatus.setText("Connected");
				
				while(alive) {
					try {
						input = br.readLine();
						printText(input);
					} catch (IOException e) {e.printStackTrace();}
					
				}
				
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
				t.start();
				
				state = 1;
				button.setText("Send");
				break;
			case 1: 
				input = this.fieldInput.getText();
				 try {
			        input = "Client : "+ input + "\n";
			        bw.write(input);
			        System.out.println("write " + input);
			        bw.flush();
				 } catch (IOException e) {e.printStackTrace();}
				break;
			
			}
		}
	

	
	
}
