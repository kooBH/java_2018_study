package network;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;

public class MyFrame extends JFrame{

	JPanel chatText;
	
	JLabel labelIP;
	JLabel labelStatus;
	JLabel[] labelText;
	JButton button;
	JTextField fieldInput;
	
	GridBagConstraints c;
	
	String myIP;
	
	//입출력을 위한 버퍼
	OutputStream os = null;
    OutputStreamWriter osw = null;
    BufferedWriter bw = null;

 	InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
	
	MyFrame(String title) throws UnknownHostException{
		this.setTitle(title);
		this.setSize(640, 480);
		
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		//Info
		labelIP = new JLabel(myIP);
		labelIP.setFont(new Font("TimesRoman", Font.PLAIN, 36));			
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(labelIP,c);
		
		//Status
		labelStatus = new JLabel("Status");
		labelStatus.setFont(new Font("TimesRoman", Font.PLAIN, 72));	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		this.add(labelStatus,c);
		
		//Text Output
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx=0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;

		//출력 패널 설정
		chatText = new JPanel();
		chatText.setLayout(new GridLayout(10,1));
		labelText = new JLabel[10];
		for(int i=0;i<10;i++) {
			labelText[i] = new JLabel(String.valueOf(i));
			labelText[i].setFont(new Font("TimesRoman", Font.PLAIN, 24));
			chatText.add(labelText[i]);
		}			
		c.fill = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 4;
		this.add(chatText,c);

		
		//입력 패널 설정
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		fieldInput = new JTextField();
		this.add(fieldInput,c);
		
		//버튼
		button = new JButton("Connect");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		this.add(button,c);
		//내 IP
		myIP = InetAddress.getLocalHost().getHostAddress();
		labelIP.setText(myIP);
		
		
		this.setVisible(true);
		
		// 종료버튼 누르면 종료
		// Default 종료버트는 GUI만 닫고 프로세스는 계속 돌아간다.
		// 일단은 다 종료되게 해놨음
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	//프로세스까지 다 종료
		    	System.exit(0);
		    }
		});
	}
	
	// LabelText는 10개의 JLabel로 되어있으며
	// 새로운 입력이 들어올때마다 한칸씩 위로 올린다.
	protected void printText(String input) {
		for(int i=0;i<9;i++) {
			labelText[i].setText(labelText[i+1].getText());
		}
		labelText[9].setText(input);
	}
	
	//입출력 버퍼 생성과 소멸
	void initBuffer(Socket s) throws IOException {
		is = s.getInputStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		os = s.getOutputStream();
	    osw = new OutputStreamWriter(os);
        bw = new BufferedWriter(osw);
	}
	void freeBuffer() throws IOException {
		bw.close();
        osw.close();
        os.close();
        br.close();
        isr.close();
        is.close();
	}
	
}
