package lee.mpcomponents;

import java.awt.CardLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import an.OjdbcConnection;
import hong.SaveInfo;
import lee.mpmodel.MPprofileModel;

public class MPprofilePanel extends JPanel {

	
	SaveInfo saveInfo;
	String user_id; //���߿� ����

	MPprofilePanel_1 MPprofile_1;
	MPprofilePanel_2 MPprofile_2;
	
	CardLayout MPprofileCard = new CardLayout();

	
	public MPprofilePanel(SaveInfo saveInfo) {
		this.saveInfo = saveInfo;
		this.user_id = saveInfo.get_user_id();
		
		MPprofile_1 = new MPprofilePanel_1();
		MPprofile_2 = new MPprofilePanel_2();
		
		setLayout(MPprofileCard);

		add("������ ����", MPprofile_1);
		add("������ ����", MPprofile_2);

		
		ImageIcon backImg = new ImageIcon("image/mp�������гι��.png");
		JLabel backLb = new JLabel(backImg);
		backLb.setBounds(0, 0, 510, 460);
		add(backLb);		

		//MPprofile_1�� ȸ������ ����_________________________________________________________________________________________
		//�����͸��� �޼��带 �̿��� ȸ�������� String���� ������ �� �����ʶ󺧿� �ֱ�
		String MPprofileLbStr = MPprofileModel.MPprofileInfo(user_id);
		JLabel MPprofileLb = new JLabel(MPprofileLbStr);
		//�����ʶ� ����
		MPprofileLb.setOpaque(true);//���߿� ���� 
		MPprofileLb.setFont(new Font("���", Font.BOLD, 18)); 
		MPprofileLb.setBounds(50, 50, 390, 200);
		MPprofileLb.setVerticalAlignment(JLabel.TOP);
		MPprofile_1.add(MPprofileLb);
		//_________________________________________________________________________________________________________________
		
		//MPprofile_2�� tf�鿡 ȸ�������� �⺻���� �� �ְ�___________________________________________________________________________
		try (Connection conn = OjdbcConnection.getConnection();){
			//�̸�
			MPprofile_2.MPnameTf.setText(MPprofileModel.MPgetUserName(conn, user_id));		
			//�ڵ�����ȣ
			String str = MPprofileModel.MPgetUserPhoneNum(conn, user_id);
			String[] arr = str.split("-");
			MPprofile_2.MPphoneTf_1.setText(arr[0]);
			MPprofile_2.MPphoneTf_2.setText(arr[1]);
			MPprofile_2.MPphoneTf_3.setText(arr[2]);
			//��й�ȣ
			MPprofile_2.MPnewpwTf.setText(MPprofileModel.MPgetUserPw(conn, user_id));	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//__________________________________________________________________________________________________________________	
	}
	

	public void showMPprofile_2() {
		MPprofileCard.show(this, "������ ����");
	}
	
	
	public void showMPprofile_1() {
		MPprofileCard.show(this, "������ ����");
	}
	
}
