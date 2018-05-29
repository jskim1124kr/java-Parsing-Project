package Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class AcademicInfo {
	
	 public Map<String, List> academic_datas(String data){
      String job_cnt = null;
      
      ArrayList<String> list = new ArrayList<String>();
	  ArrayList<String> idxList = new ArrayList<String>();
	  Map<String, List> resultMap = new HashMap<>();
      switch(data)
      {
      case "����": job_cnt = "1";
    	  break;
      case "���� (2,3��)" : job_cnt = "2";
    	  break;
      case "���� (4��)" : job_cnt = "3";
    	  break;
      case "��/�ڻ� ����" : job_cnt = "4";
    	  break;
      case "�з¹���" : job_cnt = "5";
    	  break;
      
      }
      String url = "http://www.saramin.co.kr/zf_user/jobs/public/list?sort=ud&quick_apply=&search_day=&keyword=&final_edu=";
      String local = url + job_cnt;
      try {
         Document doc = Jsoup.connect(local).get();
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