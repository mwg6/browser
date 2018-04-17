package browser;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class adressBar extends JPanel {

	JPanel bar = new JPanel();
	
	JTextField adress = new JTextField(100);
	
	JButton go = new JButton("GO!");
	
	String a = "https://www.google.com";
	
	JEditorPane pageDisplay = new JEditorPane("text/html", a);
	
	JScrollPane b = new JScrollPane(pageDisplay);
	/*	
	public void setDisplay(String string){
		
		try {
			URL a = new URL(string);
			System.out.println(string);
			pageDisplay.setPage(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed");
		}
	}
	*/
	public adressBar(){
		
		go.addActionListener(new ActionListener()
		{ 
			  public void actionPerformed(ActionEvent e) { 
			   System.out.println(adress.getText());
			   try {
				pageDisplay.setPage(validateAndRedirect(adress.getText()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  } 
			} );
		go.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						pageDisplay.setPage(adress.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					};
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		pageDisplay.addHyperlinkListener(new HyperlinkListener(){
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event){
				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
					String url = event.getURL().toString();
					try {
						pageDisplay.setPage(url);
						adress.setText(url);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}


		
		});
		add(adress, BorderLayout.NORTH);
		add(go, BorderLayout.NORTH);
		b.setPreferredSize(new Dimension(400,400));
		pageDisplay.setEditable(false);
		add(b, BorderLayout.SOUTH);
	}
	
	public String getWebsite(){
		return adress.getText();
	}
	public static String validateAndRedirect(String text){
		
		try {
			URL obj = new URL(text);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			
			System.out.println("Requested URL: " + text);
			
			boolean redirect = false;
			int status = conn.getResponseCode();
			//if(status = HttpURLConnection.HTTP_ACCEPTED){
				
			if(status != HttpURLConnection.HTTP_OK||status == HttpURLConnection.HTTP_MOVED_TEMP||status == HttpURLConnection.HTTP_MOVED_PERM||status == HttpURLConnection.HTTP_SEE_OTHER){
				System.out.println("Redirected!");
				redirect = true;
			}
			System.out.println("Webpage status: " + status);
			
			if(redirect){
				text = conn.getHeaderField("Location");
				
				
			}
		
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	
}
