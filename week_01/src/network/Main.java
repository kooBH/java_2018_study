package network;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws IOException, AWTException {
		MyServer sv = new MyServer();
		MyClient cl1 = new MyClient("Client1");
	  //MyClient cl2 = new MyClient("Client2");
		//MyClient cl3 = new MyClient("Client3");
	}
}
