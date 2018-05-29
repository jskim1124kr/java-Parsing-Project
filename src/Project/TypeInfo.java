package Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class TypeInfo {
	
	 public Map<String, List> job_datas(String data){
      String job_cnt = null;
  
      	ArrayList<String> list  = new ArrayList<String>();
		ArrayList<String> idxList  = new ArrayList<String>();
		Map<String, List> resultMap = new HashMap<>();
      switch(data)
      {
      case "서비스업": job_cnt = "1";
    	  break;
      case "제조업" : job_cnt = "2";
    	  break;
      case "IT" : job_cnt = "3";
    	  break;
      case "금융업" : job_cnt = "4";
    	  break;
      case "교육업" : job_cnt = "6";
    	  break;
      case "미디어" : job_cnt = "5";
    	  break;
      case "유통업" : job_cnt = "7";
    	  break;
      case "건설업" : job_cnt = "8";
    	  break;
      case "기타": job_cnt = "9";
      		break;
      }
      String url = "http://www.saramin.co.kr/zf_user/jobs/public/list?sort=ud&quick_apply=&search_day=&keyword=&up_cd[]=";
      String local = url + job_cnt;
      try {
         Document doc = Jsoup.connect(local).get();
         Elements start = doc.select("table.common_recruit_list");
         //회사 이름//
         Elements root0 = start.select("td.company_nm");
         Elements name = root0.select("a.str_tit");
         //채용 정보 제목//
         Elements root1 = start.select("div.job_tit");
         Elements title = root1.select("a.str_tit");
         ///채용 형식//
         Elements root3 = doc.select("td.recruit_condition");
         Elements condition = root3.select("p.career");
         ///학력 제한//
         Elements education = root3.select("p.education");
         //지역//
         Elements root4 = doc.select("td.company_info");
         Elements place = root4.select("p.work_place");
         ////	
          for(int i = 0; i < name.size(); i++){
        	  list.add("┎━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┒\n");
              list.add("│회사명 :  \t    │"+name.get(i).attr("title")+"\n");
              list.add("│채용 정보 : \t│"+title.get(i).attr("title")+"\n");
              list.add("│채용 형식 : \t│"+condition.get(i).text()+"\n");
              list.add("│교육 :     \t      │"+education.get(i).text()+"\n");
              list.add("│지역 :     \t      │"+place.get(i).text()+"\n");
              list.add("┖━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┚\n");
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