package Project;

import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class DetailInfo {
    
	public static ArrayList<String> detailList(String idx){
	  ArrayList<String> list = new ArrayList<String>();
      

      String url1 = "http://www.saramin.co.kr/zf_user/jobs/public/view?rec_idx=";
      String url2 ="&t_ref=public-recruit&t_ref_content=list";
     
     
      String URL = url1 + idx + url2;
      try {
         Document doc = Jsoup.connect(URL).get();
         Elements start = doc.select("table.tbl_prview");
         //회사 이름//
         Elements select = start.select("tr");
         
         for(int i = 0; i<select.size(); i++)
         {
        	 list.add(select.get(i).text());
        	 
         }
              
      } catch (Exception e){
         e.printStackTrace();
      }
      return list;
   }
}
