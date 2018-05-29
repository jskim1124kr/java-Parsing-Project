package Project;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class MajorInfo {
    
   public static Map<String, List> Major_datas(String data){
      String major_value = null;

        ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> idxList = new ArrayList<String>();
		Map<String, List> resultMap = new HashMap<>();
      switch(data)
      {
      case "���,�ڵ���":major_value = "1";
         break;
      case "����,����":major_value = "2";
         break;
      case "����,���":major_value = "3";
         break;
      case "���,�ݼ�":major_value = "4";
         break;
      case "����,���":major_value = "6";
         break;
      case "�������,������":major_value = "9";
         break;
      case "��ǻ��,������Ű���":major_value = "10";
          break;
         
      }
      String url = "http://www.saramin.co.kr/zf_user/jobs/public/list?sort=ud&quick_apply=&search_day=&keyword&mj_cd[]=";
     
      String major = url + major_value;
      try {
         Document doc = Jsoup.connect(major).get();
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