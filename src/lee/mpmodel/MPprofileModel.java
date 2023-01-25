package lee.mpmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import an.OjdbcConnection;

public class MPprofileModel {
	
	String user_id;
	String user_name;
	String user_phonenum;
//---------------------------
	String user_password;
	
	
	
	
	public String getUser_name() {
		return user_name;
	}
	
	public String getUser_phonenum() {
		return user_phonenum;
	}
	
	public String getUser_password() {
		return user_password;
	}

	public MPprofileModel(ResultSet rs) throws SQLException {
		user_id = rs.getString("user_id");
		user_name = rs.getString("user_name");
		user_phonenum = rs.getString("user_phonenum");
		user_password = rs.getString("user_password");
	}

	
 
	
	public static void MPdeleteUserInfo(Connection conn, String user_id) {
		String sql = "DELETE FROM user_info WHERE user_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, user_id);
			System.out.println(user_id);
			System.out.println("ȸ������ ���� ����? : " + pstmt.executeUpdate());	
		} catch (SQLException e) {
			System.out.println("");
		}
	}
	
	
	//ȸ���� ��� �������� �޼���
	public static String MPgetUserPw(Connection conn, String user_id) {
		String sql = "SELECT user_password FROM user_info WHERE user_id = ?";
		String userPw = "";
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			
			pstmt.setString(1, user_id);
			
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next() ) {
					userPw = rs.getString("user_password");
				}
			}	
		} catch (SQLException e) {
			System.out.println("");
		}
		return userPw;
	}
	
	//ȸ���� ����ó �������� �޼���
	public static String MPgetUserPhoneNum(Connection conn, String user_id) {
		String sql = "SELECT user_phonenum FROM user_info WHERE user_id = ?";
		String userPhone = "";
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			
			pstmt.setString(1, user_id);
			
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next() ) {
					userPhone = rs.getString("user_phonenum");
				}
			}	
		} catch (SQLException e) {
			System.out.println("");
		}
		return userPhone;
	}
	
	//ȸ���� �̸� �������� �޼���
	public static String MPgetUserName(Connection conn, String user_id) {
		String sql = "SELECT user_name FROM user_info WHERE user_id = ?";
		String userName = "";
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			
			pstmt.setString(1, user_id);
			
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next() ) {
					userName = rs.getString("user_name");
				}
			}	
		} catch (SQLException e) {
			System.out.println("");
		}
		return userName;
	}
	
	
	//�̸������޼��� 
	public static void MPupdateUserName(Connection conn, String user_id, String newName) {
		String sql = "UPDATE user_info SET user_name = ? WHERE user_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ) {
	
			pstmt.setString(1, newName);
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
			//System.out.println("�̸� ���� ����? : " + pstmt.executeUpdate());	
		} catch (SQLException e) {
			System.out.println("");
		}
	}
	
	//����ó�����޼���
	public static void MPupdateUserPhoneNum(Connection conn, String user_id, String newPhoneNum) {
		String sql = "UPDATE user_info SET user_phonenum = ? WHERE user_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, newPhoneNum);
			pstmt.setString(2, user_id);
			
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("");
		}
	}
	
	//��������޼���
	public static void MPupdateUserPw(Connection conn, String user_id, String newPw) {
		String sql = "UPDATE user_info SET user_password = ? WHERE user_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, newPw);
			pstmt.setString(2, user_id);
			
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("");
		}
	}
	

	//MPprofilePanel_2�� ��� ȸ������ String���� ��ȯ�ϴ� �޼���
	public static String MPprofileInfo(String user_id) {
		String sql = "SELECT user_id, user_name, user_phonenum, user_password FROM user_info WHERE user_id = ?";
		String MPprofileLbStr = "";	
		MPprofileModel pm = null;
		try (
				Connection conn = OjdbcConnection.getConnection();				
				PreparedStatement pstmt = conn.prepareStatement(sql);		
		){
			pstmt.setString(1, user_id);
		
			try(ResultSet rs = pstmt.executeQuery();){
				//��
				while(rs.next()) {
					pm = new MPprofileModel(rs);
					MPprofileLbStr = pm.toString();				
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return MPprofileLbStr;
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"<html><pre style=\"font-family:���; font-size:16;\">���̵�    : %s<br><br>"
				+ "�̸�       : %s<br><br>"
				+ "�ڵ��� ��ȣ  :   %s</pre><html/>"
				, user_id, user_name, user_phonenum);	
	}
	
}
