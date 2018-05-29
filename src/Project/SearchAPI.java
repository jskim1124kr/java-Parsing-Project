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
                 al.add("�Ǧ�����������������������������������������������������������\n");
                 al.add("�� ȸ��� :   \t   ��" + getTagValue("name",eElement));
                 al.add("�� �������� :  \t��" + getTagValue("title",eElement));
                 al.add("�� ä��з� :  \t��" + getTagValue("job-category",eElement));
                 al.add("�� �������� :  \t��" + getTagValue("keyword",eElement));
                 al.add("�� ä������ :  \t��" + getTagValue("experience-level",eElement));
                 //al.add(" �з����� : " + getTagValue("#required-education-levelname",eElement));
                 al.add("�Ŧ�����������������������������������������������������������\n");
               
              }
           }
           
        } catch(Exception e){
           e.printStackTrace();
           
        }
	   return al;
    }

   public  String getTagValue(String tag, Element eElement) {
      // TODO �ڵ� ������ �޼ҵ� ����
      NodeList nlist = eElement.getElementsByTagName(tag).item(0).getChildNodes();
      Node nValue = (Node) nlist.item(0);
      if(nValue == null)
         return null;
      return nValue.getNodeValue();
   }

}