package an.userinfo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import an.login.Login_Mainframe;
import an.userinfo.action.Info_LogoutAction;
import an.userinfo.action.Info_MyInfoAction;
import an.userinfo.action.Info_ReservationAction;
import hong.SaveInfo;
import hong.selectroute.SelectRouteMainFrame;
import lee.mpcomponents.MPmainFrame;

public class Info_MainFrame extends JFrame{
	JButton Reservation = new Info_JButton("����");
	JButton myinfo 		= new Info_JButton("������");
	JButton logout 		= new Info_JButton("�α� �ƿ�");
	
	JPanel buttonPanel = new Info_Panel(Reservation,myinfo,logout);
	JPanel imagePanel = new Info_ImagePanel();
	JPanel titlePanel = new Info_TitlePanel();

	SaveInfo saveInfo;
	
	
	public Info_MainFrame(SaveInfo saveInfo) {
		
		this.saveInfo = saveInfo;
		
		setTitle("��������");
		
		add(titlePanel,BorderLayout.NORTH);
		add(imagePanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
		/////////////////////////////////////////
		
		Info_ReservationAction rvevent = new Info_ReservationAction(this);
		Info_MyInfoAction myevent = new Info_MyInfoAction(this);
		Info_LogoutAction logevent = new Info_LogoutAction(this);
		
		Reservation.addActionListener(rvevent);
		myinfo.addActionListener(myevent);
		logout.addActionListener(logevent);
			
		
		/////////////////////////////////////////
		setBounds(500, 200, 800, 500);
		setResizable(false);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//���� �̵� �̺�Ʈ
	public void ReservationAction() {
		new SelectRouteMainFrame(saveInfo);
		dispose();
	}
	
	//������ �̵� �̺�Ʈ
	public void myinfoAction() {
		new MPmainFrame(saveInfo);
		dispose();
	}
	
	//�α׾ƿ� �̵� �̺�Ʈ
	public void logoutAction() {
		new Login_Mainframe();
		dispose();
	}

}