package jang;

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
import javax.swing.JTextField;

import jang.Data.Member_Data;

public class Member_InsertGUI extends JFrame{
	JPanel panel = new JPanel();
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf5 = new JTextField();

	JButton btn1 = new JButton("�߰�");

	JLabel l1 = new JLabel("ID : ");
	JLabel l2 = new JLabel("�̸� : ");
	JLabel l3 = new JLabel("��й�ȣ : ");
	JLabel l4 = new JLabel("��ȭ��ȣ : ");
	JLabel l5 = new JLabel("������/�մ� : ");
	
	JLabel pName = new JLabel("ȸ�� �߰�");
	
	Member_DB db = new Member_DB();

	public Member_InsertGUI() {
		setTitle("ȸ�� ���� ���α׷�");
		add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		setResizable(false);
		setBounds(10, 20, 400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		pName.setBounds(50, 30, 200, 50);
		pName.setFont(new Font("�޸ո���ü", Font.BOLD, 40));
		panel.add(pName);

		// �Է� ����
		tf1.setBounds(65, 100, 80, 25);
		tf1.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf1); // ���̵� �Է� ����
		tf2.setBounds(65, 140, 80, 25);
		tf2.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf2); // �̸� �Է� ����
		tf3.setBounds(260, 100, 80, 25);
		tf3.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf3); // ���
		tf4.setBounds(260, 140, 80, 25);
		tf4.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf4); // ����
		tf5.setBounds(110, 180, 80, 25);
		tf5.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf5); // ����������

		// �Է� ���� �� �̸�
		l1.setBounds(25, 100, 80, 30);
		l1.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l1); // ���̵� ��
		l2.setBounds(25, 140, 80, 30);
		l2.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l2); // �̸� ��
		l3.setBounds(190, 100, 80, 30);
		l3.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l3); // ���
		l4.setBounds(190, 140, 80, 30);
		l4.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l4); // ����
		l5.setBounds(25, 180, 90, 30);
		l5.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l5); // ����������
		
		btn1.setBounds(150, 250, 80, 30);
		btn1.setBackground(new Color(0XE7E6E6));
		panel.add(btn1);

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
				Pattern passPattern2 = Pattern.compile("\\d{11}");
				Matcher passMatcher2 = passPattern2.matcher(user_phonenum);
				Member_Data data = new Member_Data(user_id, user_name, user_password, user_phonenum,
						user_passenger_manager);
				if (!arr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �ߺ��˴ϴ�", "���̵� �ߺ�", 1);
					return;
				} else if (!passMatcher.find()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����+Ư������+���� 8�ڷ� �����Ǿ�� �մϴ�", "��й�ȣ ����", 1);
					return;
				} else if (!passMatcher2.find()) {
					JOptionPane.showMessageDialog(null, "���ڸ� �Է����ּ���", "��ȭ��ȣ ����", 1);
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
				dispose();
			}

		});
	}

}
