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
      case "����":local_cnt = "101000";
    	  break;
      case "���":local_cnt = "102000";
    	  break;
      case "��õ":local_cnt = "108000";
      	  break;
      case "����":local_cnt = "109000";
    	  break;
      case "���":local_cnt = "114000";
    	  break;
      case "�泲":local_cnt = "115000";
    	  break;
      case "����":local_cnt = "113000";
    	  break;
      case "����":local_cnt = "112000";
    	  break;
      case "���":local_cnt = "111000";
    	  break;
      case "�泲":local_cnt = "110000";
    	  break;
      case "����":local_cnt = "116000";
    	  break;
      case "����":local_cnt = "118000";
    	  break;
      case "�뱸":local_cnt = "104000";
    	  break;
      case "���":local_cnt = "107000";
    	  break;
      case "�λ�":local_cnt = "106000";
    	  break;
      case "����":local_cnt = "103000";
    	  break;
    	  
      }
      
      String local = null;
      if(data == "����") {
    	  local = "http://www.saramin.co.kr/zf_user/jobs/public/list";
      } else {
    	  String url = "http://www.saramin.co.kr/zf_user/jobs/public/list?sort=ud&quick_apply=&search_day=&keyword=&loc_mcd=";
          local = url + local_cnt;
      }
     
      try {
         Document doc = Jsoup.connect(local).get();
         Elements start = doc.select("tbody");
         //ȸ�� �̸�//
         Elements root0 = start.select("td.company_nm");
         Elements name = root0.select("a.str_tit");
         System.out.println(name.size());
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
          for(int i = 0; i < name.size(); i++){
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