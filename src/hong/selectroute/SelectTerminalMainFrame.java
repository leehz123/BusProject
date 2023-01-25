package hong.selectroute;

import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;

import an.OjdbcConnection;

public class SelectTerminalMainFrame extends JFrame {
	
	HashSet<String> terminals = new HashSet<>();
	ResultSet rs;
	final private static String SQL = "SELECT rt_depart_from, rt_arrive_at FROM bus_route";
	ArrayList<TerminalButton> terminalButtons = new ArrayList();
	
	public ArrayList<TerminalButton> getAllBtns() {
		return terminalButtons;
	}
	
	public static void main(String[] args) {
		new SelectTerminalMainFrame();
	}
	
	public SelectTerminalMainFrame() {
		
		setTitle("�͹̳� ����");
		
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		
		getTerminals();
		
		for (String terminal : terminals) {
			TerminalButton btn = new TerminalButton(terminal);
			add(btn);
			terminalButtons.add(btn);
		}
		
		setSize(500, 600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	
	private void getTerminals() {
		try (
				Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL);
		){
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				terminals.add(rs.getString("rt_depart_from"));
				terminals.add(rs.getString("rt_arrive_at"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
