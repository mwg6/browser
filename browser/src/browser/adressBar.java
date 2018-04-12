package browser;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class adressBar extends JPanel {

	JPanel bar = new JPanel();
	
	JTextField adress = new JTextField(100);
	
	JButton go = new JButton("GO!");
	
	
	
	
	
	public adressBar(){
		go.addActionListener(new ActionListener()
		{ 
			  public void actionPerformed(ActionEvent e) { 
			   System.out.println(adress.getText());
			  } 
			} );
		add(adress);
		add(go);
	}
	
	public String getWebsite(){
		return adress.getText();
	}
	
}
