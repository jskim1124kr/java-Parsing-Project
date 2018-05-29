package Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class LocalInfo {
	public Map<String, List> Local_datas(String data) {
		ArrayList<String> list ;
		ArrayList<String> idxList  ;
		Map<String, List> resultMap = new HashMap<>();
		String local_cnt = null;
		list = new ArrayList<String>();
		idxList = new ArrayList<>();
		int idx = 0;
      switch(data)
      {
      case "서울":local_cnt = "101000";
    	  break;
      case "경기":local_cnt = "102000";
    	  break;
      case "인천":local_cnt = "108000";
      	  break;
      case "강원":local_cnt = "109000";
    	  break;
      case "충북":local_cnt = "114000";
    	  break;
      case "충남":local_cnt = "115000";
    	  break;
      case "전북":local_cnt = "113000";
    	  break;
      case "전남":local_cnt = "112000";
    	  break;
      case "경북":local_cnt = "111000";
    	  break;
      case "경남":local_cnt = "110000";
    	  break;
      case "제주":local_cnt = "116000";
    	  break;
      case "세종":local_cnt = "118000";
    	  break;
      case "대구":local_cnt = "104000";
    	  break;
      case "울산":local_cnt = "107000";
    	  break;
      case "부산":local_cnt = "106000";
    	  break;
      case "광주":local_cnt = "103000";
    	  break;
    	  
      }
      
      String local = null;
      if(data == "전국") {
    	  local = "http://www.saramin.co.kr/zf_user/jobs/public/list";
      } else {
    	  String url = "http://www.saramin.co.kr/zf_user/jobs/public/list?sort=ud&quick_apply=&search_day=&keyword=&loc_mcd=";
          local = url + local_cnt;
      }
     
      try {
         Document doc = Jsoup.connect(local).get();
         Elements start = doc.select("tbody");
         //회사 이름//
         Elements root0 = start.select("td.company_nm");
         Elements name = root0.select("a.str_tit");
         System.out.println(name.size());
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