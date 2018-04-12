package browser;

import javax.swing.JFrame;

public class browserMain {
	   private static void createAndShowUI(){
		   
		   JFrame display = new JFrame("Max's Browser");
		   
		   browserPage main = new browserPage();
		   adressBar bar = new adressBar();
		   display.add(main);
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
