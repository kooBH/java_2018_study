package week_04;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame me = new MyFrame("Hello!");
		me.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	System.exit(0);
		    }
		});
	}

}
