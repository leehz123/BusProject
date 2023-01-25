package an.admin;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import an.admin.action.Admin_OutAction;
import an.admin.action.Admin_RouteAction;
import an.admin.action.Admin_UserAction;
import an.login.Login_Mainframe;
import jang.GUI.MemberManagementGUI;
import jang.GUI.RouteManageMentGUI;

public class Admin_MainFrame extends JFrame{
	ImageIcon logoutimage = new ImageIcon("image/button3.png");
	ImageIcon routemodifyimage = new ImageIcon("image/button4.png");
	ImageIcon userModifyimage = new ImageIcon("image/button5.png");
	
	JButton userModify = new Admin_Button("ȸ������ ����");
	JButton routemodify = new Admin_Button("�뼱���� ����");
	JButton out 		= new Admin_Button("������");
	
	JPanel main = new Admin_MainPanel(userModify, routemodify, out);
	JPanel imagepanel = new Admin_MainImagePanel();
	JPanel titlePanel = new Admin_TitlePanel(); 
	public Admin_MainFrame() {
		setTitle("������ ������");
		
		userModify.setText("");
		userModify.setIcon(userModifyimage);
		
		routemodify.setText("");
		routemodify.setIcon(routemodifyimage);
		
		out.setText("");
		out.setIcon(logoutimage);
		
		
		add(main,BorderLayout.SOUTH);
		add(titlePanel,BorderLayout.NORTH);
		add(imagepanel,BorderLayout.CENTER);
		//////////////////////////////////////////////////////////////////
		
		Admin_RouteAction rtevent = new Admin_RouteAction(this);
		Admin_UserAction userevent = new Admin_UserAction(this);
		Admin_OutAction outevent = new Admin_OutAction(this);
		
		userModify.addActionListener(userevent);
		routemodify.addActionListener(rtevent);
		out.addActionListener(outevent);

		///////////////////////////////////////////////////////////////////
		setSize( 1100, 750);
		setLocationRelativeTo(null);
		setResizable(false);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	//ȸ������ �����̵� �׼�
	public void userModifyAction() {
		new MemberManagementGUI();
		dispose();
	}
	//�뼭���� �����̵� �׼�
	public void routeModifyAction() {
		new RouteManageMentGUI();
		dispose();
		
	}
	//������ �׼�
	public void outAction() {
	dispose();
	new Login_Mainframe();
		
	}
}
