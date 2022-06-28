package jang.Route;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import jang.Route_DB;
import jang.Data.Route_Insert_Data;
import jang.Data.Route_Read_Data;

public class Route_Insert extends JFrame {

	JScrollPane scrolledpane;
	JPanel panel = new JPanel();

	JButton btn3 = new JButton("노선 조회");
	JButton btn1 = new JButton("추가");
	JButton btn2 = new JButton("추가");
	JButton btn4 = new JButton("확인");

	JLabel bus = new JLabel("버스 정보");
	JLabel route = new JLabel("노선 정보");

	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf5 = new JTextField();
	JTextField tf6 = new JTextField();
	JTextField tf7 = new JTextField();
	JTextField tf8 = new JTextField();

	JLabel l1 = new JLabel("날짜 : ");
	JLabel l2 = new JLabel("시간 : ");
	JLabel l3 = new JLabel("노선 ID : ");
	JLabel l4 = new JLabel("출발 : ");
	JLabel l5 = new JLabel("도착 : ");
	JLabel l6 = new JLabel("요금 : ");
	JLabel l7 = new JLabel("좌석 번호 : ");
	JLabel l8 = new JLabel("버스 ID : ");

	JLabel pName = new JLabel("노선 추가");

	Route_DB db = new Route_DB();

	public Route_Insert() {
		Route_Insert();
	}

	public void allView() {
		try {
			remove(scrolledpane);
		} catch (NullPointerException e) {

		}

		ArrayList<Route_Insert_Data> arr = db.route_readData();

		String[] colNames = { "노선 ID", "출발지", "도착지", "요금" };
		String[][] rowData = new String[arr.size()][colNames.length];

		for (int row = 0; row < rowData.length; ++row) {
			rowData[row][0] = Integer.toString(arr.get(row).getRT_ID());
			rowData[row][1] = arr.get(row).getDepart();
			rowData[row][2] = arr.get(row).getArrive();
			rowData[row][3] = arr.get(row).getCharge();

		}

		DefaultTableModel model = new DefaultTableModel(rowData, colNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable table = new JTable(model);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					System.out.println("select row : " + table.getSelectedRow());
					int row = table.getSelectedRow();
					tf3.setText(rowData[row][0]);

				}

			}
		});

		model.fireTableDataChanged();
		scrolledpane = new JScrollPane();
		scrolledpane.setViewportView(table);
//		table.setEnabled(false);

		scrolledpane.setBounds(40, 320, 530, 230);
		getContentPane().add(scrolledpane);
	}

	public void Route_Insert() {
		setTitle("노선 관리 프로그램");
		add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		setResizable(false);
		setBounds(10, 20, 650, 650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

		pName.setBounds(200, 30, 200, 50);
		pName.setFont(new Font("휴먼매직체", Font.BOLD, 40));
		panel.add(pName);

		btn3.setBounds(40, 100, 100, 30); // 전체조회
		btn3.setBackground(new Color(0XE7E6E6));
		btn1.setBounds(500, 185, 70, 30); // 노선 추가
		btn1.setBackground(new Color(0XE7E6E6));
		btn2.setBounds(500, 270, 70, 30); // 버스 추가
		btn2.setBackground(new Color(0XE7E6E6));
		btn4.setBounds(250, 570, 70, 30); // 확인
		btn4.setBackground(new Color(0XE7E6E6));

		panel.add(btn3);
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn4);

		route.setBounds(40, 150, 80, 30);
		route.setFont(new Font("휴먼매직체", Font.BOLD, 20));
		panel.add(route);
		bus.setBounds(40, 230, 80, 30);
		bus.setFont(new Font("휴먼매직체", Font.BOLD, 20));
		panel.add(bus);
//		seat.setBounds(20, 260, 80, 30);
//		seat.setFont(new Font("휴먼매직체", Font.BOLD, 20));
//		panel.add(seat);

		// 입력 공간 라벨
		tf1.setBounds(90, 270, 80, 25);
		tf1.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(tf1); // 날짜
		tf2.setBounds(230, 270, 80, 25);
		tf2.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(tf2); // 시간
		tf3.setBounds(390, 270, 80, 25);
		tf3.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(tf3); // 노선 ID
		tf4.setBounds(90, 190, 80, 25);
		tf4.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(tf4); // 출발지
		tf5.setBounds(230, 190, 80, 25);
		tf5.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(tf5); // 도착지
		tf6.setBounds(370, 190, 80, 25);
		tf6.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(tf6); // 요금

		// 입력 공간 라벨 이름
		l1.setBounds(40, 270, 80, 30);
		l1.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(l1); // 날짜
		l2.setBounds(180, 270, 80, 30);
		l2.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(l2); // 시간
		l3.setBounds(320, 270, 80, 30);
		l3.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(l3); // 노선 ID
		l4.setBounds(40, 190, 80, 30);
		l4.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(l4); // 출발지
		l5.setBounds(180, 190, 80, 30);
		l5.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(l5); // 도착지
		l6.setBounds(320, 190, 80, 30);
		l6.setFont(new Font("휴먼매직체", Font.PLAIN, 15));
		panel.add(l6); // 요금

		// 노선 추가
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String rt_depart_from = tf4.getText();
				String rt_arrive_at = tf5.getText();
				String rt_charge = tf6.getText();

				Pattern passPattern = Pattern.compile("^[가-힣]*$");
				Matcher passMatcher = passPattern.matcher(rt_depart_from);
				Pattern passPattern2 = Pattern.compile("^[가-힣]*$");
				Matcher passMatcher2 = passPattern.matcher(rt_arrive_at);
				Pattern passPattern3 = Pattern.compile("\\d");
				Matcher passMatcher3 = passPattern2.matcher(rt_charge);

				if (rt_depart_from.equals("") || rt_arrive_at.equals("") || rt_charge.equals("")) {
					JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요", "알림", 1);
					return;
				} else if (!passMatcher.find()) {
					JOptionPane.showMessageDialog(null, "출발지를 다시 입력해주세요\n(한글만 입력 가능)", "출발지 오류", 1);
					return;
				} else if (!passMatcher2.find()) {
					JOptionPane.showMessageDialog(null, "도착지를 다시 입력해주세요\n(한글만 입력 가능)", "도착지 오류", 1);
					return;
				} else if (!passMatcher3.find()) {
					JOptionPane.showMessageDialog(null, "숫자만 입력 가능합니다", "요금 오류", 1);
					return;
				} else {
					db.route_insertData(rt_depart_from, rt_arrive_at, rt_charge);
					JOptionPane.showMessageDialog(null, "추가되었습니다!");

				}

				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
			}
		});

		// 버스 정보 추가
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String bi_day = tf1.getText();
				String bi_time = tf2.getText();
				int rtfk_id = Integer.parseInt(tf3.getText());
				

				Pattern passPattern = Pattern.compile("\\d{2}/\\d{2}/\\d{2}");
				Matcher passMatcher = passPattern.matcher(bi_day);
				Pattern passPattern2 = Pattern.compile("\\d{2}:\\d{2}");
				Matcher passMatcher2 = passPattern2.matcher(bi_time);

//				String rt_id = Integer.toString(rtfk_id);
//				ArrayList<Integer> rt_id_list = new ArrayList<>();
//				rt_id_list.add(rtfk_id);
				
//				Route_Read_Data data = new Route_Read_Data();
//				String rt_id = Integer.toString(data.getRT_ID());
//				tf3.getText().length() == 0
				
				
				if (bi_day.equals("") || bi_time.equals("") || tf3.getText().length() == 0 ) {
					JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요", "알림", 1);
					return;
				}
				else if (!passMatcher.find()) {
					JOptionPane.showMessageDialog(null, "날짜를 다시 입력하세요\nex) xx/xx/xx", "날짜 오류", 1);
					return;
				} else if (!passMatcher2.find()) {
					JOptionPane.showMessageDialog(null, "시간을 다시 입력하세요\nex) 24:00", "시간 오류", 1);
					return;
				} else {
					db.bus_info_insertData(bi_day, bi_time, rtfk_id);
					int i = db.get_currsq();
					db.seat_insertData(i);

					JOptionPane.showMessageDialog(null, "추가되었습니다!");
				}

				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf8.setText("");
			}
		});

		// 출력 버튼 이벤트
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				allView();
			}
		});

		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

	}

}
