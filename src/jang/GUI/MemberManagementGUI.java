package jang.GUI;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import an.admin.Admin_MainFrame;
import jang.RoundButton;
import jang.DB.Member_DB;
import jang.Data.Member_Data;
import jang.Data.Member_Update_Data;

public class MemberManagementGUI extends JFrame {
	ArrayList<JTextField> tfList = new ArrayList<>();

	JScrollPane scrollpane;
	JLabel panel = new JLabel();
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf5 = new JTextField();
	JTextField tf6 = new JTextField();

	RoundButton btn1 = new RoundButton("�߰�");
	RoundButton btn2 = new RoundButton("��ü ��ȸ");
	RoundButton btn3 = new RoundButton("����");
	RoundButton btn4 = new RoundButton("����");
	RoundButton btn5 = new RoundButton("�˻�");

	JLabel l1 = new JLabel("ID : ");
	JLabel l2 = new JLabel("�̸� : ");
	JLabel l3 = new JLabel("��й�ȣ : ");
	JLabel l4 = new JLabel("��ȭ��ȣ : ");
	JLabel l5 = new JLabel("������/�մ� : ");
	JLabel l6 = new JLabel("ID : ");
	JLabel imageLabel = new JLabel();

	JLabel pName = new JLabel("ȸ��");

	JButton homeBtn;

	ImageIcon image = new ImageIcon("Image/yujin2.jpg");

	ImageIcon home_image = new ImageIcon("Image/home2-1.png");
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
//		table.setFont(new Font("�޸�����ü", Font.PLAIN, 15));

		// �� ���� ���� ��� ����
		DefaultTableCellRenderer tcellRander = new DefaultTableCellRenderer();
		tcellRander.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcolumn = table.getColumnModel();
		for (int i = 0; i < tcolumn.getColumnCount(); ++i) {
			tcolumn.getColumn(i).setCellRenderer(tcellRander);
		}

		scrollpane.setBounds(40, 170, 710, 370);
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
//		table.setFont(new Font("�޸�����ü", Font.PLAIN, 15));

		// �� ���� ���� ��� ����
		DefaultTableCellRenderer tcellRander = new DefaultTableCellRenderer();
		tcellRander.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcolumn = table.getColumnModel();
		for (int i = 0; i < tcolumn.getColumnCount(); ++i) {
			tcolumn.getColumn(i).setCellRenderer(tcellRander);
		}

		scrollpane.setBounds(40, 170, 710, 370);
		getContentPane().add(scrollpane);
	}

	public void MemberManagementGUI() {
		setTitle("ȸ�� ���� ���α׷�");
		add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		setResizable(false);
		setSize(800, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		homeBtn = new JButton(home_image);
//		homeBtn.setRolloverIcon(home_image2);
		homeBtn.setBorderPainted(false);
		homeBtn.setBounds(700, 20, 50, 50);
		homeBtn.setContentAreaFilled(false);
		panel.add(homeBtn);

		pName.setBounds(350, 10, 200, 50);
		pName.setFont(new Font("�޸�����ü", Font.BOLD, 35));
		panel.add(pName);

//		panel.add(imageLabel);
		panel.setIcon(image);

		btn1.setBounds(160, 80, 70, 30); // �߰�
//		btn1.setBackground(Color.white);
		btn1.setBackground(new Color(255, 255, 255, 122)); // ���� ����(122)
		btn1.setFont(new Font("�޸�����ü", Font.BOLD, 15));
		btn2.setBounds(40, 80, 100, 30); // ��ȸ
		btn2.setBackground(new Color(255, 255, 255, 122));
		btn2.setFont(new Font("�޸�����ü", Font.BOLD, 15));
		btn3.setBounds(250, 80, 70, 30); // ����
		btn3.setBackground(new Color(255, 255, 255, 122));
		btn3.setFont(new Font("�޸�����ü", Font.BOLD, 15));
		btn4.setBounds(680, 560, 70, 30); // ����
		btn4.setBackground(new Color(255, 255, 255, 122));
		btn4.setFont(new Font("�޸�����ü", Font.BOLD, 15));
		btn5.setBounds(600, 560, 70, 30); // �˻�
		btn5.setBackground(new Color(255, 255, 255, 122));
		btn5.setFont(new Font("�޸�����ü", Font.BOLD, 15));

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
		tf6.setBounds(490, 560, 100, 30);
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
				new Admin_MainFrame(); // ���߿� �������� �ٲٸ� ��
//				new ManagerMainFrame2();
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
				arr = db.user_id_check(user_id);

				Pattern passPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
				Matcher passMatcher = passPattern.matcher(user_password);
				Pattern passPattern2 = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
				Matcher passMatcher2 = passPattern2.matcher(user_phonenum);
				Pattern passPattern3 = Pattern.compile("[0-9]+");
				Matcher passMatcher3 = passPattern3.matcher(user_name);
				Pattern passPattern4 = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
				Matcher passMatcher4 = passPattern4.matcher(user_id);
				Pattern passPattern5 = Pattern.compile("[�մ�]*[������]*");
				Matcher passMatcher5 = passPattern4.matcher(user_passenger_manager);

				if (user_id.equals("") || user_name.equals("") || user_password.equals("") || user_phonenum.equals("")
						|| user_passenger_manager.equals("")) {
					JOptionPane.showMessageDialog(null, "������ ��� �Է����ּ���", "�˸�", 1);
					return;
				} else if (!arr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �ߺ��˴ϴ�", "���̵� �ߺ�", 1);
					return;
				} else if (!passMatcher4.find()) {
					JOptionPane.showMessageDialog(null, "���� �Ǵ� ���ڷ� �̷���� 5~12�ڷ� �������ּ���", "���̵� ����", 1);
					return;
				} else if (passMatcher3.find()) {
					JOptionPane.showMessageDialog(null, "�̸����� ���ڸ� �Է��Ͻ� �� �����ϴ�", "�̸� ����", 1);
					return;
				} else if (!passMatcher.find()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����+Ư������+���� �ּ� 8�ڿ��� �ִ� 20�ڷ� �����Ǿ�� �մϴ�", "��й�ȣ ����", 1);
					return;
				} else if (!passMatcher2.find()) {
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �ٽ� �Է����ּ���\nex) xxx-xxxx-xxxx", "��ȭ��ȣ ����", 1);
					return;
				} else if (passMatcher5.find()) {
					JOptionPane.showMessageDialog(null, "������ or �մԸ� �Է� �����մϴ�", "�˸�", 1);
					return;
				} else {
					db.insertData(
							new Member_Data(user_id, user_name, user_password, user_phonenum, user_passenger_manager));
					JOptionPane.showMessageDialog(null, "�߰��Ǿ����ϴ�!");

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
				Pattern passPattern2 = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
				Matcher passMatcher2 = passPattern2.matcher(user_phonenum);
				Pattern passPattern3 = Pattern.compile("[0-9]+");
				Matcher passMatcher3 = passPattern3.matcher(user_name);
				Pattern passPattern4 = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
				Matcher passMatcher4 = passPattern4.matcher(user_id);

				ArrayList<Member_Data> arr = new ArrayList<Member_Data>();
				arr = db.user_id_check(user_id);

				int update = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);

				if (user_id.equals("") || user_name.equals("") || user_password.equals("")
						|| user_phonenum.equals("")) {
					JOptionPane.showMessageDialog(null, "������ ��� �Է����ּ���", "�˸�", 1);
					return;
				} else if (arr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�", "���̵� ����", 1);
					return;
				} else if (!passMatcher4.find()) {
					JOptionPane.showMessageDialog(null, "���� �Ǵ� ���ڷ� �̷���� 5~12�ڷ� �������ּ���", "���̵� ����", 1);
					return;
				} else if (passMatcher3.find()) {
					JOptionPane.showMessageDialog(null, "�̸����� ���ڸ� �Է��ϽǼ� �����ϴ�", "�̸� ����", 1);
					return;
				} else if (!passMatcher.find()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����+Ư������+���� �ּ� 8�ڿ��� �ִ� 20�ڷ� �����Ǿ�� �մϴ�", "��й�ȣ ����", 1);
					return;
				} else if (!passMatcher2.find()) {
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �ٽ� �Է����ּ���\\nex) xxx-xxxx-xxxx", "��ȭ��ȣ ����", 1);
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
				arr = db.user_id_check(user_id);
				int delete = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);

				if (user_id.equals("")) {
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
