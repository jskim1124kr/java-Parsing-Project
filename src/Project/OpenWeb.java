package Project;
import java.io.IOException; 
import java.net.URI; 
import java.net.URISyntaxException;
import java.awt.Desktop;

public class OpenWeb {
	public static void WebPage(String url) {
		
	
		try { 
			Desktop.getDesktop().browse(new URI(url)); 
		} catch (IOException e) {
			e.printStackTrace(); 
		} catch (URISyntaxException e) { e.printStackTrace(); } 
		
	} 
}



