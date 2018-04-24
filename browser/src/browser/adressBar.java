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
	
	JButton back = new JButton("<-");
	
	JButton forward = new JButton("->");
	
	String homepage = "https://www.google.com";
	
	
	JEditorPane pageDisplay = new JEditorPane("text/html", null);
	
		
	JScrollPane b = new JScrollPane(pageDisplay);
	
	int historyPlace = 0;
	
	String[] history = new String[20];

	public adressBar(){
		
		go.addActionListener(new ActionListener()
		{ 
			  public void actionPerformed(ActionEvent e) { 
			   System.out.println(adress.getText());
			   try {
				
				pageDisplay.setPage(validateAndRedirect(adress.getText()));
				//adds the current page to the history
				history[historyPlace] = adress.getText();
				//tries to handle reaching the end of the history memory
				historyPlace++;
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  } 
			} );
		back.addActionListener(new ActionListener()
		{ 
			  public void actionPerformed(ActionEvent k) { 
			   System.out.println(adress.getText());
			   try {
				   if(historyPlace==0){
					   
				   }
				   else{
				   history[historyPlace] = adress.getText();
				   historyPlace--;
				   pageDisplay.setPage(history[historyPlace]);
				   adress.setText(history[historyPlace]);
				   }
				
			} catch (IOException k1) {
				// TODO Auto-generated catch block
				System.out.println(historyPlace);
			}
			  } 
			} );
		forward.addActionListener(new ActionListener()
		{ 
			  public void actionPerformed(ActionEvent j) { 
			  try{
				  historyPlace++;
				  pageDisplay.setPage(history[historyPlace]);
				  adress.setText(history[historyPlace]);
				  
			} catch (IOException j1) {
				// TODO Auto-generated catch block
				System.out.println("fail");
				for(int x = 0; x<=historyPlace;x++){
					
				
				System.out.println(history[historyPlace]);
				}
			}
			  } 
			} );
		adress.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						pageDisplay.setPage(adress.getText());
						System.out.println("Success");
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
		add(back, BorderLayout.NORTH);
		add(forward, BorderLayout.NORTH);
		add(adress, BorderLayout.NORTH);
		add(go, BorderLayout.NORTH);
		b.setPreferredSize(new Dimension(400,400));
		pageDisplay.setEditable(false);
		add(b, BorderLayout.SOUTH);
	}
	
	public String getWebsite(){
		return adress.getText();
	}
	//the below will redirect websites. This will be useful if http is typed but https is required
	public static String validateAndRedirect(String text){
		
		try {
			System.out.println(text);
			//checks for confirmation to url standards and forces compliance
			if(text.substring(text.length()-4)!=".com"){
				System.out.println(text.substring(text.length()-4));
				text = text+".com";
				System.out.println("fail .com");
				
			}
			else{}
			if(text.substring(0,11)!="https://www."||text.length()<11)
			{
				text = "https://www."+text;
				System.out.println("fail http");
			}
			else{}
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
