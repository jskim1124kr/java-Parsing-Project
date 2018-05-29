package Project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException; 
import java.net.URI; 
import java.net.URISyntaxException;
import java.awt.Desktop;

public class ProjectMain extends JFrame implements ActionListener {

	JFrame j = new JFrame();
	
	Container con = j.getContentPane();
	int page = 0;
	Image icon;
	JOptionPane jp;
	JComboBox<ArrayList> jc , jc2 ,jc3,jc4;

	JLabel city , job , background1,background2, icon_background;
	JLabel serch_job_label , academic ,Majors, recomend, popup;	
	
	JPanel pane1 = new JPanel(new BorderLayout());
	
	JTextField serch_job;

	JButton serch_job_button , back , next, fButton;
	
	JList jl, jl2 ,jl3 ;
	DefaultListModel<Object> md, md2,md3;
	
	JScrollPane jlist_scroll , jlist_scroll2, jlist_scroll3;
	
	SearchAPI sj;
	MainScreen ms;
	LocalInfo ld;
	TypeInfo jt;
	AcademicInfo ab;
	MajorInfo ma;
	MovePage mp;
	
	String demestic_all[] = {"����", "����", "���", "��õ", "����", "���", "����", "����","���","�泲","���","�뱸","�λ�","����","����","����"};
	String jobtype[] = {"���񽺾�","������","IT","������", "������", "�̵��","�����","�Ǽ���","��Ÿ"};
	String majors []  = {"���,�ڵ���","����,����","����,���","���,�ݼ�","����,���","�������,������","��ǻ��,������Ű���"};
	String academic_abilitys[] = {"����","���� (2,3��)","���� (4��)","��/�ڻ� ����","�з¹���"};
	
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> list1 = new ArrayList<String>();
	ArrayList<String> favorite = new ArrayList<String>();
	Map<String, List> resultMap = new HashMap<>();
	
	
	public ProjectMain(){
		
		j.setTitle("���ڸ� ���� ���α׷� ver 1.0");
		j.setSize(1000,650);
		j.setLayout(null);
		
		
		
		icon_background = new JLabel(new ImageIcon(".\\main_icon.jpg"));
		icon_background.setBounds(800, 10, 130, 80);
		j.add(icon_background);

		city = new JLabel("������");
		city.setBounds(15, 10, 100, 20);
		j.add(city);
		
		jc = new JComboBox(demestic_all);
		jc.setBounds(15, 30, 100, 20);
		j.add(jc);
		jc.addActionListener(this) ;
		
		job = new JLabel("������");
		job.setBounds(120,10, 100, 20);
		j.add(job);
		
		jc2 = new JComboBox(jobtype);
		jc2.setBounds(120, 30, 100, 20);
		j.add(jc2);
		jc2.addActionListener(this);
		
		Majors = new JLabel("����");
		Majors.setBounds(225, 10, 100, 20);
		j.add(Majors);
		
		jc3 = new JComboBox(majors);
		jc3.setBounds(225,30, 100,20);
		j.add(jc3);
		jc3.addActionListener(this);
		
		academic = new JLabel("�з�");
		academic.setBounds(330, 10, 100, 20);
		j.add(academic);
		
		jc4 = new JComboBox(academic_abilitys);
		jc4.setBounds(330,30, 100,20);
		j.add(jc4);
		jc4.addActionListener(this);
		
		serch_job_label = new JLabel("�˻� : ");
		serch_job_label.setBounds(80, 55, 80, 20);
		j.add(serch_job_label);

		
		serch_job = new JTextField();
		serch_job.setBounds(120, 55, 300, 20);
		j.add(serch_job);
		
		popup =  new JLabel(">>  ȸ���̸��� ��Ŭ�� �Ͻø� �� ����Ʈ�� ����˴ϴ�.");
		popup.setBounds(80, 80, 400, 20);
		j.add(popup);
		
		serch_job_button = new JButton("Done");
		serch_job_button.setBounds(426, 55, 68, 20);
		j.add(serch_job_button);
		serch_job_button.addActionListener(this);
			
		
		fButton = new JButton("��ũ�� ���");
		fButton.setBounds(510, 55, 110, 20);
		j.add(fButton);
		fButton.addActionListener(this);
		
		
		resultMap = ms.mains(page);

		jl = new JList(new DefaultListModel());
		md = (DefaultListModel<Object>) jl.getModel(); 
		
		jlist_scroll = new JScrollPane(jl);
		jlist_scroll.setBounds(80, 100, 420, 450);
		j.add(jlist_scroll);
		
		jl2 = new JList(new DefaultListModel());
		md2 = (DefaultListModel<Object>) jl2.getModel(); 
		
		jlist_scroll2 = new JScrollPane(jl2);
		jlist_scroll2.setBounds(510, 80, 450, 370);
		j.add(jlist_scroll2);
		
		
		recomend = new JLabel("��ũ�� ���� �ּ���!������");
		recomend.setBounds(520, 450, 200, 20);
		j.add(recomend);
		
		
		jl3 = new JList(new DefaultListModel());
		md3 = (DefaultListModel<Object>) jl3.getModel(); 
		
		jlist_scroll3 = new JScrollPane(jl3);
		jlist_scroll3.setBounds(510, 470, 450, 100);
		j.add(jlist_scroll3);
		
		list = (ArrayList<String>) resultMap.get("data");
		md.addElement("��ä�Ӻ�>>>>>>>>>>>>>>>");
		for( int i = 0; i <  list.size();i ++)
		{
			
			md.addElement(list.get(i)+"\n");
		}
		
		jl.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0){
				ArrayList<String> Detail = new ArrayList<>();
				String URL = null;
				md2.removeAllElements();
				md3.removeAllElements();
				
				if(!arg0.getValueIsAdjusting()) {
					
					int selectedIdx = jl.getSelectedIndex();
					int parsedIdx = (selectedIdx - 2) / 7;
					int remain = (selectedIdx - 2) % 7;
					
					if (selectedIdx != 0 && selectedIdx != 1 &&
							remain != 5 && remain != 6) {
							Detail = DetailInfo.detailList((String) resultMap.get("idx").get((selectedIdx - 2) / 7));		
							URL = DetailUrl.returnURL((String) resultMap.get("idx").get((selectedIdx - 2) / 7));
					}
	
					for(int i = 0; i< Detail.size(); i++) {
						md2.addElement(Detail.get(i));
					
					}
					if(URL != null){
						md3.addElement("�� ���� ������ ���Ͻó���??"+"\n");
						md3.addElement("�ش� ����� �� ä�� �����Դϴ� !"+"\n");
						md3.addElement(URL);
					}
				}
			} 
			
		});

	
		jl.addMouseListener(new MouseAdapter()  {
			
			public void mouseClicked(MouseEvent mouseEvent){
				JList theList = (JList)mouseEvent.getSource();
				if (mouseEvent.getModifiers() == MouseEvent.BUTTON3_MASK){
					jp.showMessageDialog(null, "���Ͽ� �߰� �߽��ϴ�.");
					
					int selectedIdx = jl.getSelectedIndex();
					
					for(int i = selectedIdx-2; i<selectedIdx+5; i++){
						favorite.add((String)resultMap.get("data").get(i));
					}	
					
				}
			}
		});
	
		
		jl3.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0){
			if(!arg0.getValueIsAdjusting()) {
				try{
						OpenWeb.WebPage(jl3.getSelectedValue().toString());
				} catch (NullPointerException e){ }
			}
		}
});

	
		
		back = new JButton("�ڷΰ���");
		back.setBounds(80, 565, 100, 20);
		j.add(back);
		back.addActionListener(this);
		
		next = new JButton("���� ������");
		next.setBounds(370, 565, 130, 20);
		j.add(next);
		next.addActionListener(this);
		
		background1 = new JLabel();
		background1.setOpaque(true);
		background1.setBackground(Color.white);

		background1.setBounds(10, 10, 960, 590);
		j.add(background1);
		
		background2 = new JLabel();
		background2.setOpaque(true);
		background2.setBackground(Color.BLUE);

		background2.setBounds(0, 00, 1000, 650);
		j.add(background2);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(serch_job_button))
		{
			md2.removeAllElements();
			if(serch_job.getText().equals(""))
			{
				jp.showMessageDialog(null, "�˻�â�� �˻�� �Է��ؿ� �ּ���");
				md.removeAllElements();
			
				md.addElement("�˻� ����!");
			}
			else {
			
				serch_job.setText("");
				sj = new SearchAPI();
				ms = new MainScreen();
				md.removeAllElements();
				for( int i = 0; i <  sj.Keyboard(serch_job.getText()).size();++i )
				{
					md.addElement(sj.Keyboard(serch_job.getText()).get(i)+"\n");
				}
			}
		}
		
		if(e.getSource().equals(jc) )
		{
			md2.removeAllElements();
			for(int i = 0; i< 16; i++) 
			{				
				if(jc.getSelectedItem().equals(demestic_all[i]))
				{
					jp.showMessageDialog(null, demestic_all[i]+" (��)�� ���� �ϼ̽��ϴ�.");
					md.removeAllElements();
					md.addElement(demestic_all[i]+ ">>");
					ld = new LocalInfo();
					resultMap = ld.Local_datas(demestic_all[i]);
					list1 = (ArrayList<String>) resultMap.get("data");
					
					for(int j=0; j< list1.size(); j++)
					{
						md.addElement(list1.get(j)+"\n");
						
					}
				}	
			}
		}
		
		if(e.getSource().equals(jc2))
		{
			md2.removeAllElements();
			for(int i = 0; i< 9; i++) 
			{				
				if(jc2.getSelectedItem().equals(jobtype[i]))
				{
					jp.showMessageDialog(null, jobtype[i]+" (��)�� ���� �ϼ̽��ϴ�.");
					md.removeAllElements();
					md.addElement(jobtype[i]+ ">>");
					jt = new TypeInfo();
					resultMap = jt.job_datas(jobtype[i]);
					list1 = (ArrayList<String>) resultMap.get("data");
					for(int j=0; j< list1.size(); j++)
					{
						md.addElement(list1.get(j)+"\n");
							
					}
				}
			}  
		}
		
		if(e.getSource().equals(jc3))
		{
			md2.removeAllElements();
			for(int i = 0; i< 7; i++) 
			{				
				if(jc3.getSelectedItem().equals(majors[i]))
				{
					jp.showMessageDialog(null, majors[i]+" (��)�� ���� �ϼ̽��ϴ�.");
					md.removeAllElements();
					md.addElement(majors[i]+ ">>");
					ma = new MajorInfo();
					resultMap = ma.Major_datas(majors[i]);
					list1 = (ArrayList<String>) resultMap.get("data");
					for(int j=0; j< list1.size(); j++)
					{
						md.addElement(list1.get(j)+"\n");
							
					}
				}
			}  
		}
		
		if(e.getSource().equals(jc4))
		{
			md2.removeAllElements();
			for(int i = 0; i< 5; i++) 
			{				
				if(jc4.getSelectedItem().equals(academic_abilitys[i]))
				{
					jp.showMessageDialog(null, academic_abilitys[i]+" (��)�� ���� �ϼ̽��ϴ�.");
					md.removeAllElements();
					md.addElement(academic_abilitys[i]+ ">>");
					ab = new AcademicInfo();
					resultMap = ab.academic_datas(academic_abilitys[i]);
					list1 = (ArrayList<String>) resultMap.get("data");
					for(int j=0; j< list1.size(); j++)
					{
						md.addElement(list1.get(j)+"\n");
							
					}
				}
			}  
		}
	    if(e.getSource().equals(back)){
	    	md2.removeAllElements();
	    	  jp.showMessageDialog(null, "�ڷ� �̵� �մϴ�.");
	             
	             page--;
	             if(page == 0 )
	            	 jp.showMessageDialog(null, "ù��° ������ �Դϴ�.");
	             else {
	            	 md.removeAllElements();
	            	 resultMap = mp.result(page);
		             list =  (ArrayList<String>) resultMap.get("data");;
		         	md.addElement("��ä�Ӻ�>>");
		            for(int i = 0; i<list.size(); i++)
		            {   
		             md.addElement(list.get(i)+"\n");
		            }
	             }
	      }

		 if(e.getSource().equals(next)){
				md2.removeAllElements();
		    	 jp.showMessageDialog(null, "���� �������� �̵� �մϴ�.");
		    	 page++;
			     if(page >= 5  )
			     {
	            	 jp.showMessageDialog(null, "������ ������ �Դϴ�.");
	            	
			     }
			     else {
			    	 md.removeAllElements();
			    	 resultMap = mp.result(page);
		             list =  (ArrayList<String>) resultMap.get("data");;
		         	 md.addElement("��ä�Ӻ�>>");
				     for(int i = 0; i<list.size(); i++)
				     {   
				    	 md.addElement(list.get(i)+"\n");
				     }
			   }
		  }
		 
		 if(e.getSource().equals(fButton)){
			
			FavoriteWindow fWindow = new FavoriteWindow();
			 
			 
			for( int i = 0; i <  favorite.size();i ++)
			{
				fWindow.md.addElement(favorite.get(i));
				
			}
		 }
			
	 }
	
	public class FavoriteWindow implements ActionListener{
		
		JFrame favoritesFrame = new JFrame("��ũ�� ���");
		JLabel background2, lotation_popup;
		JButton renew = new JButton("���ΰ�ħ");
		JButton remove = new JButton("����");
		JButton close = new JButton("�ݱ�");
		JList favoriteList;
		DefaultListModel<Object> md;
		
		
		
		public FavoriteWindow(){
		
			favoritesFrame.setSize(480, 610);
			favoritesFrame.setLayout(null);
			favoritesFrame.setLocation(880,0);
			
			
			lotation_popup = new JLabel("> ���� : ���� �Ͻ÷��� ȸ����� ������ ���� ��ư�� �������� ! ");
			
			lotation_popup.setBounds(10, 40, 400, 20);
			favoritesFrame.add(lotation_popup);
			
		    favoriteList = new JList(new DefaultListModel());
			md = (DefaultListModel<Object>) favoriteList.getModel(); 
			
			close.setBounds(340, 10, 100, 20);
			favoritesFrame.add(close);
			close.addActionListener(this);
			
			renew.setBounds(10, 10, 100, 20);
			favoritesFrame.add(renew);
			renew.addActionListener(this);
			
			remove.setBounds(340, 545, 100, 20);
			favoritesFrame.add(remove);
			remove.addActionListener(this);
			
			JScrollPane favorite_scroll = new JScrollPane(favoriteList);
			favorite_scroll.setBounds(15, 60, 440, 480);
			favoritesFrame.add(favorite_scroll);
			
			background2 = new JLabel();
			background2.setOpaque(true);
			background2.setBackground(Color.CYAN);

			background2.setBounds(0, 00, 1000, 650);
			favoritesFrame.add(background2);
			
			favoritesFrame.setVisible(true);
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(renew)){
					md.removeAllElements();	 
					for( int i = 0; i <  favorite.size();i ++)
					{
						
						md.addElement(favorite.get(i));
						
					}
					jp.showMessageDialog(null, "���ΰ�ħ ����ϴ�.");
					if(md.getSize()==0){  //����Ʈ���� ����� 0�̵Ǹ� ������ư�� ���� �� ���� �Ѵ�.
						   jp.showMessageDialog(null, "�׸��� ��� �ֽ��ϴ�!");
					}
				 }
				
				if(e.getSource().equals(remove)){
					//���� ��ư�� ��������
					   int index = favoriteList.getSelectedIndex(); //���õ� �׸��� �ε����� �����´�.
					             //�ε����� 0���� ����
					   //for(int i = index - 1; i < index + 6 ;i++)
						   md.removeRange(index-1, index+5);//����Ʈ�𵨿��� ���õ� �׸��� �����.
					   if(md.getSize()==0){  //����Ʈ���� ����� 0�̵Ǹ� ������ư�� ���� �� ���� �Ѵ�.
						   jp.showMessageDialog(null, "�׸��� ��� �ֽ��ϴ�!");
						   remove.setEnabled(false);
						   favoritesFrame.setVisible(false);
					   }
					   
					}
				
				if(e.getSource().equals(close))
				{
					favoritesFrame.setVisible(false);
				}
				
		 }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProjectMain pu = new ProjectMain();
	}
}

