package Project;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchAPI {
	ArrayList<String> al;
	//String number1 = null;
   public ArrayList<String> Keyboard(String ad){
	  
	   al = new ArrayList<String>();
	   try {
           String base = "http://api.saramin.co.kr/job-search?keywords="
           		+ "";
           String keyword = ad;
           String url = base+keyword;
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
           DocumentBuilder builder = factory.newDocumentBuilder();
           Document doc = builder.parse(url);
           
           doc.getDocumentElement().normalize();
        //   System.out.println("root element: "+doc.getDocumentElement());
           
           NodeList list = doc.getElementsByTagName("job");
         //  System.out.println(list.getLength());
           
           for(int temp = 0; temp< list.getLength(); temp++) {
              Node nNode = list.item(temp);
              if(nNode.getNodeType() == Node.ELEMENT_NODE){
                 
                 Element eElement = (Element) nNode;
            
                // al.add(number1);
                 al.add("┎━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┒\n");
                 al.add("│ 회사명 :   \t   │" + getTagValue("name",eElement));
                 al.add("│ 공고제목 :  \t│" + getTagValue("title",eElement));
                 al.add("│ 채용분류 :  \t│" + getTagValue("job-category",eElement));
                 al.add("│ 업무내용 :  \t│" + getTagValue("keyword",eElement));
                 al.add("│ 채용형태 :  \t│" + getTagValue("experience-level",eElement));
                 //al.add(" 학력제한 : " + getTagValue("#required-education-levelname",eElement));
                 al.add("┖━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┚\n");
               
              }
           }
           
        } catch(Exception e){
           e.printStackTrace();
           
        }
	   return al;
    }

   public  String getTagValue(String tag, Element eElement) {
      // TODO 자동 생성된 메소드 스텁
      NodeList nlist = eElement.getElementsByTagName(tag).item(0).getChildNodes();
      Node nValue = (Node) nlist.item(0);
      if(nValue == null)
         return null;
      return nValue.getNodeValue();
   }

}