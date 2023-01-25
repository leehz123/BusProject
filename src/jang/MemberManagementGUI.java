package jang;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
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

import jang.Data.Member_Data;
import jang.Data.Member_Update_Data;

public class MemberManagementGUI extends JFrame {
	ArrayList<JTextField> tfList = new ArrayList<>();

	JScrollPane scrollpane;
	JPanel panel = new JPanel();
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf5 = new JTextField();
	JTextField tf6 = new JTextField();

	JButton btn1 = new JButton("�߰�");
	JButton btn2 = new JButton("��ü ��ȸ");
	JButton btn3 = new JButton("����");
	JButton btn4 = new JButton("����");
	JButton btn5 = new JButton("�˻�");

	JLabel l1 = new JLabel("ID : ");
	JLabel l2 = new JLabel("�̸� : ");
	JLabel l3 = new JLabel("��й�ȣ : ");
	JLabel l4 = new JLabel("��ȭ��ȣ : ");
	JLabel l5 = new JLabel("������/�մ� : ");
	JLabel l6 = new JLabel("ID : ");

	JLabel pName = new JLabel("ȸ��");
	// JButton backBtn;
	JButton homeBtn;

	ImageIcon image = new ImageIcon("Image/back2.png");
	ImageIcon image2 = new ImageIcon("Image/back3.png");

	ImageIcon home_image = new ImageIcon("Image/home.png");
	ImageIcon home_image2 = new ImageIcon("Image/home2.png");

	public MemberManagementGUI() {
		MemberManagementGUI();

		tfList.add(tf1);
		tfList.add(tf2);
		tfList.add(tf3);
		tfList.add(tf4);
		tfList.add(tf5);
		tfList.add(tf6);

		for (int i = 0; i < tfList.size(); ++i) {
			JTextField tf = tfList.get(i);
			tf.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tf.setText("");
				}
			});
		}

	}

	Member_DB db = new Member_DB();

	public void allView() {
		try {
			remove(scrollpane);
		} catch (NullPointerException e) {

		}

		ArrayList<Member_Data> arr = db.readData();

		String[] colNames = { "���̵�", "�̸�", "��й�ȣ", "��ȭ��ȣ", "������/�մ�" };
		String[][] rowData = new String[arr.size()][colNames.length];

		for (int row = 0; row < rowData.length; ++row) {
			rowData[row][0] = arr.get(row).getID();
			rowData[row][1] = arr.get(row).getName();
			rowData[row][2] = arr.get(row).getPassword();
			rowData[row][3] = arr.get(row).getPhonenum();
			rowData[row][4] = arr.get(row).getPassenger();
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
					tf1.setText(rowData[row][0]);
					tf2.setText(rowData[row][1]);
					tf3.setText(rowData[row][2]);
					tf4.setText(rowData[row][3]);
					tf5.setText(rowData[row][4]);
				}

			}
		});

		model.fireTableDataChanged();
		scrollpane = new JScrollPane();
		scrollpane.setViewportView(table);
//		table.setEnabled(false);

		scrollpane.setBounds(40, 170, 710, 220);
		getContentPane().add(scrollpane);
	}

	public void searchView(String user_id) {
		try {
			remove(scrollpane);
		} catch (NullPointerException e) {

		}

		ArrayList<Member_Data> arr = db.search(user_id);

		String[] colNames = { "���̵�", "�̸�", "��й�ȣ", "��ȭ��ȣ", "������/�մ�" };
		String[][] rowData = new String[arr.size()][colNames.length];

		for (int row = 0; row < rowData.length; ++row) {
			rowData[row][0] = arr.get(row).getID();
			rowData[row][1] = arr.get(row).getName();
			rowData[row][2] = arr.get(row).getPassword();
			rowData[row][3] = arr.get(row).getPhonenum();
			rowData[row][4] = arr.get(row).getPassenger();
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
					tf1.setText(rowData[row][0]);
					tf2.setText(rowData[row][1]);
					tf3.setText(rowData[row][2]);
					tf4.setText(rowData[row][3]);
					tf5.setText(rowData[row][4]);
				}

			}
		});

		model.fireTableDataChanged();
		scrollpane = new JScrollPane();
		scrollpane.setViewportView(table);

		scrollpane.setBounds(40, 170, 710, 220);
		getContentPane().add(scrollpane);
	}

	public void MemberManagementGUI() {
		setTitle("ȸ�� ���� ���α׷�");
		add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		setResizable(false);
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		homeBtn = new JButton(home_image);
		homeBtn.setRolloverIcon(home_image2);
		homeBtn.setBorderPainted(false);
		homeBtn.setBounds(700, 20, 50, 50);
		panel.add(homeBtn);

		pName.setBounds(350, 10, 200, 50);
		pName.setFont(new Font("�޸�����ü", Font.BOLD, 35));
		panel.add(pName);
		
		
		

		btn1.setBounds(160, 80, 70, 30); // �߰�
		btn1.setBackground(new Color(0XE7E6E6));
		btn2.setBounds(40, 80, 100, 30); // ��ȸ
		btn2.setBackground(new Color(0XE7E6E6));
		btn3.setBounds(250, 80, 70, 30); // ����
		btn3.setBackground(new Color(0XE7E6E6));
		btn4.setBounds(680, 410, 70, 30); // ����
		btn4.setBackground(new Color(0XE7E6E6));
		btn5.setBounds(600, 410, 70, 30); // �˻�
		btn5.setBackground(new Color(0XE7E6E6));

		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		panel.add(btn5);

//		TextHint tHint = new TextHint(" ���̵� �Է�");
//		tHint.setBounds(490, 410, 100, 30);
//		panel.add(tHint);
		
		// �Է� ����
		tf1.setBounds(70, 130, 80, 25);
		tf1.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(tf1); // ���̵� �Է� ����
		tf6.setBounds(490, 410, 100, 30);
		tf6.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		tf6.setText("���̵� �Է�");
		panel.add(tf6); // ���̵� �Է� ���� (�˻�)
		tf2.setBounds(195, 130, 60, 25);
		tf2.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(tf2); // �̸� �Է� ����
		tf3.setBounds(325, 130, 80, 25);
		tf3.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(tf3); // ���
		tf4.setBounds(475, 130, 120, 25);
		tf4.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(tf4); // ����
		tf5.setBounds(685, 130, 60, 25);
		tf5.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(tf5); // ����������

		// �Է� ���� �� �̸�
		l1.setBounds(40, 130, 80, 30);
		l1.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(l1); // ���̵� ��
		l2.setBounds(155, 130, 80, 30);
		l2.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(l2); // �̸� ��
		l3.setBounds(260, 130, 80, 30);
		l3.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(l3); // ���
		l4.setBounds(410, 130, 80, 30);
		l4.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(l4); // ����
		l5.setBounds(600, 130, 90, 30);
		l5.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		panel.add(l5); // ����������

		// Ȩ ��ư
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				new Admin_MainFrame(); // ���߿� �������� �ٲٸ� ��
				new ManagerMainFrame2();
				dispose();
			}
		});

		// �Է� ��ư �̺�Ʈ
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String user_id = tf1.getText();
				String user_name = tf2.getText();
				String user_password = tf3.getText();
				String user_phonenum = tf4.getText();
				String user_passenger_manager = tf5.getText();
				ArrayList<Member_Data> arr = new ArrayList<Member_Data>();
				arr = db.search(user_id);

				Pattern passPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
				Matcher passMatcher = passPattern.matcher(user_password);
				Pattern passPattern2 = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
				Matcher passMatcher2 = passPattern2.matcher(user_phonenum);
				Pattern passPattern3 = Pattern.compile("^[��-�Ra-zA-Z]*$");
				Matcher passMatcher3 = passPattern3.matcher(user_name);
				Member_Data data = new Member_Data(user_id, user_name, user_password, user_phonenum,
						user_passenger_manager);
				if (user_id.equals("") || user_name.equals("") || user_password.equals("") || user_phonenum.equals("")
						|| user_passenger_manager.equals("")) {
					JOptionPane.showMessageDialog(null, "������ ��� �Է����ּ���", "�˸�", 1);
					return;
				} else if (!arr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �ߺ��˴ϴ�", "���̵� �ߺ�", 1);
					return;
				} else if (!passMatcher3.find()) {
					JOptionPane.showMessageDialog(null, "�ѱ� �Ǵ� ������ �Է����ּ���", "�̸� ����", 1);
					return;
				} else if (!passMatcher.find()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����+Ư������+���� 8�ڷ� �����Ǿ�� �մϴ�", "��й�ȣ ����", 1);
					return;
				} else if (!passMatcher2.find()) {
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �ٽ� �Է��ϼ���\nex) xxx-xxxx-xxxx", "��ȭ��ȣ ����", 1);
					return;
				} else {
					db.insertData(
							new Member_Data(user_id, user_name, user_password, user_phonenum, user_passenger_manager));
					JOptionPane.showMessageDialog(null, "�ԷµǾ����ϴ�!");

				}
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				allView();
			}

		});

		// ��� ��ư �̺�Ʈ
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				allView();
			}
		});

		// ���� ��ư �̺�Ʈ
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String user_id = tf1.getText();
				String user_name = tf2.getText();
				String user_password = tf3.getText();
				String user_phonenum = tf4.getText();

				Pattern passPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
				Matcher passMatcher = passPattern.matcher(user_password);
				Pattern passPattern2 = Pattern.compile("\\d");
				Matcher passMatcher2 = passPattern2.matcher(user_phonenum);
				Pattern passPattern3 = Pattern.compile("^[��-�Ra-zA-Z]*$");
				Matcher passMatcher3 = passPattern3.matcher(user_name);

				ArrayList<Member_Data> arr = new ArrayList<Member_Data>();
				arr = db.search(user_id);

				int update = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
				if (user_id.equals("") || user_name.equals("") || user_password.equals("")
						|| user_phonenum.equals("")) {
					JOptionPane.showMessageDialog(null, "������ ��� �Է����ּ���", "�˸�", 1);
					return;
				} else if (!passMatcher3.find()) {
					JOptionPane.showMessageDialog(null, "�ѱ� �Ǵ� ������ �Է����ּ���", "�̸� ����", 1);
					return;
				} else if (!passMatcher.find()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����+Ư������+���� 8�ڷ� �����Ǿ�� �մϴ�", "��й�ȣ ����", 1);
					return;
				} else if (!passMatcher2.find()) {
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ���ڷθ� �����Ǿ�� �մϴ�", "��ȭ��ȣ ����", 1);
					return;
				} else {
					if (update == JOptionPane.YES_OPTION) {
						db.updateData(new Member_Update_Data(user_id, user_name, user_password, user_phonenum));
						JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�!", "�˸�", 1);

					}
					allView();
				}
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");

			}
		});

		// ���� ��ư �̺�Ʈ
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String user_id = tf1.getText();
				ArrayList<Member_Data> arr = new ArrayList<Member_Data>();
				arr = db.search(user_id);
				int delete = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
				
				if(user_id.equals("")) {
					JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���", "�˸�", 1);
					return;
				} else if (arr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�", "���̵� ����", 1);
					return;
				} else {
					if (delete == JOptionPane.YES_OPTION) {
						db.deleteData(user_id);
						JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�!", "�˸�", 1);

					}
					allView();

				}

				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
			}
		});

		// �˻� ��ư �̺�Ʈ
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user_id = tf6.getText();
				ArrayList<Member_Data> arr = new ArrayList<Member_Data>();
				arr = db.search(user_id);

				// ��ü ���
				if (arr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�", "���̵� ����", 1);
					return;
				} else {
					searchView(user_id);

				}

				tf6.setText("");

			}
		});
	}
}
