package lee.mpmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class MPreservationlistModel {
	
	Integer br_id;//���Ź�ȣ

	String bi_day;//��¥
	String bi_time;//�ð�
	
	String rt_depart_from;//�����
	String rt_arrive_at;//������
	
	String bs_name;//�¼���ȣ
	
	//���� ��� ���ϱ� ���ؼ� �߰�
	String br_age_group;//���ɱ���
	Integer  rt_charge;//���

	Integer bs_id;//�¼�id
	
	
	
	//rs �޾Ƽ� ������ sql���� �ش��ϴ� ����� ��������� �����ϴ� �޼��� 
	//ex. while(rs.next()) {new MPreservationlistModel(rs);}
	public MPreservationlistModel(ResultSet rs) throws SQLException {
		br_id = rs.getInt("br_id");
		bi_day = rs.getString("bi_day");
		bi_time = rs.getString("bi_time");
		rt_depart_from = rs.getString("rt_depart_from");
		rt_arrive_at = rs.getString("rt_arrive_at");
		bs_name = rs.getString("bs_name");
		br_age_group = rs.getString("br_age_group");
		rt_charge = rs.getInt("rt_charge");
	}
	
	
	
	//���Ź�ȣ �������� �޼���
	public Integer getBr_id() {
		return br_id;
	}
	
	
	//���̿� ���� ���� ������ �������
	public double totalCharge() {
		if(br_age_group.equals("����")) {
			return rt_charge * 1;
		} else if(br_age_group.equals("û�ҳ�")) {
			return rt_charge * 0.85;
		} else if(br_age_group.equals("�Ƶ�")) {
			return rt_charge * 0.7;
		}
		return 0; 
	}
	
	//bus_reservation ���̺��� user_id�� �ش��ϴ� �� ���� 
	public static void delete_user_id_row(Connection conn, String user_id) {
		String sql = "DELETE FROM bus_reservation WHERE user_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, user_id);
			System.out.println("user_id�� �� ���� ����? : " + pstmt.executeUpdate());
		} catch (SQLException e) {
			System.out.println("�� ���� �� ��");
		}
	}
	
	

	// bs_id(�����¼� ���̵�. �¼���ȣ �ƴ�!) �� �ش��ϴ� �¼����ſ��� (1= ���ŵ�, 0=���žȵ�)�� 1���� 0���� �ٲٴ� �޼���
	public static void update_bs_is_reserved(Connection conn, int bs_id) {
		String sql = "UPDATE bus_seat SET bs_is_reserved = 0 WHERE bs_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {	
			
			pstmt.setInt(1, bs_id);
			
			System.out.println("bs_id�� �ش��ϴ� bs_is_reserved 0���� �ٲٱ� ����? : " + pstmt.executeUpdate());	
		
		} catch (SQLException e) {
			System.out.println("���� �� ��");
		}
	}
	
	
	
	//bus_reservation���̺��� ���Ź�ȣ(br_id)�� �ش��ϴ� �� ����
	public static void delete_br_id_row(Connection conn, int br_id) {
		String sql = "DELETE FROM bus_reservation WHERE br_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, br_id);
			System.out.println("br_id�� �� ���� ����? : " + pstmt.executeUpdate());	
		} catch (SQLException e) {
			System.out.println("");
		}
	}
	
	
	
	
	//user_id �Է��ϸ� bs_id�� ���ؼ� ArrayList�� ����ִ� �޼��� 
	public static ArrayList<Integer> get_bs_id(Connection conn, String user_id) {
		ArrayList<Integer> bs_id_list = new ArrayList<>();
		String sql = "SELECT user_id, br_id, bs_id"
				+ " FROM user_info"
				+ " INNER JOIN bus_reservation USING(user_id)"
				+ " INNER JOIN bus_seat USING (bs_id)"
				+ " WHERE user_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, user_id);
			
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next() ) {
					bs_id_list.add(rs.getInt("bs_id"));
				}
			}	
		} catch (SQLException e) {
			System.out.println("");
		}
		return bs_id_list;
	}
	
	

	
	//Bus_Seat + Bus_Reservation �̳����� �ؼ� br_id(���Ź�ȣ)�� �Ű������� �����ϸ� bs_id(�¼�id)�� �����ϴ� �޼���
	public static int get_bs_id(Connection conn, int br_id) {
		String sql = "SELECT bs_id, br_id FROM bus_seat INNER JOIN bus_Reservation USING(bs_id) WHERE br_id = ?";	
		int bs_id = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setInt(1, br_id);
			
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next() ) {
					bs_id = rs.getInt("bs_id");
				}
			}			
		} catch (SQLException e) {
			System.out.println("");
		}
		return bs_id;
	}
	

	
	
	//Ư�� ���Ź�ȣ�� �Է��ϸ� �ش� ���Ź�ȣ�� ���� ��ȯ�ϴ� �޼���
	public static MPreservationlistModel get(Connection conn, int br_id) {
		String sql = 
				"SELECT br_id, bi_day, bi_time, rt_depart_from, rt_arrive_at, bs_name, br_age_group, rt_charge"
				+ " FROM user_info"
				+ " INNER JOIN bus_reservation USING(user_id)"
				+ " INNER JOIN bus_info USING (bi_id)"
				+ " INNER JOIN bus_route USING (rt_id)"
				+ " INNER JOIN bus_seat USING (bs_id)"
				+ " WHERE br_id = ?";
		
		MPreservationlistModel result = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, br_id);
			
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next() ) {
					result = new MPreservationlistModel(rs);
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	//ȸ�����̵� �Է��ϸ� ���� ���� �ð� ������ ���� ������ ��ȯ�ϴ� �޼���
	public static ArrayList<MPreservationlistModel> get(Connection conn, String user_id) {
		
		ArrayList<MPreservationlistModel> list = new ArrayList<>();
		
		//���� ��¥ ���ϱ�
		String[] split1 = LocalDate.now().toString().split("-");
		String join1 = String.join("/", split1);
		String today = join1.substring(2);
		String time = LocalTime.now().toString().substring(0, 5);
		//���ϳ�¥ ���ϱ�
		String[] split2 = LocalDate.now().plusDays(1).toString().split("-");
		String join2 = String.join("/", split2);
		String tomorrow = join2.substring(2);
		
		//���� �ð� ������ ���� Ƽ�ϸ� ��ȸ
		String sql1 = "SELECT br_id, bi_day, bi_time, rt_depart_from,"
				+ "rt_arrive_at, bs_name, br_age_group, rt_charge, bs_is_reserved"
				+ " FROM user_info"
				+ " INNER JOIN bus_reservation USING(user_id)"
				+ " INNER JOIN bus_info USING (bi_id)"
				+ " INNER JOIN bus_route USING (rt_id)"
				+ " INNER JOIN bus_seat USING (bs_id)"
				+ " WHERE user_id = ?"
				+ " AND bus_seat.bs_is_reserved = 1"
				+ " AND bus_info.bi_day = ?"
				+ " AND bus_info.bi_time >= ?"
				+ " ORDER BY bus_info.bi_time, bs_name";
		
		//���� ������ Ƽ�� ��ȸ
		String sql2 = "SELECT br_id, bi_day, bi_time, rt_depart_from,"
				+ "rt_arrive_at, bs_name, br_age_group, rt_charge, bs_is_reserved"
				+ " FROM user_info"
				+ " INNER JOIN bus_reservation USING(user_id)"
				+ " INNER JOIN bus_info USING (bi_id)"
				+ " INNER JOIN bus_route USING (rt_id)"
				+ " INNER JOIN bus_seat USING (bs_id)"
				+ " WHERE user_id = ?"
				+ " AND bus_seat.bs_is_reserved = 1"
				+ " AND bus_info.bi_day >= ?"
				+ " ORDER BY bus_info.bi_day, bus_info.bi_time, bs_name";
		
		//���� �ð� ������ ���� Ƽ�� ���� list�� ����
		try(PreparedStatement pstmt1 = conn.prepareStatement(sql1);) {
			pstmt1.setString(1, user_id);
			pstmt1.setString(2, today);
			pstmt1.setString(3, time);
			try(ResultSet rs = pstmt1.executeQuery();) {
				while(rs.next() ) {
					list.add(new MPreservationlistModel(rs));
				}
			}
		//���� ������ Ƽ�� ���� list�� ����
		try(PreparedStatement pstmt2 = conn.prepareStatement(sql2);) {
			pstmt2.setString(1, user_id);
			pstmt2.setString(2, tomorrow);
			try(ResultSet rs = pstmt2.executeQuery();) {
				while(rs.next() ) {
					list.add(new MPreservationlistModel(rs));
				}
			}	
		}				
		} catch (SQLException e) {
			System.out.println("DB���� ���ų��� �������� �߿� ���� �߻�(���ų��� ���� ���� ����)");
		}
		return list;
	}
	

	
	
	
	@Override 
	public String toString() {	
		return String.format("<html><pre style=\"font-family:���;\">���Ź�ȣ : %s<br>"
			+ "%s  %s<br>"
			+ "%s - %s<br>"
			+ "�¼� : %s<br>"
			+ "���ɱ��� : %s<br>"
			+ "��� : %d</pre></html>",
			br_id, bi_day, bi_time, rt_depart_from, rt_arrive_at, bs_name, br_age_group, Math.round(totalCharge()));
	}

	
} 
