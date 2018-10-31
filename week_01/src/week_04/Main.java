package week_04;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyFrame me = new MyFrame("Hello!");
		
		// 종료버튼 누르면 종료
		// Default 종료버트는 GUI만 닫고 프로세스는 계속 돌아간다.
		me.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	//프로세스까지 다 종료
		    	System.exit(0);
		    }
		});
		
	}

}
