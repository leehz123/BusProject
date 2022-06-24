package an.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import an.OjdbcConnection;
import an.admin.Admin_MainFrame;
import an.find.Find_MainFrame;
import an.login.action.Allpanel_loginAction;
import an.login.action.Join_Action;
import an.login.action.Up_action;
import an.sign_up.SignupMain_Frame;
import an.userinfo.Info_MainFrame;
import hong.SaveInfo;

public class Login_Mainframe extends JFrame{
	
	JPanel title = new Login_TitlePanal();
	JPanel image = new Login_ImgPanel();
	JPanel jp = new JPanel();
	
	SaveInfo saveInfo = new SaveInfo();
	
	JButton login = new Lg_Button("�α���");
	JButton join = new Lg_Button("ȸ������");
	JButton up = new Lg_Button("���̵�/��й�ȣ ã��");
	
	JPanel idpanel = new IdPanel();
	JTextField ptext = new IdPwd_TextFeild(10);
	JPanel idPanel2 = new IdPanel2(ptext);

	JPanel pwdPanel = new PwdPanel();
	JPasswordField itext = new JPasswordField(10);
	JPanel pwdPanel2 = new PwdPanel2(itext);
	
	JPanel loginPanel = new Login_MainPanel(login);
	JPanel joinPanel = new JoinPanel(up);
	JPanel findPanel = new FindPanel();
	
	JPanel buttonPanel = new Login_buttonPanel(login, up, join); 
	
	JComboBox<String> combo = new Login_Combo();
	
	String id;
	
	/////////////////////////////////////////////////////////////////
	public Login_Mainframe() {

		setTitle("���� ����ý���");
		JPanel allIdLabel = new AllPanel();
		setLayout(new BorderLayout(0,0));
		jp.setLayout(new FlowLayout());
		jp.add(allIdLabel);
		
		setLayout(new BorderLayout());
		add(title, BorderLayout.NORTH);
		add(jp, BorderLayout.SOUTH);
		add(image,BorderLayout.CENTER);
		
		///////////////////////////////////////////////////////////////
		
		//�α����� ������,ȸ�� �̵� �̺�Ʈ
		Allpanel_loginAction loginevent = new Allpanel_loginAction(this);
		//ȸ������ �̵� �̺�Ʈ
		Join_Action joinevent = new Join_Action(this);
		//���̵� ��й�ȣ ã�� �̵� �̺�Ʈ
		Up_action upevent = new Up_action(this);
		
		login.addActionListener(loginevent);
		join.addActionListener(joinevent);
		up.addActionListener(upevent);
		
		
		///////////////////////////////////////////////////////////////
		setBounds(500, 200, 800, 500);
		setResizable(false);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	 class AllPanel extends JPanel{
		 public AllPanel() {
			 setLayout(new FlowLayout());
			 setBackground(new Color(0x77757B));
				add(idpanel);add(idPanel2);
				add(pwdPanel);add(pwdPanel2);add(combo);
				add(buttonPanel); 
		}
	 }
	 // ���̵� �ѱ�� �޼���
	 public void all_id(String id) {
			saveInfo.set_user_id(id);
		}
	 
	 
	 //�α��� üũ �޼���
	 public void loginac() {
		 
		 	id = ptext.getText();
			String pass = itext.getText();
			String check = combo.getSelectedItem().toString();
			
		
		String sql = String.format("SELECT user_password FROM user_info WHERE user_id = '%s'"
				+ " AND user_password ='%s'and user_passenger_manager = '%s'",
				id, pass,check);	
		try(Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
				ResultSet rset = pstmt.executeQuery();

				rset.next();
				
				if (pass.equals(rset.getString(1))) {
					if(check == "�մ�") {
						all_id(id);
						dispose();
						new Info_MainFrame(saveInfo);
					}else {
					new Admin_MainFrame();
					dispose();
					}
				
				} else
					JOptionPane.showMessageDialog(null, "Login Failed", "�α��� ����", 1);

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Login Failed", "�α��� ����", 1);
			} 
	 }
	 
	 // ȸ���������� �̵� �޼���
	 public void join() {
		 new SignupMain_Frame();
	 }
	 //��й�ȣ/���̵� ã�� �̵� �޼���
	 public void  up() {
		 new Find_MainFrame();
	 }
	
	public static void main(String[] args) {
			  

	  new Login_Mainframe();

	}
}