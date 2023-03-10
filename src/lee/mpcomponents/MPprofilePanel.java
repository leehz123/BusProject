package lee.mpcomponents;

import java.awt.CardLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import an.OjdbcConnection;
import hong.SaveInfo;
import lee.mpmodel.MPprofileModel;

public class MPprofilePanel extends JPanel {

	
	SaveInfo saveInfo;
	String user_id; //나중에 수정

	MPprofilePanel_1 MPprofile_1;
	MPprofilePanel_2 MPprofile_2;
	
	CardLayout MPprofileCard = new CardLayout();

	
	public MPprofilePanel(SaveInfo saveInfo) {
		this.saveInfo = saveInfo;
		this.user_id = saveInfo.get_user_id();
		
		MPprofile_1 = new MPprofilePanel_1();
		MPprofile_2 = new MPprofilePanel_2();
		
		setLayout(MPprofileCard);

		add("내정보 보기", MPprofile_1);
		add("내정보 수정", MPprofile_2);
		
		//profile_1 에 내 정보 뜨는 라벨 붙이기_____________________________________________________________________________
		String sql = "SELECT user_id, user_name, user_phonenum, user_password FROM user_info WHERE user_id = ?";
		String MPprofileLbStr = "";	
		MPprofileModel pm = null;
		try (
				Connection conn = OjdbcConnection.getConnection();				
				PreparedStatement pstmt = conn.prepareStatement(sql);		
		){
			pstmt.setString(1, user_id);  // 아 pstmt.setString()로 물음표 먼저 채워주고 rs써야됨!
		
			try(ResultSet rs = pstmt.executeQuery();){
				//라벨
				while(rs.next()) {
					pm = new MPprofileModel(rs);
					MPprofileLbStr = pm.toString();				
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel MPprofileLb = new JLabel(MPprofileLbStr);
		//프로필라벨 설정
		MPprofileLb.setOpaque(true);//나중에 삭제 
		MPprofileLb.setFont(new Font("고딕", Font.BOLD, 18)); 
		MPprofileLb.setBounds(50, 50, 390, 200);
		MPprofileLb.setVerticalAlignment(JLabel.TOP);
		MPprofile_1.add(MPprofileLb);
		//__________________________________________________________________________________________________________________
		
		
		//MPprofile_2의 tf들에 회원정보가 기본으로 떠 있게_________________________________________________________________________
		//이름
		MPprofile_2.MPnameTf.setText(pm.getUser_name());
				
		//핸드폰번호
		String str = pm.getUser_phonenum();
		String[] arr = str.split("-");
		MPprofile_2.MPphoneTf_1.setText(arr[0]);
		MPprofile_2.MPphoneTf_2.setText(arr[1]);
		MPprofile_2.MPphoneTf_3.setText(arr[2]);
		//비밀번호
		MPprofile_2.MPnewpwTf.setText(pm.getUser_password());
		//__________________________________________________________________________________________________________________

	}
	

	public void showMPprofile_2() {
		MPprofileCard.show(this, "내정보 수정");
	}
	
	
	public void showMPprofile_1() {
		MPprofileCard.show(this, "내정보 보기");
	}
	
}
