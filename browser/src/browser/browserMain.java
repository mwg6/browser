package browser;

import javax.swing.JFrame;

public class browserMain {
	 static JFrame display = new JFrame("Max's Browser");
	   
	
	   static adressBar bar = new adressBar();
	
	private static void createAndShowUI(){
		   
		   display.add(bar);
		   display.pack();
		   display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   display.setVisible(true);
		   
	   }
	
	public static void main(String[] args) {
		      java.awt.EventQueue.invokeLater(new Runnable() {
		         public void run() {
		            createAndShowUI();
		            
		         }
		      });
		   
}
}
