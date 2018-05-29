package Project;

import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class DetailUrl {
    
	public static String returnURL(String idx){
		String webURL;

      String url1 = "http://www.saramin.co.kr/zf_user/jobs/public/view?rec_idx=";
      String url2 ="&t_ref=public-recruit&t_ref_content=list";
     
     
      String URL = url1 + idx + url2;
   
         webURL = URL;
              
   
      return webURL;
   }
}
