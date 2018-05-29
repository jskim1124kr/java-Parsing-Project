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
	
	String demestic_all[] = {"전국", "서울", "경기", "인천", "강원", "충북", "전북", "전남","경북","경남","울산","대구","부산","제주","세종","광주"};
	String jobtype[] = {"서비스업","제조업","IT","금융업", "교육업", "미디어","유통업","건설업","기타"};
	String majors []  = {"기계,자동차","전기,전자","건축,토목","재료,금속","법학,상경","생명공학,생물학","컴퓨터,정보통신공학"};
	String academic_abilitys[] = {"고졸","대졸 (2,3년)","대졸 (4년)","석/박사 졸업","학력무관"};
	
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> list1 = new ArrayList<String>();
	ArrayList<String> favorite = new ArrayList<String>();
	Map<String, List> resultMap = new HashMap<>();
	
	
	public ProjectMain(){
		
		j.setTitle("일자리 정보 프로그램 ver 1.0");
		j.setSize(1000,650);
		j.setLayout(null);
		
		
		
		icon_background = new JLabel(new ImageIcon(".\\main_icon.jpg"));
		icon_background.setBounds(800, 10, 130, 80);
		j.add(icon_background);

		city = new JLabel("지역별");
		city.setBounds(15, 10, 100, 20);
		j.add(city);
		
		jc = new JComboBox(demestic_all);
		jc.setBounds(15, 30, 100, 20);
		j.add(jc);
		jc.addActionListener(this) ;
		
		job = new JLabel("직업별");
		job.setBounds(120,10, 100, 20);
		j.add(job);
		
		jc2 = new JComboBox(jobtype);
		jc2.setBounds(120, 30, 100, 20);
		j.add(jc2);
		jc2.addActionListener(this);
		
		Majors = new JLabel("전공");
		Majors.setBounds(225, 10, 100, 20);
		j.add(Majors);
		
		jc3 = new JComboBox(majors);
		jc3.setBounds(225,30, 100,20);
		j.add(jc3);
		jc3.addActionListener(this);
		
		academic = new JLabel("학력");
		academic.setBounds(330, 10, 100, 20);
		j.add(academic);
		
		jc4 = new JComboBox(academic_abilitys);
		jc4.setBounds(330,30, 100,20);
		j.add(jc4);
		jc4.addActionListener(this);
		
		serch_job_label = new JLabel("검색 : ");
		serch_job_label.setBounds(80, 55, 80, 20);
		j.add(serch_job_label);

		
		serch_job = new JTextField();
		serch_job.setBounds(120, 55, 300, 20);
		j.add(serch_job);
		
		popup =  new JLabel(">>  회사이름을 우클릭 하시면 찜 리스트로 저장됩니다.");
		popup.setBounds(80, 80, 400, 20);
		j.add(popup);
		
		serch_job_button = new JButton("Done");
		serch_job_button.setBounds(426, 55, 68, 20);
		j.add(serch_job_button);
		serch_job_button.addActionListener(this);
			
		
		fButton = new JButton("스크랩 목록");
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
		
		
		recomend = new JLabel("링크를 눌러 주세요!↓↓↓↓↓");
		recomend.setBounds(520, 450, 200, 20);
		j.add(recomend);
		
		
		jl3 = new JList(new DefaultListModel());
		md3 = (DefaultListModel<Object>) jl3.getModel(); 
		
		jlist_scroll3 = new JScrollPane(jl3);
		jlist_scroll3.setBounds(510, 470, 450, 100);
		j.add(jlist_scroll3);
		
		list = (ArrayList<String>) resultMap.get("data");
		md.addElement("공채속보>>>>>>>>>>>>>>>");
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
						md3.addElement("더 많은 정보를 원하시나요??"+"\n");
						md3.addElement("해당 기업의 상세 채용 정보입니다 !"+"\n");
						md3.addElement(URL);
					}
				}
			} 
			
		});

	
		jl.addMouseListener(new MouseAdapter()  {
			
			public void mouseClicked(MouseEvent mouseEvent){
				JList theList = (JList)mouseEvent.getSource();
				if (mouseEvent.getModifiers() == MouseEvent.BUTTON3_MASK){
					jp.showMessageDialog(null, "찜목록에 추가 했습니다.");
					
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

	
		
		back = new JButton("뒤로가기");
		back.setBounds(80, 565, 100, 20);
		j.add(back);
		back.addActionListener(this);
		
		next = new JButton("다음 페이지");
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
				jp.showMessageDialog(null, "검색창에 검색어를 입력해여 주세요");
				md.removeAllElements();
			
				md.addElement("검색 오류!");
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
					jp.showMessageDialog(null, demestic_all[i]+" (을)를 선택 하셨습니다.");
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
					jp.showMessageDialog(null, jobtype[i]+" (을)를 선택 하셨습니다.");
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
					jp.showMessageDialog(null, majors[i]+" (을)를 선택 하셨습니다.");
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
					jp.showMessageDialog(null, academic_abilitys[i]+" (을)를 선택 하셨습니다.");
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
	    	  jp.showMessageDialog(null, "뒤로 이동 합니다.");
	             
	             page--;
	             if(page == 0 )
	            	 jp.showMessageDialog(null, "첫번째 페이지 입니다.");
	             else {
	            	 md.removeAllElements();
	            	 resultMap = mp.result(page);
		             list =  (ArrayList<String>) resultMap.get("data");;
		         	md.addElement("공채속보>>");
		            for(int i = 0; i<list.size(); i++)
		            {   
		             md.addElement(list.get(i)+"\n");
		            }
	             }
	      }

		 if(e.getSource().equals(next)){
				md2.removeAllElements();
		    	 jp.showMessageDialog(null, "다음 페이지로 이동 합니다.");
		    	 page++;
			     if(page >= 5  )
			     {
	            	 jp.showMessageDialog(null, "마지막 페이지 입니다.");
	            	
			     }
			     else {
			    	 md.removeAllElements();
			    	 resultMap = mp.result(page);
		             list =  (ArrayList<String>) resultMap.get("data");;
		         	 md.addElement("공채속보>>");
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
		
		JFrame favoritesFrame = new JFrame("스크랩 목록");
		JLabel background2, lotation_popup;
		JButton renew = new JButton("새로고침");
		JButton remove = new JButton("제거");
		JButton close = new JButton("닫기");
		JList favoriteList;
		DefaultListModel<Object> md;
		
		
		
		public FavoriteWindow(){
		
			favoritesFrame.setSize(480, 610);
			favoritesFrame.setLayout(null);
			favoritesFrame.setLocation(880,0);
			
			
			lotation_popup = new JLabel("> 공지 : 제거 하시려면 회사명을 누르고 제거 버튼을 누르세요 ! ");
			
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
					jp.showMessageDialog(null, "새로고침 됬습니다.");
					if(md.getSize()==0){  //리스트모델의 사이즈가 0이되면 삭제버튼을 누를 수 없게 한다.
						   jp.showMessageDialog(null, "항목이 비어 있습니다!");
					}
				 }
				
				if(e.getSource().equals(remove)){
					//삭제 버튼을 눌렀을때
					   int index = favoriteList.getSelectedIndex(); //선택된 항목의 인덱스를 가져온다.
					             //인덱스는 0부터 시작
					   //for(int i = index - 1; i < index + 6 ;i++)
						   md.removeRange(index-1, index+5);//리스트모델에서 선택된 항목을 지운다.
					   if(md.getSize()==0){  //리스트모델의 사이즈가 0이되면 삭제버튼을 누를 수 없게 한다.
						   jp.showMessageDialog(null, "항목이 비어 있습니다!");
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

