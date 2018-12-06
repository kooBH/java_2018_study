package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MyServer extends MyFrame implements ActionListener {

	Server t;
	//0이면 닫힌 상태, 1이면 서버가 열린 상탠
	int state;
	String input;
	ArrayList<Client> l;
	
	MyServer() throws IOException{
		super("Server");
		l = new ArrayList<Client>();
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
				labelStatus.setText("Accepting");
				
				while(alive) {
					try {
						s = ss.accept();
						Client c = new Client(s);
						c.start();
						l.add(c);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//종료
				try {
					//서버소켓 종료
					ss.close();
					//소켓 종료
					//버퍼 할당 해제
					freeBuffer();
	                state = 0;
	                button.setText("connect");
				} catch (IOException e) {e.printStackTrace();}
			}
			
		}
		class Client extends Thread{
			
			InputStream Cis = null;
		    InputStreamReader Cisr = null;
		    BufferedReader Cbr = null;
			
		    OutputStream Cos = null;
		    OutputStreamWriter Cosw = null;
		    BufferedWriter Cbw = null;
		    
			boolean alive;
			Socket s;
			String input;
			Client(Socket _s){
				s = _s;
				alive = true;
			}
			public void run() {
				try {					
					Cis = s.getInputStream();
					Cisr = new InputStreamReader(Cis);
					Cbr = new BufferedReader(Cisr);
					Cos = s.getOutputStream();
				    Cosw = new OutputStreamWriter(Cos);
			        Cbw = new BufferedWriter(Cosw);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while(alive) {
					try {
					    input = Cbr.readLine();
						printText(input);
						input = input + "\n"; 
						for(Client t : l) {
							t.Cbw.write(input);
							t.Cbw.flush();	
						}	
					} 
					 catch (IOException e) {e.printStackTrace();}		
				}
				try {
				Cis.close();
				Cisr.close();
				Cbr.close();
				Cos.close();
			    Cosw.close();
		        Cbw.close();
				}
				catch(IOException e) {e.printStackTrace();}
				l.remove(this);
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
					input = this.fieldInput.getText();
					input = "Server : " + input + "\n";
					for(Client t : l) {
						t.Cbw.write(input);
						t.Cbw.flush();	
					}					
					printText(input);							
					System.out.println("write " + input);
				    //bw.flush();
				} catch (IOException e1) {e1.printStackTrace();	}
				     
					break;
				
			}
		}
		
	
}
