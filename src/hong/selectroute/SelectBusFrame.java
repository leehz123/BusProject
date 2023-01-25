package hong.selectroute;

import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import an.OjdbcConnection;

public class SelectBusFrame extends JFrame {
	
	final private static int MAX_SEAT = 21;

	private ArrayList <Integer> canReserve = new ArrayList();
	private ArrayList<Integer> busID = new ArrayList();
	private ArrayList<String> busTime = new ArrayList();
	private ArrayList<SelectBusButton> reserveBtns = new ArrayList();
	SelectBusButton busBtn;
	
	public SelectBusFrame() {
		
		super("���� ����");
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		add(new ColumnNameLabel("���� ��ȣ"));
		add(new ColumnNameLabel("��� �ð�"));
		add(new ColumnNameLabel("���� �¼�"));
		add(new ColumnNameLabel("�����ϱ�"));
		
		
		setSize(440, 550);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	// ���� �ð�, ���� id�� �ҷ����� �޼���
	public void showBusInfo(int routeID, int year, int month, int day) {
		
		String date = String.format("%d/%02d/%02d" , year-2000, month, day);
		
		String sql = "SELECT bi_id, bi_time FROM bus_info WHERE rt_id=? AND bi_day=?";

		try (
				Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){	
			pstmt.setInt(1, routeID);
			pstmt.setString(2, date);
			ResultSet rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				busID.add(rs.getInt("bi_id"));
				busTime.add(rs.getString("bi_time"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ������ �� �ִ� �¼� ���� �ҷ����� �޼���
	public void getCanReserve() {
		
		String sql = "SELECT bs_is_reserved FROM bus_seat WHERE bi_id=?";
		
		for(int bi_id : busID) {
			int cnt = 0;
			try (
					Connection conn = OjdbcConnection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
			){	
				pstmt.setInt(1, bi_id);
				ResultSet rs = pstmt.executeQuery();
				
				
				while (rs.next()) {
					if(rs.getInt("bs_is_reserved") == 0) {
						++cnt;
					}
				}
				this.canReserve.add(cnt);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	}
	
	
	// �� ������ �����ӿ� add�ϴ� �޼���
	public void addInfo() {
		int size = busID.size();
		
		for (int i = 0; i < size; ++i) {
			add(new BusInfoLabel(busID.get(i).toString()));
			add(new BusInfoLabel(busTime.get(i)));
			add(new BusInfoLabel(canReserve.get(i) + " / " + MAX_SEAT));
			
			if(canReserve.get(i) == 0) {
				add(new BusInfoLabel("����"));
			} else {
				busBtn = new SelectBusButton();
				busBtn.setName(busID.get(i).toString());
				add(busBtn);
				reserveBtns.add(busBtn);
			}
		}
	}
	
	public ArrayList<SelectBusButton> getAllBtns() {
		return reserveBtns;
	}
	
}




