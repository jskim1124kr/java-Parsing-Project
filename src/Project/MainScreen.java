package Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class MainScreen {
	
	public static Map<String, List> mains(int cnt){
     
	  ArrayList<String> list ;
	  ArrayList<String> idxList  ;
	  Map<String, List> resultMap = new HashMap<>();
      list = new ArrayList<String>();
      idxList = new ArrayList<String>();
	  int idx = 0;
      String url = "http://www.saramin.co.kr/zf_user/jobs/public/list?page=";
      
      String url2 = "&sort=ud&listType=public&public_list_flag=y#searchTitle";
      String[] page = {"1","2","3","4","5","6"};
      String URL = null;
      switch (cnt){
         case 1:
            URL = url+page[0]+url2;
            break;
         case 2:
            URL = url+page[1]+url2;
            break;
         case 3:
            URL = url+page[2]+url2;
            break;
         case 4:
            URL = url+page[3]+url2;
            break;
         case 5:
            URL = url+page[4]+url2;
            break;
         case 6:
            URL = url+page[5]+url2;
            break;
      }
      try {
         Document doc = Jsoup.connect(url).get();
         Elements start = doc.select("table.common_recruit_list");
         //ȸ�� �̸�//
         Elements root0 = start.select("td.company_nm");
         Elements name = root0.select("a.str_tit");
         //ä�� ���� ����//
         Elements root1 = start.select("div.job_tit");
         Elements title = root1.select("a.str_tit");
         ///ä�� ����//
         Elements root3 = doc.select("td.recruit_condition");
         Elements condition = root3.select("p.career");
         ///�з� ����//
         Elements education = root3.select("p.education");
         //����//
         Elements root4 = doc.select("td.company_info");
         Elements place = root4.select("p.work_place");
         ////	
          for(int i = 0; i < place.size(); i++){
        	   list.add("�Ǧ�����������������������������������������������������������\n");
               list.add("��ȸ��� :  \t    ��"+name.get(i).attr("title")+"\n");
               list.add("��ä�� ���� : \t��"+title.get(i).attr("title")+"\n");
               list.add("��ä�� ���� : \t��"+condition.get(i).text()+"\n");
               list.add("������ :     \t      ��"+education.get(i).text()+"\n");
               list.add("������ :     \t      ��"+place.get(i).text()+"\n");
               list.add("�Ŧ�����������������������������������������������������������\n");
               String href = name.get(i).attr("href");
 	  		  idxList.add(href.substring(href.indexOf("=")+1, href.length()));
            }

          resultMap.put("data", list);
          resultMap.put("idx", idxList);
      } catch (Exception e){
         e.printStackTrace();
      }
      return resultMap;
	}

}